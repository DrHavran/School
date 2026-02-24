@echo off
setlocal EnableDelayedExpansion
:CALC

set /P x=Enter x: 
set /P operation=Operation: 
set /P y=Enter y: 

set check="false"

for %%n in (%*) do (
    if "!operation!"=="%%n" set check="true"
)

if %check% == "false" exit /B 2

if "%operation%"=="+" set /A result=x+y
if "%operation%"=="-" set /A result=x-y
if "%operation%"=="*" set /A result=x*y
if "%operation%"=="/" set /A result=x/y

if %result% == -1 exit /B 0

echo ---------------------
echo Result: %result%
echo ---------------------

goto CALC