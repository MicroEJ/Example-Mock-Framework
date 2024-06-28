/*
 * Java
 *
 * Copyright 2021-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.property;

import java.io.File;

import com.microej.mock.annotation.Property;
import com.microej.mock.property.FileProperty;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * File provider class responsible for receiving the .csv file and feeding into the Temperature and Speed providers.
 */
@Property()
public class FileProvider extends FileProperty {

	private javafx.beans.property.Property<String> stringProperty;

	/**
	 * Creates a new FileProvider to send the file data to the Speed and Temperature providers.
	 */
	public FileProvider() {
		super(null);
		this.addValueListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				setValue(newValue);
			}
		});
	}

	/**
	 * Binds a String property to the File property.
	 *
	 * @param property
	 *            the String property.
	 */
	public void bindString(javafx.beans.property.Property<? super String> property) {
		property.bind(this.stringProperty);
	}

	@Override
	protected javafx.beans.property.Property<? super File> newProperty(File value) {
		javafx.beans.property.Property<? super File> myProperty = new SimpleObjectProperty<>(value);
		this.stringProperty = new SimpleStringProperty(value, "name", "not loaded");
		return myProperty;
	}

}
