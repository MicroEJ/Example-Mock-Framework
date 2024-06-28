/*
 * Java
 *
 * Copyright 2021-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.property;

import com.microej.mock.annotation.Property;
import com.microej.mock.property.DoubleProperty;

/**
 * Double property for the Slider widget.
 */
@Property(getter = "com.microej.example.mockframework.service.CustomWidgetsService.getSliderValue")
public class SliderValue extends DoubleProperty {

	private static final Double INITIAL_VALUE = Double.valueOf(50);

	private static final Double MIN_VALUE = Double.valueOf(0);

	private static final Double MAX_VALUE = Double.valueOf(100);

	/**
	 * Constructor of SliderValue.
	 *
	 */
	public SliderValue() {
		super(INITIAL_VALUE, MIN_VALUE, MAX_VALUE);
	}
}
