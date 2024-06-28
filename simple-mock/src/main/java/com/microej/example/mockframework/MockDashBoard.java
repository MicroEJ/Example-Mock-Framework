/*
 * Java
 *
 * Copyright 2020 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework;

import com.microej.example.mockframework.property.RandomEnabled;
import com.microej.example.mockframework.widget.RandomIntGenerator;
import com.microej.mock.AbstractDashboard;
import com.microej.mock.annotation.DashBoard;
import com.microej.mock.widget.CheckBox;
import com.microej.mock.widget.TitledWidget;

/**
 * Application entry point.
 */
@DashBoard(title = "Simple Mock")
public class MockDashBoard extends AbstractDashboard {

	public MockDashBoard() {
		addWidget(new RandomIntGenerator());
		addWidget(new TitledWidget("Random Generator Status", new CheckBox("Enabled", RandomEnabled.class)));
	}
}
