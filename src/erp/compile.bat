@echo off
setlocal
set SRC_DIR=src
set BIN_DIR=bin
rem Create the bin directory if it does not exist
if not exist %BIN_DIR% mkdir %BIN_DIR%
rem Compile all Java files
for /r %SRC_DIR% %%f in (*.java) do javac -d %BIN_DIR% %%f
endlocal
