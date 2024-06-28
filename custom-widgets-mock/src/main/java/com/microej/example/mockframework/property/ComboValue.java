/*
 * Java
 *
 * Copyright 2021-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.property;

import com.microej.mock.annotation.Property;
import com.microej.mock.property.IntegerProperty;

/**
 * Integer property for the ComboBox widget.
 */
@Property(getter = "com.microej.example.mockframework.service.CustomWidgetsService.getComboValue")
public class ComboValue extends IntegerProperty {

	private static final Integer INITIAL_VALUE = Integer.valueOf(0);

	/**
	 * Constructor of ComboValue.
	 */

	public ComboValue() {
		super(INITIAL_VALUE);
	}
}
