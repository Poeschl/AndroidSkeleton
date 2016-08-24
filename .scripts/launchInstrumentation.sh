#!/bin/sh
component=$1/$2
instrumentationResult=`adb shell "am instrument -w $component ; printf \"$?\""`
printf "$instrumentationResult\n"
exitCode=$(printf "$instrumentationResult" | tail -1)
if [ $exitCode != "0" ]; then
    exit 1
fi