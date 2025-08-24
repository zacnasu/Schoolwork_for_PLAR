CSC361 Assignment 2
Zachary Nasu V00911790

Running program (assuming python is python3):
python ReadTCP.py [capfile]

There were a couple of ambiguities that I found in the description so I recorded the assumptions I made
Assumptions:
-min, max, mean window size is for each individual packet vs combined for each connection
-number of bytes sent for each packet is MSS and not MSS
-duration is measured by the difference in timestamp between first syn and last fin
-end time is defined by last fin packet
-start time is defined by first syn packet