/*
 * Java
 *
 * Copyright 2021-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.property;

import com.microej.example.mockframework.widget.DateTimePicker;
import com.microej.mock.annotation.Property;
import com.microej.mock.property.LongProperty;

/**
 * Long property for the {@link DateTimePicker} widget.
 */
@Property(getter = "com.microej.example.mockframework.service.CustomWidgetsService.getUnixTimeValue")
public class UnixTimeValue extends LongProperty {

	private static final Long INITIAL_VALUE = Long.valueOf(0);

	/**
	 * Constructor of UnixTimeValue.
	 */
	public UnixTimeValue() {
		super(INITIAL_VALUE);
	}
}
