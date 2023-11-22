import zmq
import time

context = zmq.Context()
socket = context.socket(zmq.PAIR)
socket.connect("ipc:///tmp/ledSequence")

while True:
    print("Switching off leds")
    socket.send_string(f"ledsequence off")
