#include <string.h>
#include <cstdlib>
#include <jni.h>
#include <android/log.h>

#include "sample.c"

 extern "C" {
 JNIEXPORT jstring JNICALL Java_com_example_helloworldndk_MainActivity_addFromJNICPP(JNIEnv * env, jobject obj,jint a, jint b);
};

 JNIEXPORT jstring JNICALL Java_com_example_helloworldndk_MainActivity_addFromJNICPP(JNIEnv * env, jobject obj,jint a, jint b)
{
	 std::string s;
	 s = addCPP(a+b);
     return env->NewStringUTF(s.c_str());

}
