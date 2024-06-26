#!/usr/bin/python3
import json
import os
import sys

APP_PATH = os.path.dirname(os.path.realpath(__file__))
WORK_MODE_JSON_FILE = os.path.join(APP_PATH, "work-mode.json")

def load_work_json():
    with open(WORK_MODE_JSON_FILE, "r", encoding="utf-8") as f:
        return json.load(f)

def write_work_json(jsonData):
    with open(WORK_MODE_JSON_FILE, "w", encoding="utf-8") as f:
        json.dump(jsonData, f)

def main():
    new_data = ""
    content_len = os.environ.get('CONTENT_LENGTH', '0')
    if len(sys.argv) > 1:
        new_data = json.loads(sys.argv[1])
    elif int(content_len) > 0:
        body = sys.stdin.read(int(content_len))
        new_data = json.loads(body)

    work = load_work_json()

    for item in new_data.items():
        if item[0] in work.keys():
            work[item[0]] = item[1]
    write_work_json(work)

if __name__ == "__main__":
    main()
