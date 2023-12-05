#!/usr/bin/python3
import json
import os
import sys

APP_PATH = os.path.dirname(os.path.realpath(__file__))
WORK_MODE_JSON_FILE = os.path.join(APP_PATH, "figures-mode.json")

def load_work_json():
    with open(WORK_MODE_JSON_FILE, "r") as f:
        return json.load(f)

def main():
    print("Content-type: application/json")
    print("")
    work = load_work_json()
    print(json.dumps(work))

if __name__ == "__main__":
    main()

