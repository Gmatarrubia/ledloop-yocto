import zmq
import time

context = zmq.Context()
socket = context.socket(zmq.PAIR)
socket.connect("tcp://192.168.1.42:28700")

while True:
    print("Switching on leds")
    socket.send_string(f"ledsequence on")
