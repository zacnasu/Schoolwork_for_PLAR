CSC361 Assignment 1
Zachary Nasu V00911790

Running program (assuming python is python3):
python SmartClient.py [website]

Optional Flags:
[--print-redirect]
if you include:
python SmartClient.py [website] --print-redirect
then it will print out the site redirected to.
The "website" field will only include the website requested
rather than the website that was eventually at the end. I made this assumption thinking this was what was desired.

[--print-response]
if you include
python SmartClient.py [website] --print-response
then it will print out the output of the last request sent

Other assumptions:
website that is desired to be printed at the beginning is
the last page if redirected. (ie uvic.ca redirects to www.uvic.ca so www.uvic.ca is printed)