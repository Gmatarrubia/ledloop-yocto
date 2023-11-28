#!/usr/bin/python3
from datetime import datetime

def main():
    now = datetime.now()
    print("Content-Type: text/plain")
    print("")
    print("This is my first test")
    print(str(now))

    print("---------------------------------")
if __name__ == "__main__":
    main()
