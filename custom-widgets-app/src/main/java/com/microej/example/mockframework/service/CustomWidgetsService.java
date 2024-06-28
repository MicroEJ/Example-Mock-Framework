/*
 * Java
 *
 * Copyright 2021-2022 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.service;

/**
 * Provides communication between the application and the mock.
 */
public class CustomWidgetsService {

	private CustomWidgetsService() {
	}

	/**
	 * Gets the slider value.
	 *
	 * @return the value.
	 */
	public static native double getSliderValue();

	/**
	 * Gets the combo value.
	 *
	 * @return the value.
	 */
	public static native int getComboValue();

	/**
	 * Gets the Unix time value.
	 *
	 * @return the value.
	 */
	public static native long getUnixTimeValue();
}
