@setlocal enableextensions enabledelayedexpansion
@echo off
set counter=%1
set number=%2
set root=%CD%
ECHO "build.counter %counter% env.BUILD_NUMBER: %number%"
#cd ws-wrapper\chat-ws-war\target\
#ren *.war *-%number%.war
set /a "i = 1"
set /a "i = %counter% - 1"
if !i! lss 0 (ECHO "ERROR"
goto EXIT) else (
goto JBOSS
)

:JBOSS
SET  current_build=chat-ws-war-%number%
CALL set tmp=%%current_build:~0,-1%%
ECHO "%tmp%"
set "old_build=%tmp%%i%.war"
ECHO "UNDEPLOY: %old_build%"


ECHO "I: %i%"
goto EXIT
chat-ws-war.war

:EXIT
exit /b 0