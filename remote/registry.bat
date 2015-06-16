start /min "rmiregistry" rmiregistry
timeout /t %1
taskkill /im rmiregistry.exe
exit