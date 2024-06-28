/*
 * Java
 *
 * Copyright 2020-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.property;

import com.microej.mock.annotation.Property;
import com.microej.mock.property.IntegerProperty;

/**
 * Creates a span for number generation.
 */
@Property(getter = "com.microej.example.mockframework.service.RandomService.getInt")
public class RandomValue extends IntegerProperty {

	private static final Integer INITIAL_VALUE = Integer.valueOf(0);

	private static final Integer MIN_VALUE = Integer.valueOf(0);

	private static final Integer MAX_VALUE = Integer.valueOf(100);

	/**
	 * The constructor.
	 */
	public RandomValue() {
		super(INITIAL_VALUE, MIN_VALUE, MAX_VALUE);
	}

}
