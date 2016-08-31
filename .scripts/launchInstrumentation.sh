#!/bin/sh
component=$1/$2
instrumentationResult=`adb shell "am instrument -w $component ; printf \"$?\""`
printf "$instrumentationResult\n"
exitCode=$(printf "$instrumentationResult" | tail -1)
failures=`printf "$instrumentationResult" | grep "Failures: 0"`
if [ ${exitCode} != "0" -o -z "${failures}" ]; then
    exit 1
fi