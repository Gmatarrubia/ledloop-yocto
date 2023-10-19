import zmq
import time

context = zmq.Context()
socket = context.socket(zmq.PUB)
socket.bind("ipc:///tmp/ledSequence")

print("Switching off leds")
socket.send_string(f"ledsequence off")
