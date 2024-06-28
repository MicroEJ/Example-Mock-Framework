/*
 * Java
 *
 * Copyright 2021-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.property;

import java.util.LinkedList;
import java.util.Queue;

import com.microej.mock.annotation.Property;
import com.microej.mock.property.BooleanProperty;

/**
 * Status provider class responsible for Queueing the status values and send to the app.
 */
@Property(getter = "com.microej.example.mockframework.service.DataService.getStatus")
public class StatusProvider extends BooleanProperty {

	private final Queue<Boolean> queue;

	/**
	 * The Status provider constructor.
	 */
	public StatusProvider() {
		this.queue = new LinkedList<>();
	}

	@Override
	public Boolean getInitialValue() {
		return Boolean.FALSE;
	}

	@Override
	public Boolean getValue() {
		if (this.queue.isEmpty()) {
			return Boolean.FALSE;
		}
		if (this.queue.size() > 1) {
			return this.queue.poll();
		}
		return this.queue.peek();
	}

	@Override
	public void setValue(Boolean newValue) {
		this.queue.add(newValue);
	}

}