package com.demo;

import android.graphics.Color;
import android.os.Bundle;

import com.example.sliderlib.MaterialNavigationDrawer;
import com.example.sliderlib.R;

@SuppressWarnings("rawtypes")
public class MESslider extends MaterialNavigationDrawer{

	@SuppressWarnings("unchecked")
	@Override
	public void init(Bundle savedInstanceState) {
		
		   // set header data
        setDrawerHeaderImage(R.drawable.mat2);
        setUsername("My App Name");
        setUserEmail("My version build");
      //  setFirstAccountPhoto(getResources().getDrawable(R.drawable.photo_icon));

        
		  // create and set the header
//        View view = LayoutInflater.from(this).inflate(R.layout.custom_drawer,null);
//        setDrawerHeaderCustom(view);

        // create sections
        this.addSection(newSection("Home", R.drawable.ic_launcher,new FragmentHome()).setSectionColor(Color.parseColor("#9c27b0")));
        this.addSection(newSection("Security_Policy", R.drawable.ic_launcher,new FragmentButton()).setSectionColor(Color.parseColor("#03a9f4")));
        this.addSection(newSection("Change_Password", R.drawable.ic_launcher, new FragmentButton()).setSectionColor(Color.parseColor("#9c27b0")));
        this.addSection(newSection("Logout", R.drawable.ic_launcher,new FragmentButton()).setSectionColor(Color.parseColor("#03a9f4")));
        
     // create bottom section
      // this.addBottomSection(newSection("Bottom Section",R.drawable.ic_settings_black_24dp,new Intent(this,Settings.class)));
        //For  Click Disabled
     //   this.addBottomSection(newSection(getResources().getString(R.string.copyright),R.drawable.copyright_logo,false,new FragmentButton()));
	
        if(isDrawerOpen()){
        	closeDrawer();
        }
	
	}


}
