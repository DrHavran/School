@echo off
setlocal EnableDelayedExpansion

set /A count=1

for %%a in (a b c d e f g h) do (
    set /A loops=count*32

    for /L %%n in (1,1,!loops!) do (
        echo %%a >> %%a.txt
    )

    dir %%a.txt | findstr File >> sizes.txt

    ren %%a.txt !loops!x%%a.txt

    set /A count+=1
)

pause

set /A count=1

for %%a in (a b c d e f g h) do (
    set /A loops=count*32

    del !loops!x%%a.txt

    set /A count+=1
)
del sizes.txt