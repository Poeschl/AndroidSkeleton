#!/bin/sh
echo no | android create avd --force -n test -t $1 --abi $2
sed -i "/^hw.ramSize/s/=.*/=1536/" ~/.android/avd/test.avd/config.ini

emulator -avd test -no-skin -no-audio -no-window -gpu off -data ~/.android/avd/test.avd/userdata-qemu.im/userdata.img

