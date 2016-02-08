package com.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sliderlib.R;

/**
 * Created by neokree on 31/12/14.
 */
public class FragmentHome extends Fragment{

	Button b1;
    Context context;
    Fragment currentFragment;
    
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    	View view = inflater.inflate(R.layout.my_button, container, false);
    	
        context = this.getActivity();
        currentFragment = this;
        b1= (Button)view.findViewById(R.id.b1);
		
		addListeners();
		
        return view;
    }
    


	
	public void addListeners() {
		b1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
//				  Fragment fragment = new TestFragment2();
//			        Bundle data = new Bundle();
//			        data.putString("Test","Banana");
//			        fragment.setArguments(data);
//			        ((MaterialNavigationDrawer)context).setFragmentChild(fragment,"Test page 2");
				
				Fragment fragment = new FragmentA();
			//	 ((MaterialNavigationDrawer)context).setFragmentChild(new FragmentA(),"Child Title");
				 
			//	 setFragment(Fragment fragment,String title,Fragment oldFragment,boolean hasSavedInstanceState);
			
				
//				 setFragment((Fragment) section.getTargetFragment(), section.getTitle(), (Fragment) currentSection.getTargetFragment());
//	              afterFragmentSetted((Fragment) section.getTargetFragment(),section.getTitle());

                 FragmentTransaction ft = getFragmentManager().beginTransaction();
                 ft.replace(R.id.frame_container, fragment);
                 ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                 ft.addToBackStack(null);
                 ft.commit();
			}
		});
		

	}
	
}
