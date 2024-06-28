/*
 * Java
 *
 * Copyright 2020-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework;

import com.microej.example.mockframework.service.RandomService;

import ej.annotation.Nullable;
import ej.microui.MicroUI;
import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.Displayable;
import ej.microui.display.Font;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Painter;
import ej.microui.event.generator.Buttons;
import ej.mwt.util.Alignment;

/**
 * Example application for the Mock Framework Library.
 */
public class Main {
	private Main() {
		// private constructor
	}

	/**
	 * Application entry point.
	 *
	 * @param args
	 *            ignored
	 */
	public static void main(String[] args) { // NOSONAR: allow unused method parameter
		MicroUI.start();
		Display.getDisplay().requestShow(new SimpleAppDisplayable());
	}

}
