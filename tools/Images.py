#! py -3
"""
build images for ebook
"""
TODO = """
"""
from pathlib import Path
import pprint
import os, sys, re, shutil
from itertools import chain
from betools import CmdLine, visitDir, ruler, head

resources = Path(r'C:\Users\Bruce\Dropbox\___OnJava\resources')
src = resources/ "Images-svg"
target = resources / "Images"

@CmdLine('x')
def clean_and_setup():
    """Delete old directory, create and populate new one"""
    os.chdir(str(resources))
    if target.exists():
        assert target.is_dir(), "Images must be a directory"
        shutil.rmtree(str(target))
    target.mkdir()
    for img in src.glob("*.svg"):
        print(img.name)
        shutil.copy(str(img), str(target))

# convert_command = "convert {} -resize 1200 {}"
convert_command = "convert {} {}"

@CmdLine('c')
def convert():
    """Convert to desired format"""
    os.chdir(str(target))
    for img in Path(".").glob("*.svg"):
        cmd = convert_command.format(img, img.with_suffix(".gif"))
        print(cmd)
        os.system(cmd)



if __name__ == '__main__': CmdLine.run()

