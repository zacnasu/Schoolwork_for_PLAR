"""
CSC 361 Assignment 2
Zachary Nasu
V00911790
"""
import re
import sys
import packet_struct
from statistics import mean

# Reads TCP packets 
def read_tcp(tcp_packet):
    tcp = packet_struct.TCP_Header()
    tcp.get_data_offset(tcp_packet[12].to_bytes(1, byteorder='little'))
    tcp.get_flags(tcp_packet[13].to_bytes(1, byteorder='little'))
    tcp.get_window_size(tcp_packet[14].to_bytes(1, byteorder='little'),tcp_packet[15].to_bytes(1, byteorder='little'))
    tcp.get_src_port(tcp_packet[0:2])
    tcp.get_dst_port(tcp_packet[2:4])
    tcp.get_seq_num(tcp_packet[4:8])
    tcp.get_ack_num(tcp_packet[8:12])
    return tcp

def read_ip(ip_packet):
    ip = packet_struct.IP_Header()
    ip.get_header_len(ip_packet[0].to_bytes(1, byteorder='little'))
    ip.get_total_len(ip_packet[2:4])
    ip.get_IP(ip_packet[12:16], ip_packet[16:20])
    if ip_packet[9] != 0x06:
        ip.tcp = False
        return ip
    return ip

def read_packet(packet_header, packet_num, orig_time,swapped):
    packet = packet_struct.packet()
    packet.packet_No_set(packet_num)
    if swapped:
        packet.timestamp_set(packet_header[0:4],packet_header[4:8],orig_time)
    else:
        packet.timestamp_set(bytes(reversed(packet_header[0:4])),bytes(reversed(packet_header[4:8])),orig_time)
    #skips over packet and ethernet header
    packet.IP_Header = read_ip(packet_header[30:])
    #checks to make sure there is an ip packet
    if packet.IP_Header.tcp:
        packet.TCP_Header = read_tcp(packet_header[30+packet.IP_Header.ip_header_len:])
    return packet

# Organizes packets into connections
def organize_packets(packet_list):
    connections = {}
    for packet in packet_list:
        # if source ip and port and dest ip and port are already in connections (for client)
        if (packet.IP_Header.src_ip,
        packet.TCP_Header.src_port,
        packet.IP_Header.dst_ip,
        packet.TCP_Header.dst_port) in connections:
            connections[(packet.IP_Header.src_ip,
            packet.TCP_Header.src_port,
            packet.IP_Header.dst_ip,
            packet.TCP_Header.dst_port)].add_src_dst_packet(packet)
        # if ip/port combination is already there but reverse (for server)
        elif (packet.IP_Header.dst_ip,
        packet.TCP_Header.dst_port,
        packet.IP_Header.src_ip,
        packet.TCP_Header.src_port) in connections:
            connections[(packet.IP_Header.dst_ip,
        packet.TCP_Header.dst_port,
        packet.IP_Header.src_ip,
        packet.TCP_Header.src_port)].add_dst_src_packet(packet)
        # create new connection if none found
        else:
            new_connection = packet_struct.Connection(packet.IP_Header.src_ip,
                packet.TCP_Header.src_port,
                packet.IP_Header.dst_ip,
                packet.TCP_Header.dst_port)
            new_connection.add_src_dst_packet(packet)
            connections[(packet.IP_Header.src_ip,
                packet.TCP_Header.src_port,
                packet.IP_Header.dst_ip,
                packet.TCP_Header.dst_port)] = new_connection
    return connections

# sets relative seq and relative ack for rtt calculations
def set_relative_seq_ack(completed_connections):
    for connection in completed_connections:
        orig_num_seq = connection.packets[0].TCP_Header.seq_num
        orig_num_ack = connection.packets[1].TCP_Header.seq_num
        for packet in connection.packets:
            if connection.src_ip == packet.IP_Header.src_ip:
                packet.TCP_Header.relative_seq_num(orig_num_seq)
                packet.TCP_Header.relative_ack_num(orig_num_ack)
            else:
                packet.TCP_Header.relative_ack_num(orig_num_seq)
                packet.TCP_Header.relative_seq_num(orig_num_ack)

# gets rtt values for completed connections
def get_rtt_values(completed_connections):
    rtt_values = []
    for connection in completed_connections:
        for index, packet1 in enumerate(connection.packets):
            if packet1.IP_Header.src_ip == connection.src_ip and \
            packet1.TCP_Header.flags["FIN"] == 0 and \
            packet1.TCP_Header.flags["RST"] == 0 and \
            packet1.IP_Header.total_len>packet1.IP_Header.ip_header_len+packet1.TCP_Header.data_offset:
                for packet2 in connection.packets[index:]:
                    if packet2.TCP_Header.ack_num == packet1.TCP_Header.seq_num+packet1.IP_Header.total_len-packet1.IP_Header.ip_header_len-packet1.TCP_Header.data_offset\
                    and packet2.IP_Header.src_ip == connection.dst_ip:
                        packet1.get_RTT_value(packet2)
                        rtt_values.append(packet1.RTT_value)
                        break
    return rtt_values

# Gathers aggregate statistics for completed connections
def gather_statistics(completed_connections):
    durations = []
    rtt_values = []
    no_of_packets = []
    window_sizes = []
    for connection in completed_connections:
        durations.append(connection.get_duration())
        no_of_packets.append(len(connection.packets))
        connection_rtt_values = 0
        for packet in connection.packets:
            window_sizes.append(packet.TCP_Header.window_size)
    set_relative_seq_ack(completed_connections)
    rtt_values = get_rtt_values(completed_connections)
            
    print("D) Complete TCP connections:",len(completed_connections))
    print("\nMinimum time duration:", min(durations))
    print("Mean time duration:",round(mean(durations),6))
    print("Maximum time durtaion:",max(durations))
    print("\nMinimum RTT value:",min(rtt_values))
    print("Mean RTT value",round(mean(rtt_values),6))
    print("Maximum RTT value",max(rtt_values))
    print("\nMinimum number of packet including both send/recieved",min(no_of_packets))
    print("Mean number of packet including both send/recieved", round(mean(no_of_packets),6))
    print("Maximum number of packet including both send/recieved", max(no_of_packets))
    print("\nMinimum receive window size including both send/received", min(window_sizes))
    print("Mean receive window size including both send/received", round(mean(window_sizes),6))
    print("Maximum receive window size including both send/received", max(window_sizes))
    print("\n--------------------------------------------------\n")

# prints connection stats, also collects completed connections
def print_connections(connections):
    resets=0
    completed=0
    still_open=0
    completed_connections = []
    print("A) Total number of connections:", len(connections))
    print("--------------------------------------------------")
    print("B) Connections' details:\n")
    for index, connection in enumerate(connections.values()):
        # counting resets
        if connection.reset == True:
            resets+=1
        # having syn and fin makes it complete
        if connection.fin == True and connection.syn == True:
            completed+=1
            completed_connections.append(connection)
            status = "completed"
        else:
            status = "not completed"
            still_open+=1
        print("Connection:", index+1)
        print("Source Address:", connection.src_ip)
        print("Destination Address:",connection.dst_ip)
        print("Source Port:",connection.src_port)
        print("Destination Port:",connection.dst_port)
        print("Status:", status)
        if status == "completed":
            print("Start time:", connection.get_start_time())
            print("End Time:",connection.get_end_time())
            print("Duration:", connection.get_duration())
            print("Number of packets sent from Source to Destination:", connection.src_to_dst_packet)
            print("Number of packets sent from Destination to Source:", connection.dst_to_src_packet)
            print("Total number of packets:", connection.src_to_dst_packet+connection.dst_to_src_packet)
            print("Number of data bytes sent from Source to Destination:", connection.src_to_dst_data)
            print("Number of data bytes sent from Destination to Source:", connection.dst_to_src_data)
            print("Total number of data bytes:", connection.src_to_dst_data+connection.dst_to_src_data)
        print("END")
        print("+++++++++++++++++++++++++++++++++")
    print("\nC) General\n")
    print("Total number of complete TCP connections:", completed)
    print("Number of reset TCP connections:", resets)
    print("Number of TCP connections that were still open when the trace capture ended:", still_open)
    print("\n--------------------------------------------------\n")
    return completed_connections

def main():
    #takes in capfile from command line
    if len(sys.argv)>1:
        capfile = sys.argv[1]
    else:
        raise Exception("No capfile")
    f = open(capfile, "rb")
    # reads 24 byte global header
    global_header =f.read(24)
    #checks magic number and creates swapped variable
    if global_header[0:4] == 0xa1b2c3d4:
        swapped=False
        orig_time = int.from_bytes(global_header[8:16], "big")
    else:
        swapped=True
        orig_time = int.from_bytes(global_header[8:16], "little")
    packet_num = 0
    packets = []
    orig_time = 0
    has_orig_time=False
    while True:
        packet_header = f.read(16)
        if not packet_header:
            break
        if swapped:
            packet_len = int.from_bytes(packet_header[8:12], "little")
        else:
            packet_len = int.from_bytes(packet_header[8:12], "big")
        packet_data = f.read(packet_len)
        packet = read_packet(packet_header+packet_data,packet_num, orig_time,swapped)
        if packet.IP_Header.tcp:
            packets.append(packet)
            if not has_orig_time:
                orig_time = packet.timestamp
                packet.timestamp = 0
                has_orig_time=True
        packet_num += 1
    f.close()
    #organizes packets into connections
    connections = organize_packets(packets)
    #prints connections stats and returns completed connections
    completed_connections = print_connections(connections)
    #prints aggregrate stats for completed connections
    gather_statistics(completed_connections)

if __name__ == "__main__":
    main()
