package com.semco.ipo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class ThreadActivity extends ActionBarActivity {

	ImageView imageViewSrc, imageViewRes;
    ProgressDialog progressDialog;
    Context mContext;
    
	//____________________
    private static long            THREAD_KEEP_ALIVE_TIME_MS = 1000;
    private ThreadPoolExecutor     mThreadPool;
    private int                    mDoneLines;
    private Semaphore              mDoneSem = new Semaphore(1);

    
	private void showProgressDialog(Context context) {
		progressDialog= new ProgressDialog(context);
		progressDialog.setMessage("Image Processing.....");
		progressDialog.show();
	}
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		imageViewSrc = (ImageView)findViewById(R.id.imageViewSrc);
		imageViewRes = (ImageView)findViewById(R.id.imageViewRes);
		
		//imageViewSrc.setBackgroundResource(R.drawable.lenna);
		
		Bitmap bitmapSrc = BitmapFactory.decodeResource(getResources(), R.drawable.lenna);
		int width = bitmapSrc.getWidth();
		int height = bitmapSrc.getHeight();
		imageViewSrc.setMinimumWidth(width);
		imageViewSrc.setMinimumHeight(height);
		imageViewSrc.setImageBitmap(bitmapSrc);
		
		mContext = this;
		showProgressDialog(this);
		new GreyScaleTask().execute(bitmapSrc);

	    //  Create a thread pool for parallel operations based on the number
	    //  of available processors since the threads should not block on any
	    //  of their operations.
		  Runtime runtime = Runtime.getRuntime();
		    mThreadPool = new ThreadPoolExecutor(runtime.availableProcessors(),
		            Integer.MAX_VALUE,
		            THREAD_KEEP_ALIVE_TIME_MS,
		            TimeUnit.MILLISECONDS,
		            new LinkedBlockingQueue<Runnable>());
		    
		    mThreadPool.prestartAllCoreThreads();

		    //  Prep the semphore for blocking
		    mDoneSem.drainPermits();
	}
	
	@Override
	protected void finalize() throws Throwable {
		 mThreadPool.shutdownNow();
	        try {
	            super.finalize();
	        } catch (Throwable t) {
	            Log.e("@SEMP@", "Caught throwable on finalize: " + t.getMessage());
	        }
		super.finalize();
	}
	
	private class GreyScaleTask extends AsyncTask<Bitmap, String, String>{
	    long         mStartTime;
        long         mEndTime;
        
		@Override
		protected String doInBackground(Bitmap... params) {
		      mStartTime = SystemClock.elapsedRealtime();
		      
		      doMultiThreadGreyscale(params[0]);

              mEndTime = SystemClock.elapsedRealtime();
		
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			imageViewRes.setImageBitmap(bmOut);
			progressDialog.dismiss();
            Toast.makeText(mContext,("Generation done in " +Long.toString(mEndTime - mStartTime) + " ms"),
                    Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);
		}
		
	}

	private  Bitmap bmOut;
	private  int[] intArray;
	protected void startThread(final int width ,final int height, final Bitmap src) {

        boolean done = false;

        //Initialize the intArray with the same size as the number of pixels on the image  
        intArray = new int[bmOut.getWidth()*bmOut.getHeight()];  
        
        //  Queue each line to the thread pool then wait for them all to finish
        //Log.d("@SEMP@", "Creating line gen tasks");
        mDoneLines = 0;
        for (int y = 0; y < height; y++) {
        	final int rowIndex=y;
            //mThreadPool.execute(new MandelBrotLineGenTask(y, bitmap));
        	mThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					//Log.d("@SEMP@", "Inside RUN !!");
					
					   // constant factors
				    final double GS_RED = 0.299;
				    final double GS_GREEN = 0.587;
				    final double GS_BLUE = 0.114;
				    // pixel information
				    int A, R, G, B;
				    int pixel;
				    
					// scan through every single pixel
				    for(int x = 0; x < width; ++x) {
			      
				            // get one pixel color
				            pixel = src.getPixel(x, rowIndex);
				         // retrieve color of all channels
				            A = Color.alpha(pixel);
				            R = Color.red(pixel);
				            G = Color.green(pixel);
				            B = Color.blue(pixel);
				            
				            // take conversion up to one single value
				            R = G = B = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);

			             //  bmOut.setPixel(x, colIndex, Color.argb(A, R, G, B));
				    
				            intArray[x + width * rowIndex] =Color.argb(A, R, G, B);
				            
				    }
					
					synchronized(mContext) {
		                mDoneLines++;
		                mDoneSem.release();
		            }
					}
			});
        }

       // Log.d("@SEMP@", "Gen tasks spawed, wait for done");

        while (!done) {
            try {
                mDoneSem.acquire();
            } catch (InterruptedException e) {
                Log.e("@SEMP@",
                      "Interrupted while waiting for sem: " + e.getMessage());
                break;
            } 

            synchronized(this) {
                if (mDoneLines >= height) {
                    done = true;
		             // Initialize the bitmap, with the replaced color  
		             bmOut = Bitmap.createBitmap(intArray, src.getWidth(), src.getHeight(), src.getConfig());  
		             //bmOut.setPixels(intArray, 0, src.getWidth(), 0, 0, width, height);
                    continue;
                }
            }        
        }

	}
	

	public void doMultiThreadGreyscale(Bitmap src) {
	    // create output bitmap
	    bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
	    // get image size
	    int width = src.getWidth();
	    int height = src.getHeight();
	    
	    startThread(width,height, src);
	}
	

}
