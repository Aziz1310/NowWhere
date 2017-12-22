package com.maher.nowhere.SearchActivity.calander;

import java.util.ArrayList;
import java.util.List;

class CalanderItem {

	private int day;
	private String month;
	//private int imageId;


	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public static List<CalanderItem> getData() {
		List<CalanderItem> dataList = new ArrayList<>();

		//int[] imageIds = getImages();
		String[] titles = getDate();

		for (int i = 0; i < titles.length; i++) {
			CalanderItem navItem = new CalanderItem();
			navItem.setDay(23);
			navItem.setMonth(titles[i]);
			dataList.add(navItem);
		}
		return dataList;
	}

	/*
	private static int[] getImages() {

		return new int[]{
							R.drawable.ic_event_white_24dp,
							R.drawable.ic_business_black_24dp,
							R.drawable.ic_near_me_black_24dp,
							R.drawable.ic_email_black_24dp,
							R.drawable.background_small,
							R.drawable.background_small};
	}
	*/

	private static String[] getDate() {


		return new String[] {
				"SEP", "SEP", "SEP","SEP", "SEP","SEP",
				"SEP", "SEP", "SEP","SEP", "SEP","SEP",
				"SEP", "SEP", "SEP","SEP", "SEP","SEP"
		};
	}
}
