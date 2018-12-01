package com.example.demo.model;

import java.io.Serializable;

public class Test implements Serializable{

	private String me;

	public Test(String me) { 
		this.me = me;
	}

	public String getMe() {
		return me;
	}

	public void setMe(String me) {
		this.me = me;
	}

}
