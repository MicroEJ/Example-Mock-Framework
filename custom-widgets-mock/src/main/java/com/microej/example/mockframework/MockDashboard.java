/*
 * Java
 *
 * Copyright 2021-2022 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework;

import com.microej.example.mockframework.property.ComboValue;
import com.microej.example.mockframework.property.SliderValue;
import com.microej.example.mockframework.property.UnixTimeValue;
import com.microej.example.mockframework.widget.ComboBox;
import com.microej.example.mockframework.widget.DateTimePicker;
import com.microej.example.mockframework.widget.Slider;
import com.microej.mock.AbstractDashboard;
import com.microej.mock.annotation.DashBoard;
import com.microej.mock.widget.Container;
import com.microej.mock.widget.TitledWidget;

/**
 * Custom widgets example for the Mock Framework.
 */
@DashBoard(title = "Custom Widget Mock")
public class MockDashboard extends AbstractDashboard {

	private static final int INITIAL_VALUE = 40;

	private static final int MAX_VALUE = 100;

	private static final int SLIDER_WIDTH = 50;

	/**
	 * Constructor of MockDashBoard. Creates a list with 3 custom widgets. The first
	 * widget is a slider, the second is a drop down list. The third tab is a date
	 * and time picker.
	 */
	public MockDashboard() {
		Container rootContainer = new Container(false);
		int[] comboBoxValues = { 0, 1, 2, 3, 4 };
		TitledWidget slider = new TitledWidget("Slider",
				new Slider(SliderValue.class, 0, MAX_VALUE, 0, SLIDER_WIDTH, INITIAL_VALUE));
		TitledWidget list = new TitledWidget("List", new ComboBox(ComboValue.class, comboBoxValues));
		TitledWidget timePicker = new TitledWidget("Time Picker", new DateTimePicker(UnixTimeValue.class));
		rootContainer.addWidget(slider);
		rootContainer.addWidget(list);
		rootContainer.addWidget(timePicker);

		addWidget(rootContainer);
	}
}
