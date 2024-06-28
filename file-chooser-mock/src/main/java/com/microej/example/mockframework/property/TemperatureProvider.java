/*
 * Java
 *
 * Copyright 2021-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.property;

import java.util.LinkedList;

import com.microej.mock.annotation.Property;

/**
 * Temperature provider class responsible for Queueing the speed values and send to the app.
 */
@Property(getter = "com.microej.example.mockframework.service.DataService.getTemperature")
public class TemperatureProvider extends AbstractProvider {

	/**
	 * The temperature provider constructor.
	 */

	public TemperatureProvider() {
		new LinkedList<>();
	}

}
