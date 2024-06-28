/*
 * Java
 *
 * Copyright 2021-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.widget;

import com.microej.mock.property.MockProperty;
import com.microej.mock.widget.PropertyWidget;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 * A slider {@link PropertyWidget} with display of the selected value.
 */
public class Slider extends PropertyWidget<Double> {

	private static final int PADDING_VALUE = 8;

	private final javafx.scene.control.Slider customSlider;

	/**
	 * Constructor of Slider. Creates a Slider on which appears the selected value.
	 *
	 * @param propertyClass
	 *            the extended property class for Slider.
	 * @param min
	 *            The minimum value of slider
	 * @param max
	 *            The maximum value of slider
	 * @param value
	 *            The default value of slider
	 * @param thumbWidth
	 *            The preferred width of slider's thumb
	 * @param initialValue
	 *            The initial value of slider
	 */
	public Slider(final Class<? extends MockProperty<Double>> propertyClass, int min, int max, int value,
			final int thumbWidth, final int initialValue) {
		super(propertyClass);
		this.customSlider = new javafx.scene.control.Slider(min, max, value);
		this.customSlider.setShowTickLabels(true);
		this.customSlider.setShowTickMarks(true);
		this.customSlider.setValueChanging(true);

		HBox hbox = new HBox(1);
		hbox.setPadding(new Insets(PADDING_VALUE, PADDING_VALUE, PADDING_VALUE, PADDING_VALUE));
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(this.customSlider);
		HBox.setHgrow(this.customSlider, Priority.ALWAYS);
		setContent(hbox);

		this.customSlider.sceneProperty().addListener(createChangeListenerForScene(thumbWidth, initialValue));
	}

	private ChangeListener<Scene> createChangeListenerForScene(final int thumbWidth, final int initialValue) {
		return new ChangeListener<Scene>() {
			@Override
			public void changed(ObservableValue<? extends Scene> arg0, Scene arg1, Scene arg2) {
				final EventHandler<WindowEvent> windowEventHandler = new EventHandler<WindowEvent>() {
					@Override
					public void handle(WindowEvent arg0) {
						final Pane thumb = (Pane) Slider.this.customSlider.lookup(".thumb");
						final Label label = new Label();
						label.setPrefWidth(thumbWidth);
						label.setAlignment(Pos.CENTER);
						label.textProperty().bind(Bindings.format("%.2f", Slider.this.customSlider.valueProperty()));
						thumb.getChildren().add(label);
						Slider.this.getProperty().bind(Slider.this.customSlider.valueProperty().asObject());
						Slider.this.customSlider.valueProperty().setValue(Integer.valueOf(initialValue));
					}
				};
				checkScene(windowEventHandler, arg2);
			}
		};
	}

	private void checkScene(final EventHandler<WindowEvent> windowEventHandler, Scene arg2) {
		if (arg2.getWindow() != null) {
			arg2.getWindow().addEventHandler(WindowEvent.WINDOW_SHOWN, windowEventHandler);
		} else {
			arg2.windowProperty().addListener(new ChangeListener<Window>() {

				@Override
				public void changed(ObservableValue<? extends Window> observable, Window oldValue, Window newValue) {
					newValue.addEventHandler(WindowEvent.WINDOW_SHOWN, windowEventHandler);
				}
			});
		}
	}
}
