/*
 * Java
 *
 * Copyright 2021-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.widget;

import com.microej.mock.property.MockProperty;
import com.microej.mock.widget.PropertyWidget;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * A combobox {@link PropertyWidget}.
 */
public class ComboBox extends PropertyWidget<Integer> {

	private static final int PADDING_VALUE = 10;

	/**
	 * Constructor of ComboBox. Creates a drop down list of values.
	 *
	 * @param propertyClass
	 *            the extended property class for ComboBox.
	 * @param listValues
	 *            the integer list of values for ComboBox.
	 */
	public ComboBox(Class<? extends MockProperty<Integer>> propertyClass, int[] listValues) {
		super(propertyClass);

		javafx.scene.control.ComboBox<Integer> customCombo = new javafx.scene.control.ComboBox<>();
		for (int i = 0; i < listValues.length; i++) {
			customCombo.getItems().add(Integer.valueOf(listValues[i]));
		}
		customCombo.getSelectionModel().selectFirst();

		Label label = new Label("Select a number");
		HBox hbox = new HBox(PADDING_VALUE);

		hbox.setPadding(new Insets(PADDING_VALUE, PADDING_VALUE, PADDING_VALUE, PADDING_VALUE));
		hbox.getChildren().addAll(label, customCombo);
		setContent(hbox);

		this.getProperty().bind(customCombo.valueProperty());
	}
}
