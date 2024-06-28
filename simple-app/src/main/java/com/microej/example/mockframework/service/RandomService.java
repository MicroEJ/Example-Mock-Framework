/*
 * Java
 *
 * Copyright 2020-2021 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.service;

/**
 * Provides communication between the application and the mock.
 */
public class RandomService {

	private RandomService() {
	}

	/**
	 * Gets gets a random integer from the mock.
	 *
	 * @return a random integer from the mock.
	 */
	public static native int getInt();

	/**
	 * Gets the service state.
	 *
	 * @return true if the service is enabled, false otherwise.
	 */
	public static native boolean getEnable();

	/**
	 * Sets the service state.
	 *
	 * @param enable
	 *            enables or disables the service.
	 */
	public static native void setEnable(boolean enable);

	/**
	 * Enables and disables the check button.
	 */
	public static void switchEnable() {
		final boolean status = getEnable();
		setEnable(!status);
	}

}
