@echo off
set counter=%1
set number=%2
ECHO "build.counter %counter% env.BUILD_NUMBER: %number%"
goto PAUSE
:PAUSE
pause