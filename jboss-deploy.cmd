@setlocal enableextensions enabledelayedexpansion
@echo off
set counter=%1
set number=%2
set root=%CD%
ECHO "build.counter %counter% env.BUILD_NUMBER: %number%"
cd ws-wrapper\chat-ws-war\target\
ren chat-ws-war.war chat-ws-war%number%.war
set /a "i = 1"
set /a "i = %counter% - 1"
if !i! lss 0 (ECHO "ERROR"
goto EXIT) else (
goto JBOSS
)

:JBOSS
SET  current_build=chat-ws-war%number%
CALL set tmp=%%current_build:~0,%-2%%
ECHO "%tmp%"
set "old_build=%tmp%%i%.war"
SET  current_build=%current_build%.war
ECHO "UNDEPLOY: %old_build%"
CALL %JBOSS_HOME%\bin\jboss-cli.bat --connect "undeploy %old_build%,quit"
ECHO "DEPLOY: %current_build%"
CALL %JBOSS_HOME%\bin\jboss-cli.bat --connect "deploy %root%\ws-wrapper\chat-ws-war\target\%current_build%,quit" )


goto EXIT 


:EXIT
exit /b 0