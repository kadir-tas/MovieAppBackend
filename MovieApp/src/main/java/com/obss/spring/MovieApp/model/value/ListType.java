package com.obss.spring.MovieApp.model.value;

public enum ListType {

	WATCHED("WatchedList"),

	FAVORITE("FavoriteList");

	private final String listType;

	private ListType(String listType) {
		this.listType = listType;
	}

	public String getListType() {
		return this.listType;
	}
}
