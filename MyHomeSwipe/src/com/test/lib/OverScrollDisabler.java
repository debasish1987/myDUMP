package com.test.lib;

import android.view.View;

public class OverScrollDisabler
{
	public static void disableOverScroll(View view)
	{
		view.setOverScrollMode(View.OVER_SCROLL_NEVER);
	}
}