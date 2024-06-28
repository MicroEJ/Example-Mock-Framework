/*
 * Java
 *
 * Copyright 2020-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.service;

/**
 * Provides communication between the application and the mock.
 */
public class DataService {

	private DataService() {
	}

	/**
	 * Gets the speed provided by the mock..
	 *
	 * @return The integer with the speed.
	 */
	public static native int getSpeed();

	/**
	 * Gets the temperature provided by the mock.
	 *
	 * @return The integer with the temperature.
	 */
	public static native int getTemperature();

	/**
	 * Gets the status provided by the mock.
	 *
	 * @return The boolean value of the status.
	 */
	public static native boolean getStatus();

}
