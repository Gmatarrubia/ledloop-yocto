#!/usr/bin/python3
import json
import os
import sys
import shutil

APP_PATH = os.path.dirname(os.path.realpath(__file__))
WORK_MODE_JSON_FILE = os.path.join(APP_PATH, "work-mode.json")
WORK_MODE_JSON_BACKUP_FILE = os.path.join(APP_PATH, "work-mode-backup.json")
def write_work_json(jsonData):
    with open(WORK_MODE_JSON_FILE, "w", encoding="utf-8") as f:
        try:
            json.dump(jsonData, f)
            print("{'status': 'success'}")
        except json.JSONDecodeError:
            print("{'status': 'json format error'}")
            return

def createBackUp():
    shutil.copy(WORK_MODE_JSON_FILE, WORK_MODE_JSON_BACKUP_FILE)

def main():
    new_data = ""
    content_len = os.environ.get('CONTENT_LENGTH', '0')
    if int(content_len) > 0:
        body = sys.stdin.read(int(content_len))
        new_data = json.loads(body)

    print("Content-type: application/json")
    print()
    createBackUp()
    write_work_json(new_data)

if __name__ == "__main__":
    main()
