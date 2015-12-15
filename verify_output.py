# Requires Python 3.5 or greater
# (c)2016 MindView LLC: see Copyright.txt
# We make no guarantees that this code is fit for any purpose.
# Visit http://mindviewinc.com/Books/OnJava/ for more book information.
"""
ToDo:
    - Validate errors
"""
from pathlib import Path
from output_duet import Duet, Valid
import os
import collections
import pprint

def trace(*str): pass
# trace = print

if __name__ == '__main__':
    jfiles = sorted([java.name for java in Path(".").glob("**/*.java")])
    duplicates = sorted([x for x, y in collections.Counter(jfiles).items() if y > 1])
    if duplicates:
        print("Duplicates:")
        pprint.pprint(duplicates)

    count = 0
    for output in Path(".").glob("**/*.out"):
        duet = Duet(output)
        trace("duet.ignore:", duet.ignore, duet.java_path )
        if duet.ignore:
            continue
        v = duet.validate()
        if v is Valid.fail:
            with Path("validate_failures.txt").open('a') as vf:
                print(duet, file = vf)
            with Path("strategies.txt").open('a') as st:
                print('    "' + duet.java_path.name + '" : IgnoreSortedLines(),', file = st)
            with Path("update_output.bat").open('a') as uo:
                print('call u ' + str(duet.out_path), file = uo)
        count += 1
    print("\n" + " Verified files = {} ".format(count).center(60, "*"))
    if Path("validate_failures.txt").exists():
        os.system("subl update_output.bat strategies.txt validate_failures.txt")
    else:
         print("\n" + " No Output Errors ".center(60, "="))
