/*
 * Java
 *
 * Copyright 2021-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework;

import com.microej.example.mockframework.service.CustomWidgetsService;

import ej.annotation.Nullable;
import ej.bon.Timer;
import ej.bon.TimerTask;
import ej.microui.MicroUI;
import ej.microui.display.Display;

/**
 * Example application for the Mock Framework Library.
 */
public class Main {

	private static final int TIMER_PERIOD = 100;
	private static @Nullable CustomWidgetDisplayable displayable;

	private Main() {
		// private constructor
	}

	/**
	 * Application entry point.
	 *
	 * @param args
	 *            ignored.
	 */
	public static void main(String[] args) { // NOSONAR: allow unused method parameter
		MicroUI.start();

		displayable = new CustomWidgetDisplayable();
		Display.getDisplay().requestShow(displayable);

		TimerTask timerTask = new TimerTask() {

			@Override
			public void run() {
				Main.displayable.updateValues(CustomWidgetsService.getSliderValue(),
						CustomWidgetsService.getComboValue(), CustomWidgetsService.getUnixTimeValue());
			}
		};
		Timer timer = new Timer();
		timer.schedule(timerTask, 0, TIMER_PERIOD);
	}
}
