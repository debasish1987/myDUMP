#
# Copyright (c) 2013, HiQES LLC
# ALL RIGHTS RESERVED
#
# http://www.hiqes.com
#
# This program is free software; you can redistribute it and/or modify
# it under the terms of the GNU General Public License version 2 as
# published by the Free Software Foundation.
#
#APP_OPTIM	:= release
#APP_ABI		:= armeabi-v7a

APP_ABI := armeabi-v7a
APP_STL := gnustl_static # to include C++11 features in Android

# these C++ exceptions are not default in minimalistic NDK (default in gnu)
APP_CPPFLAGS := -frtti -fexceptions  
APP_CPPFLAGS +:= -Wno-error=format-security

# to build only deblur module, and not fftw3_mod and all modules defined by Android.mk, eg all versions of camera modules
#APP_MODULES 	:= deblur
APP_PLATFORM := android-21


