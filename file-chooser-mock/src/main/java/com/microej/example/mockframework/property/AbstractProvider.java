/*
 * Java
 *
 * Copyright 2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.property;

import java.util.LinkedList;
import java.util.Queue;

import com.microej.mock.property.IntegerProperty;

/**
 * Implementation for TemperatureProvider and SpeedProvider.
 */
public class AbstractProvider extends IntegerProperty {

	private final Queue<Integer> queue;

	/**
	 * The abstract provider constructor.
	 */
	public AbstractProvider() {
		super(Integer.valueOf(0));
		this.queue = new LinkedList<>();
	}

	@Override
	public void setValue(Integer newValue) {
		this.queue.add(newValue);

	}

	@Override
	public Integer getValue() {
		if (this.queue.isEmpty()) {
			return Integer.valueOf(0);
		}
		if (this.queue.size() > 1) {
			return this.queue.poll();
		}
		return this.queue.peek();
	}

	/**
	 * Clears the queue.
	 */
	public synchronized void clear() {
		this.queue.clear();
	}
}
