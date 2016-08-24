#!/bin/sh
mkdir googleapi
cd googleapi
wget -nc https://github.com/opengapps/arm/releases/download/20160824/open_gapps-arm-7.0-pico-20160824.zip
unzip open_gapps-arm-7.0-pico-20160824.zip
adb remount
adb push GmsCore.apk /system/priv-app/
adb push GoogleServicesFramework.apk /system/priv-app/
adb push GoogleLoginService.apk /system/priv-app/
adb push Phonesky.apk /system/priv-app/
adb shell stop
adb shell start