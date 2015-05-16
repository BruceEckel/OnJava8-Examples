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

root = Path('.').resolve().parent.parent
boxdir = root / r"Box Sync\TIJ4-ebook-Backups"
gdrive = root / "Google Drive" / "TIJ4RefreshedBackups"
idrive = root / "IDrive-Sync" / "TIJ4-ebook-Backups"
print(boxdir)
print(gdrive)
print(idrive)

def cp(src, dest, display=True):
    if type(src) is pathlib.WindowsPath:
        name = src.name
    else:
        name = src
    if display:
        print("copying", name)
        print("to:", dest)
    shutil.copy(str(src), str(dest))

now = datetime.datetime.now().strftime("%Y-%m-%d_%H-%M")
zip_file_name = 'TIJDirectorsCut-' + now + '.zip'
dest = boxdir / zip_file_name
print(dest)
tozip = ["Notes.txt", "backup.bat", "go.bat"] + glob("*.py") + glob("*.docx") + glob("*.docm")

with zipfile.ZipFile(str(dest), 'w') as myzip:
    for f in tozip:
        myzip.write(f)

cp(dest, gdrive)
cp(dest, idrive)

shortcut = Path(r"C:\Python34\Scripts")
tools = ["Examples.py", "Validate.py", "backup.bat", "go.bat", "StripLastBlankLine.py",
             shortcut / "v.bat", shortcut / "e.bat"]

print("copying tools to Github")
for tool in tools:
    cp(tool, root / "Documents" / "GitHub" / "TIJ-Directors-Cut" / "tools", False)

# Touch this file to indicate most recent update time:
os.utime("backup.bat", None)
msvcrt.getch()
