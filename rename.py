#! py -3
from pathlib import Path

for old in Path('.').glob("**/oldbuild (1).xml"):
    print(old)
    old.rename(old.parent / "oldbuild.xml")
