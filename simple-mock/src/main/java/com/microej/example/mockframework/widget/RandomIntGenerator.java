/*
 * Java
 *
 * Copyright 2020 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.widget;

import com.microej.example.mockframework.property.RandomEnabled;
import com.microej.example.mockframework.property.RandomValue;
import com.microej.mock.MockupApplication;
import com.microej.mock.behavior.RandomIntegerUpdater;
import com.microej.mock.behavior.UpdateOnFixedPeriod;
import com.microej.mock.widget.IntegerBoundsSetter;
import com.microej.mock.widget.TitledWidget;

/**
 * Generates a random int number.
 */
public class RandomIntGenerator extends TitledWidget {

	private static final long UPDATE_PERIOD = 500;

	public RandomIntGenerator() {
		super("Random bounds", new IntegerBoundsSetter(RandomValue.class));

		final UpdateOnFixedPeriod<Integer> updater = new UpdateOnFixedPeriod<>(
				new RandomIntegerUpdater(RandomValue.class), UPDATE_PERIOD);

		final RandomEnabled enabled = MockupApplication.getProperty(RandomEnabled.class);
		updater.getEnableState().bind(enabled);
	}
}
