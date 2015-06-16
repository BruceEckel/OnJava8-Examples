start /min "registry.bat" registry.bat 3
start /min "server.bat" server.bat 3
timeout /t 1
java DisplayPerfectTime
timeout /t 3
exit