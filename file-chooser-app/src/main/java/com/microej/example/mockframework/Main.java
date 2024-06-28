/*
 * Java
 *
 * Copyright 2021-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework;

import java.util.logging.Logger;

import com.microej.example.mockframework.service.DataService;

/**
 * Example application for the Mock Framework Library. Read and shows the
 * temperature and speed values from a csv file with specific read time.
 */
public class Main {
	private static final Logger LOG = Logger.getLogger(Main.class.getName());
	private static final long SLEEP_TIME = 1_000;
	private static final String COMMA = ", ";
	private static final StringBuilder dataValuesBuilder = new StringBuilder();

	private Main() {
		// private constructor
	}

	/**
	 * Application entry point.
	 *
	 * @param args ignored
	 */
	public static void main(String[] args) { // NOSONAR: allow unused method parameter
		run();
	}


	public static void run() {
		while (true) { // NOSONAR: allow infinite loop
			try {
				logData();
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}
	}

	protected static void logData() {
		if (DataService.getStatus()) {
			dataValuesBuilder.setLength(0);
			dataValuesBuilder.append(DataService.getSpeed()).append(COMMA).append(DataService.getTemperature());
			LOG.info(dataValuesBuilder.toString());
		}
	}

}
