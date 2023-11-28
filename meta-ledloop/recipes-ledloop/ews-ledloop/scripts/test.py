#!/usr/bin/python3
import json
import os
import sys
from datetime import datetime
from urllib.parse import parse_qs

APP_PATH = os.path.dirname(os.path.realpath(__file__))
WORK_MODE_JSON_FILE = os.path.join(APP_PATH,"..","..","app-ledloop", "work-mode.json")

def load_work_json():
    with open(WORK_MODE_JSON_FILE, "r") as f:
        return json.load(f)

def write_work_json(jsonData):
    with open(WORK_MODE_JSON_FILE, "w") as f:
        json.dump(jsonData, f)

def main():
    newData = ""
    content_len = os.environ.get('CONTENT_LENGTH', '0')
    if len(sys.argv) > 1:
        newData = json.loads(sys.argv[1])
    elif int(content_len) > 0:
        body = sys.stdin.read(int(content_len))
        newData = json.loads(body)
    else:
        newData = json.loads('{"core":{"mode":"fill","args":{"r":0,"g":100,"b":0}}}')

    now = datetime.now()
    print("Content-Type: text/plain")
    print("")
    print(f"Content: {content_len}")
    print("This is my first test")
    print(str(now))

    work = load_work_json()
    print(work)
    print("---------------------------------")
    for item in newData.items():
        print(item)
        if item[0] in work.keys():
            work[item[0]] = item[1]
    print(work)
    write_work_json(work)

if __name__ == "__main__":
    main()