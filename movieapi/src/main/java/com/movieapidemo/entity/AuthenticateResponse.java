package com.movieapidemo.entity;

import java.io.Serializable;


public class AuthenticateResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3695631096932318815L;
	private String jwt;

	public AuthenticateResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

}
