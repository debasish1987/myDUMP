LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_LDLIBS := -llog

LOCAL_MODULE    := compJNI
LOCAL_SRC_FILES += AddJNI.cpp 

include $(BUILD_SHARED_LIBRARY)

 
 
