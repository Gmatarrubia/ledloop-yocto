#!/usr/bin/python3
import json
import os
import sys
import shutil

APP_PATH = os.path.dirname(os.path.realpath(__file__))
WORK_MODE_JSON_FILE = os.path.join(APP_PATH, "work-mode.json")
WORK_MODE_JSON_BACKUP_FILE = os.path.join(APP_PATH, "work-mode-backup.json")

def restoreBackUp():
    shutil.copy(WORK_MODE_JSON_BACKUP_FILE, WORK_MODE_JSON_FILE)

def main():
    new_data = ""
    content_len = os.environ.get('CONTENT_LENGTH', '0')
    if int(content_len) > 0:
        body = sys.stdin.read(int(content_len))
        new_data = json.loads(body)

    print("Content-type: application/json")
    print()
    restoreBackUp()

if __name__ == "__main__":
    main()
