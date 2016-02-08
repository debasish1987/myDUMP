package com.semco.ipo;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class JNIActivity extends Activity {

	ImageView imageViewSrc,imageViewRes;
    ProgressDialog progressDialog;
    Context mContext;
    
    static{
        //  Load our native shared library when we are first created
        System.loadLibrary("ipo_native");
    }
    public native int greyScaleJni(
						    		 long inputImgAddr,
						    		 long outputImgAddr
            				);
    
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
	   
		// FOR Open CV
		inputImgMat   = matFromBitmap(bitmapSrc);
		outputImgMat  = new Mat(height, width, ImageType);
		inputImgAddr  = inputImgMat.getNativeObjAddr();
		outputImgAddr = outputImgMat.getNativeObjAddr();
		
		
		//imageViewRes.setImageBitmap(doGreyscale(bitmapSrc));
		mContext = this;
		showProgressDialog(this);
		new GreyScaleTask().execute(bitmapSrc);
	}
	
	// FOR Open CV
	private Mat inputImgMat;
	private Mat outputImgMat;
	private long inputImgAddr  = 0;
	private long outputImgAddr = 0;
	private static int ImageType=24;
	
    private Mat matFromBitmap(Bitmap bm) {
    	Mat mat = new Mat();
    	Utils.bitmapToMat(bm, mat);
    	return mat;
    }
   
    
	private class GreyScaleTask extends AsyncTask<Bitmap, String, String>{
		Bitmap bitmapRes ;
	    long         mStartTime;
        long         mEndTime;
        
		@Override
		protected String doInBackground(Bitmap... params) {
			 mStartTime = SystemClock.elapsedRealtime();
			 bitmapRes = doGreyscale(params[0]);
             mEndTime = SystemClock.elapsedRealtime();
			
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			imageViewRes.setImageBitmap(bitmapRes);
			progressDialog.dismiss();
			 Toast.makeText(mContext,
	                    ("Generation done in " +
	                     Long.toString(mEndTime - mStartTime) +
	                     "ms"),
	                    Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);
		}
		
	}


	public Bitmap doGreyscale(Bitmap src) {
		Bitmap bmOut ;
		bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(),src.getConfig());
		// bitmapRes = doGreyscale(params[0]);
		greyScaleJni(inputImgAddr, outputImgAddr);
		Utils.matToBitmap(outputImgMat, bmOut);

	    // return final image
	    return bmOut;
	}
	
	private static String TAG = "Open_CV";
	private volatile boolean isOpenCVLoaded = false;
	
	private BaseLoaderCallback  mLoaderCallback = new BaseLoaderCallback(this) {
		@Override
		public void onManagerConnected(int status) {
			switch (status) {
			case LoaderCallbackInterface.SUCCESS:
			{
				System.loadLibrary("ipo_native");
				Log.i(TAG, "OpenCV loaded successfully");
				isOpenCVLoaded = true;

			} break;
			default:
			{
				super.onManagerConnected(status);
			} break;
			}
		}
	};
	
	@Override 
	protected void onResume() {
		super.onResume();
		mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
		 if (!OpenCVLoader.initDebug()) {
	            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
	            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
	        } else {
	            Log.d(TAG, "OpenCV library found inside package. Using it!");
	            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
	        }
	}

}
