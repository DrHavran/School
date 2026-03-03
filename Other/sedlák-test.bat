@echo off
setlocal EnableDelayedExpansion

set name=%1

:LOOP

set /P mode=What mode do you want? 

if %mode%==add (
    echo -----------------
    set /P task=Task? 
    set /P dateInput=Date? 
    echo -----------------
    echo Task added
    echo -----------------
    
    echo !task!,!dateInput! >> tasks_%name%.txt
)

if %mode%==read (
    echo -----------------
    echo User: %name%

    set /A count=0
    for /F %%n in (tasks_%name%.txt) do (
        set /A count=1+count
    )

    echo Count: !count!
    echo -----------------
    type tasks_%name%.txt
    echo -----------------
)

goto LOOP