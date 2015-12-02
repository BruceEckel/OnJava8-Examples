# Requires Python 3.5 or greater
# ©2016 MindView LLC: see Copyright.txt
# We make no guarantees that this code is fit for any purpose.
# Visit http://mindviewinc.com/Books/OnJava/ for more book information.
"""
ToDo:
    - Validate errors
"""
from pathlib import Path
from output_duet import Duet, Valid
import os

exclude = [
    "CADState.out",
    "Logon.out",
    "worm.out",
]

if __name__ == '__main__':
    count = 0
    for output in Path(".").glob("**/*.out"):
        if output.parts[-1] in exclude:
            continue
        duet = Duet(output)
        v = duet.validate()
        if v is Valid.fail:
            with Path("validate_failures.txt").open('a') as vf:
                print(duet, file = vf)
            with Path("strategies.txt").open('a') as st:
                print('    "' + duet.java_path.name + '" : Ignore.sorted,', file = st)
            with Path("update_output.bat").open('a') as uo:
                print('call o ' + str(duet.out_path), file = uo)
        count += 1
    print("\n" + " count = {} ".format(count).center(60, "*"))
    os.system("subl validate_failures.txt update_output.bat strategies.txt")
