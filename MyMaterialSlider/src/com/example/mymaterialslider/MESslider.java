package com.example.mymaterialslider;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.mymaterialslider.R;
import com.example.mymaterialslider.R.drawable;
import com.example.mymaterialslider.R.layout;
import com.example.sliderlib.MaterialNavigationDrawer;

/**
 * Created by neokree on 18/01/15.
 */
public class MESslider extends MaterialNavigationDrawer {
    @Override
    public void init(Bundle savedInstanceState) {

    	// set header data
     //   setDrawerHeaderImage(R.drawable.mat2);
       // setUsername("My App Name");
       // setUserEmail("My version build");
     //  setFirstAccountPhoto(getResources().getDrawable(R.drawable.photo_icon));
        
        // create and set the header
        View view = LayoutInflater.from(this).inflate(R.layout.custom_drawer,null);
        setDrawerHeaderCustom(view);

        // create sections
//        this.addSection(newSection("Section 1", new FragmentIndex()));
//        this.addSection(newSection("Section 2",new FragmentIndex()));
        this.addSection(newSection("Section 1", R.drawable.ic_mic_white_24dp,new FragmentButton()).setSectionColor(Color.parseColor("#9c27b0")));
        this.addSection(newSection("Section 2",R.drawable.ic_hotel_grey600_24dp,new FragmentButton()).setSectionColor(Color.parseColor("#03a9f4")));
        this.addSection(newSection("Section 3",R.drawable.ic_hotel_grey600_24dp,new FragmentButton()));

        // create bottom section
      //  this.addBottomSection(newSection("Bottom Section",R.drawable.ic_settings_black_24dp,new Intent(this,Settings.class)));

        
        // create bottom section
         // this.addBottomSection(newSection("Bottom Section",R.drawable.ic_settings_black_24dp,new Intent(this,Settings.class)));
           //For  Click Disabled
        //   this.addBottomSection(newSection(getResources().getString(R.string.copyright),R.drawable.copyright_logo,false,new FragmentButton()));
   	
//           if(isDrawerOpen()){
//           	closeDrawer();
//           }
    }
}
