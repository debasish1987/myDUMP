package com.example.myviewpager;

//import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;

import com.test2.R;

public class ScreenSlideActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;
    private PagerTitleStrip mTitleStrip;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
        
        mTitleStrip = (PagerTitleStrip) findViewById(R.id.pager_title_strip);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
      
        //This line for Animation of Zoom-out Effect
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        
        //This line for Animation of Depth Effect
     //   mPager.setPageTransformer(true, new DepthPageTransformer());
        
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
     	private final String[] names = {"Open[]","In-Progress[]","Feedback[]","Closed[]","Cancelled[]"};
   	 
     	
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
          //  return new ScreenSlidePageFragment();
            switch(position) {

            case 0: return ScreenSlidePageFragment.newInstance("FirstFragment, Instance 1",R.layout.fragment_screen_slide_page);
            case 1: return ScreenSlidePageFragment.newInstance("SecondFragment, Instance 1",R.layout.fragment_screen_slide_page);
            case 2: return ScreenSlidePageFragment.newInstance("ThirdFragment, Instance 1",R.layout.fragment_2);
            case 3: return ScreenSlidePageFragment.newInstance("ThirdFragment, Instance 2",R.layout.fragment_screen_slide_page);
            case 4: return ScreenSlidePageFragment.newInstance("ThirdFragment, Instance 3",R.layout.fragment_screen_slide_page);
            default: return ScreenSlidePageFragment.newInstance("ThirdFragment, Default",R.layout.fragment_2);
            }
        }
        
        @Override
        public CharSequence getPageTitle(int position) {
        	// TODO Auto-generated method stub
        	return names[position];
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}