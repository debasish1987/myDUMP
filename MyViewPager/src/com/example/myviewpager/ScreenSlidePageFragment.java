package com.example.myviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScreenSlidePageFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
//		View rootView = (View) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
//
//		TextView tv = (TextView) rootView.findViewById(R.id.content);
//		tv.setText(getArguments().getString("msg"));
		
		
        int layout = getArguments().getInt("layoutId");
        View rootView = (View) inflater.inflate(layout, container, false);

		return rootView;
	}

	public static ScreenSlidePageFragment newInstance(String text, int layoutId) {

		ScreenSlidePageFragment f = new ScreenSlidePageFragment();
		Bundle b = new Bundle();
		b.putString("msg", text);
		
		b.putInt("layoutId", layoutId);

		f.setArguments(b);

		return f;
	}
}