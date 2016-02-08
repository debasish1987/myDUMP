package com.semco.ipo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class JavaActivity extends Activity {

	ImageView imageViewSrc,imageViewRes;
    ProgressDialog progressDialog;
    Context mContext;
    
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
	   
	    
		//imageViewRes.setImageBitmap(doGreyscale(bitmapSrc));
		mContext = this;
		showProgressDialog(this);
		new GreyScaleTask().execute(bitmapSrc);
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


	public static Bitmap doGreyscale(Bitmap src) {
	    // constant factors
	    final double GS_RED = 0.299;
	    final double GS_GREEN = 0.587;
	    final double GS_BLUE = 0.114;
	 
	    // create output bitmap
	    Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
	    // pixel information
	    int A, R, G, B;
	    int pixel;
	 
	    // get image size
	    int width = src.getWidth();
	    int height = src.getHeight();
	 
	    // scan through every single pixel
	    for(int x = 0; x < width; ++x) {
	        for(int y = 0; y < height; ++y) {
	            // get one pixel color
	            pixel = src.getPixel(x, y);
	            // retrieve color of all channels
	            A = Color.alpha(pixel);
	            R = Color.red(pixel);
	            G = Color.green(pixel);
	            B = Color.blue(pixel);
	            // take conversion up to one single value
	            R = G = B = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);
	            // set new pixel color to output bitmap
	            bmOut.setPixel(x, y, Color.argb(A, R, G, B));
	        }
	    }
	 
	    // return final image
	    return bmOut;
	}


}
