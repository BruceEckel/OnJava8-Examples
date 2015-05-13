@setlocal enabledelayedexpansion && py -3 -x "%~f0" %* & exit /b !ERRORLEVEL!
#start python code here
import zipfile
import datetime
import os, sys, shutil
from glob import glob
#import platform
#print(platform.python_version())
from pathlib import Path

destdirs = [ # Different paths for different machines:
    r'''C:\Users\Bruce Eckel\Box Sync\TIJ4-ebook-Backups''',
    r'''C:\Users\Bruce\Box Sync\TIJ4-ebook-Backups''',
]

for destdir in destdirs:
  if os.path.exists(destdir):
      break
assert(os.path.exists(destdir))

now = datetime.datetime.now().strftime("%Y-%m-%d_%H-%M")
zip_file_name = 'TIJDirectorsCut-' + now + '.zip'
dest = os.path.join(destdir, zip_file_name)

with zipfile.ZipFile(dest, 'w') as myzip:
    # for f in glob("*.odt") + glob("*.docx") + glob("*.doc") + glob("*.txt") + glob("*.bat") + glob("*.py"):
    for f in ["Examples.py", "Validate.py", "Notes.txt", "backup.bat", "backupall.bat"] + glob("*.docx"):
        myzip.write(f)
    # myzip.write("TIJDirectorsCut.docx")

# copy dest to:
# double_back = os.path.join(r'C:\Users\Bruce\IDrive-Sync\TIJ4-ebook-Backups', zip_file_name)
shutil.copy(dest, r'C:\Users\Bruce\Google Drive\TIJ4RefreshedBackups')
shutil.copy(dest, r'C:\Users\Bruce\IDrive-Sync\TIJ4-ebook-Backups')

shutil.copy("Examples.py", r'C:\Users\Bruce\Documents\GitHub\TIJ-Directors-Cut\tools')
shutil.copy("Validate.py", r'C:\Users\Bruce\Documents\GitHub\TIJ-Directors-Cut\tools')

# Touch this file to indicate most recent update time:
os.utime("backup.bat", None)
