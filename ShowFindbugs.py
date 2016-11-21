#! py -3
# Requires Python 3.5
# Displays all the Findbugs main.html reports from "On Java 8" on Windows
# Must run "gradlew findbugsMain" first
from pathlib import Path
import os

cmds = ["start " + str(report) for report in Path(".").rglob("main.html")]
(Path(".") / "ShowFindbugs.bat").write_text("\n".join(cmds).strip() + "\n")
os.system("ShowFindbugs.bat")
