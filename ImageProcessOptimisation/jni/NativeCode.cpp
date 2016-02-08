/*
 * Copyright (c) 2013, HiQES LLC
 * ALL RIGHTS RESERVED
 *
 * http://www.hiqes.com
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 */
#include <jni.h>
#include <pthread.h>
#include <android/log.h>
#include <sys/queue.h>

//#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>
#include <stdio.h>
#include <stdlib.h>


#define LOG_TAG            "*MandelbrotNativeGen"

#define LOGE(fmt, ...)     __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, (fmt), ##__VA_ARGS__)
#define LOGW(fmt, ...)     __android_log_print(ANDROID_LOG_WARN, LOG_TAG, (fmt), ##__VA_ARGS__)
#define LOGI(fmt, ...)     __android_log_print(ANDROID_LOG_INFO, LOG_TAG, (fmt), ##__VA_ARGS__)
#define LOGD(fmt, ...)     __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, (fmt), ##__VA_ARGS__)

extern "C" {
    JNIEXPORT jint JNICALL
	Java_com_semco_ipo_JNIActivity_greyScaleJni(JNIEnv *env,jobject obj,
															  jlong inputImgAddr,
															  jlong outputImgAddr
	                                                       );

    JNIEXPORT void JNICALL
	Java_com_semco_ipo_JniThreadActivity_greyScaleThreadJni(JNIEnv *env,jobject obj,
															  jlong inputImgAddr,
															  jlong outputImgAddr
	                                                       );

    void *myThreadFun(void *vargp);


};

typedef struct GenWorkObj {
    int                         y;
    TAILQ_ENTRY(GenWorkObj)     node;
} GenWork;

cv::Mat* m_pclSource;
cv::Mat* m_pclDest;

JNIEXPORT jint JNICALL
Java_com_semco_ipo_JNIActivity_greyScaleJni(JNIEnv *env,jobject obj,
														  jlong inputImgAddr,
														  jlong outputImgAddr
                                                       ) {
	int input_width, input_height;

	m_pclSource=(cv::Mat*)inputImgAddr;
	m_pclDest=(cv::Mat*)outputImgAddr;

    // constant factors
    double GS_RED = 0.299;
    double GS_GREEN = 0.587;
    double GS_BLUE = 0.114;

	//m_pclSource->data
	input_width = m_pclSource->cols;
	input_height = m_pclSource->rows;

	unsigned char *input = (unsigned char*)(m_pclSource->data);
	unsigned char *output= (unsigned char*)(m_pclDest->data);
//	LOGE("input width * height = %d * %d",m_pclSource->cols, m_pclSource->rows);
//	LOGE("output width * height = %d * %d",m_pclDest->cols, m_pclDest->rows);
//
//	int length= input_width*input_height*4;
//	//memcpy(output,input,length);

	for(int i = 0;i < input_height;++i){
	    for(int j = 0;j < input_width;++j){

	    	//RGBA
	    	unsigned char r = input[i*input_width*4 + 4*j];
	        unsigned char g = input[i*input_width*4 + 4*j+1];
	        unsigned char b = input[i*input_width*4 + 4*j+2];
	        unsigned char a = input[i*input_width*4 + 4*j+3];

	        output[i*input_width*4+4*j]   = (GS_RED * r + GS_GREEN * g + GS_BLUE * b);
	        output[i*input_width*4+4*j+1] = (GS_RED * r + GS_GREEN * g + GS_BLUE * b);
	        output[i*input_width*4+4*j+2] = (GS_RED * r + GS_GREEN * g + GS_BLUE * b);
	        output[i*input_width*4+4*j+3] = input[i*input_width*4 + 4*j+3];

//	    	output[i*input_width*4+4*j+3] = input[i*input_width*4 + 4*j+3];
//	        output[i*input_width*4+4*j+2] = input[i*input_width*4 + 4*j+2];
//	        output[i*input_width*4+4*j+1] = input[i*input_width*4 + 4*j+1];
//	        output[i*input_width*4+4*j]   = input[i*input_width*4 + 4*j];

	    }
	}


	 return 0;
}

JNIEXPORT void JNICALL
Java_com_semco_ipo_JniThreadActivity_greyScaleThreadJni(JNIEnv *env, jobject obj,
		                                           jlong inputImgAddr,
												   jlong outputImgAddr
												   ) {
	pthread_t tid[10];
	//MEM Alloc
	for (int i = 0; i < 10; ++i) {
		pthread_create(&tid[i], NULL, myThreadFun, (void *) i);
	}






	 //pthread_exit(NULL);

}


// A normal C function that is executed as a thread when its name
// is specified in pthread_create()
// Let us create a global variable to change it in threads
int g = 0;

// The function to be executed by all threads
void *myThreadFun(void *vargp)
{
    // Store the value argument passed to this thread
    int myid = (int)vargp;

    // Let us create a static variable to observe its changes
    static int s = 0;

    // Change static and global variables
    ++s; ++g;

    // Print the argument, static and global variables
    LOGE("Thread ID: %d, Static: %d, Global: %d\n", myid, ++s, ++g);

}

