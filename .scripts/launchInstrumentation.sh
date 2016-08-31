#!/bin/sh
component=$1/$2
instrumentationResult=`adb shell "am instrument -w $component ; printf \"$?\""`
printf "$instrumentationResult\n"
exitCode=$(printf "$instrumentationResult" | tail -1)
failures=`printf "$instrumentationResult" | grep "FAILURES!!!"`
if [ ${exitCode} != "0" ]; then
  exit 1
elif [ -n "${failures}" ]; then
  exit 2
fi