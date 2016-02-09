package com.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TheMain {
	
	private int year, month, day, startYear, startMonth, startDay, endYear,
	endMonth, endDay, finishYear, finishMonth, finishDay;
	public static void main(String[] args) {
		  String mSrApprovedDateTime="20150521052444";
		  
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");

		try {

		    String reformattedStr = myFormat.format(simpleDateFormat.parse(mSrApprovedDateTime));
		    mSrApprovedDateTime = reformattedStr;
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		System.out.println(mSrApprovedDateTime);
//		TheMain tm= new TheMain();
//		int startYear  = 2015;
//		int startMonth = 5;
//		int startDay   = 20;
//		int endYear    = 2016;
//		int endMonth   = 5;
//		int endDay     =20;
//		System.out.println(tm.checkDate(startYear, startMonth, startDay, endYear, endMonth, endDay));
		
	}

	public boolean checkDate(int startYear, int startMonth, int startDay, int endYear,int endMonth, int endDay) {
		boolean flag = false;

		if (startYear < endYear) {
			flag = true;
		} else if (startYear == endYear) {
			if (startMonth < endMonth) {
				flag = true;
			} else if (startMonth == endMonth) {
				if (startDay <= endDay) {
					flag = true;
				} else {
					flag = false;
				}
			} else {
				flag = false;
			}
		} else
			flag = false;
		return flag;
	}
}
