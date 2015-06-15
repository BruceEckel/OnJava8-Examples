@setlocal enabledelayedexpansion && py -3 -x "%~f0" %* & exit /b !ERRORLEVEL!
#start python code here
import zipfile
import datetime
import os, sys, shutil
from glob import glob
import pathlib
from pathlib import Path
import pprint
import msvcrt
verbose = True

root = Path('.').resolve().parent.parent
boxdir = root / "Box Sync" / "On-Java-Backups"
gdrive = root / "Google Drive" / "On-Java-Backups"
idrive = root / "IDrive-Sync" / "On-Java-Backups"

def cp(src, dest, display=True, shortForm=False):
    if type(src) is pathlib.WindowsPath:
        name = src.name
    else:
        name = src
    if display:
        if shortForm:
            print(name)
        else:
            print("\ncopying", name)
            print("to:", dest)
    shutil.copy(str(src), str(dest))

now = datetime.datetime.now().strftime("%Y-%m-%d_%H-%M")
zip_file_name = 'OnJava-' + now + '.zip'
dest = boxdir / zip_file_name
print(dest)
tozip = ["Notes.txt", "backup.bat", "go.bat", "Checklist.txt"] + glob("*.py") + glob("*.docx") + glob("*.docm")

with zipfile.ZipFile(str(dest), 'w') as myzip:
    for f in tozip:
        myzip.write(f)
        if verbose:
            print("adding {}".format(f))

cp(dest, gdrive)
cp(dest, idrive)

shortcut = Path(r"C:\Python34\Scripts")
tools = ["Examples.py", "Validate.py", "AttachResults.py", "backup.bat", "go.bat", "update_git.py",
             shortcut / "a.bat", shortcut / "v.bat", shortcut / "e.bat", shortcut / "g.bat", shortcut / "home.bat"]

print("\nCopying tools to Github")
for tool in tools:
    cp(tool, root / "Documents" / "GitHub" / "On-Java" / "tools", shortForm=True)

cp("Checklist.txt", root / "Documents" / "GitHub" / "On-Java", shortForm=True)


# Touch this file to indicate most recent update time:
os.utime("backup.bat", None)
msvcrt.getch()
