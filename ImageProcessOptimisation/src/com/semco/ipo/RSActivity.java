package com.semco.ipo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.widget.ImageView;
import android.widget.Toast;

public class RSActivity extends Activity {

	ImageView imageViewSrc,imageViewRes;
    ProgressDialog progressDialog;
    Context mContext;
    
    //FOR RS
    private RenderScript mRS;
	private Allocation mInAllocation;
	private Allocation mOutAllocation;
	private ScriptC_monochrome mScript; 
	
	protected void goMono(Bitmap bitmapRes) {
		mScript.invoke_filter();
		mOutAllocation.copyTo(bitmapRes);
	}

	private void createScript(Bitmap src) {
		mRS = RenderScript.create(mContext);

		mInAllocation = Allocation.createFromBitmap(mRS, src,
				Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
		mOutAllocation = Allocation.createTyped(mRS, mInAllocation.getType());

		mScript = new ScriptC_monochrome(mRS, getResources(), R.raw.monochrome);

		mScript.set_gIn(mInAllocation);
		mScript.set_gOut(mOutAllocation);
		mScript.set_gScript(mScript); 
		
	}
    

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
			goMono(bitmapRes);
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
		
		
		createScript(src);	
		
		
//		greyScaleJni(inputImgAddr, outputImgAddr);
//		Utils.matToBitmap(outputImgMat, bmOut);

	    // return final image
	    return bmOut;
	}
	
}
