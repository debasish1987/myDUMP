/*
 * Copyright (c) Samsung Electro-Mechanics Co., Ltd
 * 2001...2022.The following source code is strictly confidential and is proprietary to
 * Samsung Electro - Mechanics Co., Ltd, India Software Centre. It may only be read, used,
 * copied, adapted, modified or otherwise partially or in full dealt with by you if you have
 * entered into a confidentiality agreement with Samsung Electro � Mechanics Co., Ltd, India
 * Software Centre. If you have not entered into a confidentiality agreement granting access to
 * this Software you should forthwith return all media, copies and printed listings containing
 * the code to Samsung Electro � Mechanics Co., Ltd, India Software Centre.
 */
package com.semco.cctv;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * @file        : VerticalTextView.java 
 * @description : Custom textview for vertical printing
 * @author      : Debasish Pradhan 
 * @email       : debasish.pradhan@samsung.com
 * @project     : CCTV 
 * @moduleName  : GUI File 
 * @modifiedBy  : debasish.pradhan@samsung.com 
 * @creationDate: 08-Feb-2016
 * @modifiedDate: 08-Feb-2016
 */
public class VerticalTextView extends TextView {

	final boolean topDown;

	public VerticalTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		final int gravity = getGravity();
		if (Gravity.isVertical(gravity)	&& (gravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.BOTTOM) {
			
			setGravity((gravity & Gravity.HORIZONTAL_GRAVITY_MASK) | Gravity.TOP);
			topDown = false;
		} else {
			topDown = true;
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(heightMeasureSpec, widthMeasureSpec);
		setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		TextPaint textPaint = getPaint();
		textPaint.setColor(getCurrentTextColor());
		textPaint.drawableState = getDrawableState();

		canvas.save();

		if (topDown) {
			canvas.translate(getWidth(), 0);
			canvas.rotate(90);
		} else {
			canvas.translate(0, getHeight());
			canvas.rotate(-90);
		}

		canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());

		getLayout().draw(canvas);
		canvas.restore();
	}
}
