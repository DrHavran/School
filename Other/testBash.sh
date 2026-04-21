#!/bin/bash

function show(){
    cat $1
}

function errors(){
    grep -i "ERROR" $1 | sed 's/error/ERROR/gI'
    grep -c -i "ERROR" $1
}

function warns(){
    grep -i "WARN" $1 | sed 's/warn/WARN/gI'
    grep -c -i "WARN" $1
}

function help(){    
    echo "<function> <file> - possible functions: show, errors, warns, help"
}

$1 "$2"