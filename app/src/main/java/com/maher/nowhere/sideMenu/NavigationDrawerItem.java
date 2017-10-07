package com.maher.nowhere.sideMenu;

import java.util.ArrayList;
import java.util.List;

class NavigationDrawerItem {

	private String title;
	private String messageNumber;
	//private int imageId;

	public String getTitle() {
		return title;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	public String getMessageNumber() {
		return messageNumber;
	}

	public void setMessageNumber(String messageNumber) {
		this.messageNumber = messageNumber;
	}

	public static List<NavigationDrawerItem> getData() {
		List<NavigationDrawerItem> dataList = new ArrayList<>();

		//int[] imageIds = getImages();
		String[] titles = getTitles();

		for (int i = 0; i < titles.length; i++) {
			NavigationDrawerItem navItem = new NavigationDrawerItem();
			navItem.setTitle(titles[i]);
            if(i==2)
            navItem.setMessageNumber("2");
			//navItem.setImageId(imageIds[i]);
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

	private static String[] getTitles() {


		return new String[] {
				"Contacts", "Notifications", "Messages","Mon Compte", "Aide","Déconnexion"
		};
	}
}
