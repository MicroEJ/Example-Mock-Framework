/*
 * Java
 *
 * Copyright 2021-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.widget;

import com.microej.mock.widget.Container;
import com.microej.mock.widget.MockWidget;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * A Container which distributes its children evenly.
 */
public class EvenlyDistributedContainer extends Container {

	final boolean isHorizontal;

	/**
	 * Initializes the container with vertical orientation.
	 */
	public EvenlyDistributedContainer() {
		this(false);
	}

	/**
	 * Initializes the container with the orientation.
	 *
	 * @param horizontal
	 *            The Layout Orientation.
	 */
	public EvenlyDistributedContainer(boolean horizontal) {
		super(horizontal);
		this.isHorizontal = horizontal;
	}

	@Override
	public void addWidget(MockWidget widget) {
		Node node = widget.getNode();
		if (node instanceof Region) {
			if (this.isHorizontal) {
				((Region) node).setMaxWidth(Double.MAX_VALUE);
				HBox.setHgrow(node, Priority.ALWAYS);
			} else {
				((Region) node).setMaxHeight(Double.MAX_VALUE);
				VBox.setVgrow(node, Priority.ALWAYS);
			}
		}
		super.addWidget(widget);
	}
}
