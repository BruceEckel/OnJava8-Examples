start /min "PerfectTimeServer" java PerfectTimeServer
timeout /t %1
taskkill /im java.exe
exit