"""
CSC 361 Assignment 1
Zachary Nasu
V00911790
"""
import socket
import ssl
import re
import sys

# Function to get cookies given data
def get_cookies(data):
    # gets all cookies
    matches = re.findall("Set-Cookie: .*\Z|Set-Cookie: .*\n",data)
    print("2. List of Cookies:")

    # parses each cookie to put into desired format
    for match in matches:
        li = match.split("; ")
        line = "cookie name: "
        line += re.search(".*: (.*)\Z", li[0]).group(1).split("=")[0]
        if len(li)> 1:
            for item in li[1:]:
                item =item.replace("\n", "")
                if "=" in item:
                    fields = item.split("=")
                    line +=( ", " + fields[0] + ": " + fields[1])
        print(line)

# gets website with optional arguements and prints out necessary information
def get_response(website, print_redirect=False, print_response=False):
    # in case root of host is not desired page
    host =website
    folder = "/"
    # splits up if not root of host
    if re.search("[a-z]*\/[a-z]*", website):
        folder=re.search("\/.*", website).group()
        host = website.replace(folder, "")
    

    context = ssl.create_default_context()
    conn = context.wrap_socket(socket.socket(socket.AF_INET), server_hostname=host)
    try:
        conn.connect((host, 443))
        conn.send("GET {} HTTP/1.1\r\nHost:{}\r\n\r\n".format(folder, host).encode())
        data = conn.recv(2048).decode()
        conn.close()
    except:
        raise Exception("connection failed for {}".format(host))


    # throws exception if not found
    if re.search("HTTP\/[0-1]\.[0-9] 404", data):
        raise Exception("404 website {} not found".format(website))

    # handle redirection
    if re.search("HTTP\/[0-1]\.[0-9] 302", data) or re.search("HTTP\/[0-1]\.[0-9] 301", data):
        location_str = ""
        if re.search("[L|l]ocation: https:/.*\n", data):
            location_str = re.search("[L|l]ocation: .*\n", data).group().strip().lower().replace("location: https://", "")
        elif re.search("[L|l]ocation: http:/.*\n", data):
            location_str = re.search("[L|l]ocation: .*\n", data).group().strip().lower().replace("location: http://", "")
        if print_redirect:
            print("redirect: to {}\n".format(location_str))
        return get_response(location_str, print_redirect, print_response)
    
    #print website
    print("website: {}".format(website))
    #test http2
    supports_http2(host)

    #parses cookies
    get_cookies(data)

    #checking out if password protected
    if re.search("40[1-3] Unauthorized", data):
        print("3. Password-protected: True\n")
    else:
        print("3. Password-protected: False\n")
    
    # prints out response if flag included
    if print_response:
        print(data)


def supports_http2(host):
    h2 = "no"
    context =ssl.create_default_context()
    context.set_alpn_protocols(['http/1.1', 'h2'])
    conn = context.wrap_socket(socket.socket(socket.AF_INET), server_hostname=host)
    conn.connect((host, 443))
    if conn.selected_alpn_protocol() != None:
        if 'h2' in conn.selected_alpn_protocol():
            h2 = "yes"
        conn.close()
        print("1. supports http2: {}".format(h2))
    else:
        print("1. supports http2: no")


def main():
    # parsing command line input
    website = sys.argv[1]
    print_redirect = False
    print_response = False
    for item in sys.argv[2:]:
        if item == "--print-redirect":
            print_redirect = True
        elif item == "--print-response":
            print_response = True
        else:
            raise Exception("did not recognize {}".format(item))

    # in case http or https is included
    if re.search("https:/.*", website):
        website = website.replace("https://", "")
    elif re.search("http:/.*", website):
        website = website.replace("http://", "")
    get_response(website, print_redirect, print_response)

if __name__ == "__main__":
    main()