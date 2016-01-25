Android Skeleton [![Build Status](https://travis-ci.org/Poeschl/AndroidSkeleton.svg?branch=master)](https://travis-ci.org/Poeschl/AndroidSkeleton)
================

This is the stub for my Android Apps. It will be updated with time (hopefully).

Included features:
------------------
+ Local keystore support (the keystore and the credentials are not checked in)
+ [checkstyle](http://checkstyle.sourceforge.net/) execution on release builds with own checkstyle config
+ git Hash and build time available via BuildConfig
+ [Dagger 2](https://github.com/google/dagger) basic setup
+ Debug Drawer from early [U2020 app](https://github.com/JakeWharton/u2020) of Jake Wharton (modified)
+ automatic travis build
+ Sample tests with [espresso](http://developer.android.com/training/testing/ui-testing/espresso-testing.html) for basic debug drawer
+ [LeakCanary](https://github.com/square/leakcanary) to track memory leaks in all activities
+ [Timber](https://github.com/JakeWharton/timber) to replace android logging
+ [ProcessPhoenix](https://github.com/JakeWharton/ProcessPhoenix) for a restart with a small footprint