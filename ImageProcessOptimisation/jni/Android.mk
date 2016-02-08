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
LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

OPENCV_CAMERA_MODULES:=off
OPENCV_INSTALL_MODULES:=on
OPENCV_LIB_TYPE:=SHARED
include E:/Softwares/Libraries/OpenCV-android-sdk/sdk/native/jni/OpenCV.mk

LOCAL_MODULE	:= ipo_native
LOCAL_SRC_FILES	:= NativeCode.cpp

LOCAL_CFLAGS    := -Werror
LOCAL_LDLIBS	:= -llog
LOCAL_LDLIBS += -L$(SYSROOT)/usr/lib

include $(BUILD_SHARED_LIBRARY)





