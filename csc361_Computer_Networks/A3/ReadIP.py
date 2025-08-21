"""
CSC 361 Assignment 3
Zachary Nasu
V00911790
"""
import re
import sys
import packet_struct
from statistics import mean, stdev

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

#reads udp headers
def read_udp(udp_packet):
    udp = packet_struct.UDP_Header()
    udp.get_src_port(udp_packet[0:2])
    udp.get_dst_port(udp_packet[2:4])
    udp.get_length(udp_packet[4:6])
    return udp

#reads icmp headers
def read_icmp(icmp_packet):
    icmp = packet_struct.ICMP_Header()
    icmp.get_protocol_type(icmp_packet[0])
    icmp.get_code_num(icmp_packet[1])
    icmp.get_seq_num(icmp_packet[6:8])
    # print(icmp_packet.hex())
    if icmp.protocol_type in [3,11]:
        icmp.inner_ip_header = read_ip(icmp_packet[8:28])
        if icmp.inner_ip_header.protocol == 1:
            icmp.inner_icmp_header = read_icmp(icmp_packet[28:len(icmp_packet)])
        elif icmp.inner_ip_header.protocol == 17:
            icmp.inner_udp_header = read_udp(icmp_packet[28:len(icmp_packet)])
        else:
            raise Exception("inner not icmp or udp")
    return icmp

# reads ip headers
def read_ip(ip_packet):
    ip = packet_struct.IP_Header()
    ip.get_header_len(ip_packet[0].to_bytes(1, byteorder='little'))
    ip.get_total_len(ip_packet[2:4])
    ip.get_IP(ip_packet[12:16], ip_packet[16:20])
    ip.get_protocol(ip_packet[9])
    ip.get_id(ip_packet[4:6])
    ip.get_offset(ip_packet[6:8])
    ip.get_flags(ip_packet[6])
    ip.get_ttl(ip_packet[8])
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
    #checks to make sure it is not a fragment and if icmp header
    if packet.IP_Header.protocol == 1 and packet.IP_Header.offset == 0:
        packet.ICMP_Header = read_icmp(packet_header[30+packet.IP_Header.ip_header_len:])
    # checks to make sure it is not a fragment and if udp header
    if packet.IP_Header.protocol == 17 and packet.IP_Header.offset == 0:
        packet.UDP_Header = read_udp(packet_header[30+packet.IP_Header.ip_header_len:])
    return packet

def sort_packets(packet_li):
    groups = {}
    windows = False
    src_ip = None
    dst_ip = None
    for packet in packet_li:
        # udp packet
        if packet.IP_Header.protocol == 17:
            # verifies offset is 0 and not an unwanted kind of packet by looking at port
            if packet.IP_Header.offset ==0 and packet.UDP_Header.dst_port >= 33434 and packet.UDP_Header.dst_port <=33529:
                # creates a traceroute group for later calculations
                group = packet_struct.traceroute_group()
                group.identification = packet.IP_Header.identification
                group.add_outgoing_packet(packet)
                group.hops = packet.IP_Header.ttl
                group.src_ip = packet.IP_Header.src_ip
                src_ip = packet.IP_Header.src_ip
                dst_ip = packet.IP_Header.dst_ip
                groups[group.identification] = group
            # if packet is a fragment
            elif packet.IP_Header.offset != 0:
                groups[packet.IP_Header.identification].add_outgoing_packet(packet)
        elif packet.IP_Header.protocol == 1:
            # ICMP request from windows
            if packet.ICMP_Header.protocol_type == 8:
                windows = True
                if packet.IP_Header.offset==0:
                    group = packet_struct.traceroute_group()
                    group.identification = packet.IP_Header.identification
                    group.add_outgoing_packet(packet)
                    group.hops = packet.IP_Header.ttl
                    group.src_ip = packet.IP_Header.src_ip
                    src_ip = packet.IP_Header.src_ip
                    dst_ip = packet.IP_Header.dst_ip
                    groups[group.identification] = group
                else:
                    groups[packet.IP_Header.identification].add_outgoing_packet(packet)
            # ICMP timeout or unreachable
            elif packet.ICMP_Header.protocol_type in [3,11]:
                if windows:
                    for key in groups.keys():
                        # looks for matching request by seq number
                        if groups[key].outgoing_packets[0].ICMP_Header.seq_num ==packet.ICMP_Header.inner_icmp_header.seq_num:
                            groups[key].add_response_packet(packet)
                            groups[key].dst_ip = packet.IP_Header.src_ip
                            continue
                else:
                    for key in groups.keys():
                        # looks for matching request by src port
                        if groups[key].outgoing_packets[0].UDP_Header.src_port == packet.ICMP_Header.inner_udp_header.src_port:
                            groups[key].add_response_packet(packet)
                            groups[key].dst_ip = packet.IP_Header.src_ip
                            continue
            # if ICMP is an ICMP reply
            elif packet.ICMP_Header.protocol_type == 0:
                for key in groups.keys():
                    # looks for matching request by seq number
                    if groups[key].outgoing_packets[0].ICMP_Header.seq_num ==packet.ICMP_Header.seq_num:
                        groups[key].add_response_packet(packet)
                        groups[key].dst_ip = packet.IP_Header.src_ip
                        continue
    return (groups, src_ip, dst_ip)

def print_groups(groups, src_ip, dst_ip, protocols):
    print("The IP address of the source node: {}".format(src_ip))
    print("The IP address of ultimate destination node: {}".format(dst_ip))
    
    routers = {}
    for key in groups.keys():
        if groups[key].dst_ip:
            # creates if none exists for that hop count
            if groups[key].hops in routers.keys():
                routers[groups[key].hops].append(groups[key].dst_ip)
            else:
                routers[groups[key].hops] = [groups[key].dst_ip]
    
    print("The IP addresses of the intermediate destination nodes:")
    for key in sorted(routers.keys()):
        routers[key] = sorted(set(routers[key]))
        print("      router {}: {}".format(key, ", ".join(routers[key])))

    print("\nThe values in the protocol field of IP headers:")
    if 1 in protocols:
        print("      1: ICMP")
    if 6 in protocols:
        print("      6: TCP")
    if 17 in protocols:
        print("      17: UDP")

    print()
    for key in groups.keys():
        if len(groups[key].outgoing_packets) > 1 and len(groups[key].response_packets) > 0:
            print("The number of fragments created from the original datagram {} is: {}".format(groups[key].outgoing_packets[0].IP_Header.identification.hex(),len(groups[key].outgoing_packets)))
            print("The offset of the last fragment is: {}".format(groups[key].outgoing_packets[-1].IP_Header.offset))
        elif len(groups[key].outgoing_packets) == 1 and len(groups[key].response_packets) > 0:
            print("The number of fragments created from the original datagram {} is: {}".format(groups[key].outgoing_packets[0].IP_Header.identification.hex(),1))
            print("The offset of the last fragment is: {}".format(groups[key].outgoing_packets[-1].IP_Header.offset))


    rtt_values = {}
    for group in groups.values():
        if group.dst_ip and (group.src_ip, group.dst_ip) not in rtt_values.keys():
            rtt_values[(group.src_ip, group.dst_ip)] = []
        # If there is more than 1 response packet, calculates with correct request packet
        if len(group.response_packets) > 1:
            print(len(group.response_packets))
            for index, response in group.response_packets:
                group.outgoing_packets[index].get_RTT_value(response)
                rtt_values[(group.src_ip, group.dst_ip)].append(group.outgoing_packets[index].RTT_value)
        # for packets with only one response, creates TTL with each outgoing request packet
        # also filters out groups with no response packets
        elif len(group.response_packets)==1:
            for outgoing_packet in group.outgoing_packets:
                outgoing_packet.get_RTT_value(group.response_packets[0])
                rtt_values[(group.src_ip, group.dst_ip)].append(outgoing_packet.RTT_value)
    print()
    for ip_set in rtt_values.keys():
        # Checks whether there is more than 1 rtt value for standard deviation calc
        if len(rtt_values[ip_set]) > 1:
            print("The avg RTT between {} and {} is: {:.1f} ms, the s.d. is: {:.1f} ms".format(ip_set[0], ip_set[1], mean(rtt_values[ip_set])*1000, stdev(rtt_values[ip_set])*1000))
        else:
            print("The avg RTT between {} and {} is: {:.1f} ms, the s.d. is: N/A ms".format(ip_set[0], ip_set[1], mean(rtt_values[ip_set])*1000))
    print("\n")
    

    

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
    packet_num = 1
    packets = []
    has_orig_time=False
    ip_protocols = []
    while True:
        packet_header = f.read(16)
        if not packet_header:
            break
        if swapped:
            packet_len = int.from_bytes(packet_header[8:12], "little")
        else:
            packet_len = int.from_bytes(packet_header[8:12], "big")
        packet_data = f.read(packet_len)
        # Checks ethernet header for type to know whether to include it
        if swapped:
            if int.from_bytes(packet_data[12:14], "little") != 8:
                packet_num += 1
                continue
        else:
            if int.from_bytes(packet_data[12:14], "big") != 8:
                packet_num += 1
                continue
        packet = read_packet(packet_header+packet_data,packet_num, orig_time,swapped)
        packets.append(packet)
        ip_protocols.append(packet.IP_Header.protocol)
        packet_num += 1
    f.close()
    groups = sort_packets(packets)
    print_groups(groups[0], groups[1], groups[2], sorted(set(ip_protocols)))

if __name__ == "__main__":
    main()
