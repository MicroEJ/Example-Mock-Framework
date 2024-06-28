/*
 * Java
 *
 * Copyright 2021-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.widget;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.microej.mock.property.MockProperty;
import com.microej.mock.widget.PropertyWidget;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * A date and time picker {@link PropertyWidget}.
 */
public class DateTimePicker extends PropertyWidget<Long> {

	private static final int UPDATE_BUTTON_WIDTH = 60;

	private static final int MINUTES_SECONDS_MAX_VALUE = 59;

	private static final int HOUR_MIN_VALUE = 1;

	private static final int HOUR_MAX_VALUE = 12;

	private static final int PADDING_VALUE = 10;

	private static final int BOX_VALUE = 5;

	private static final String COLON_VALUE = ":";

	/**
	 * Constructor of DateTimePicker.
	 * <p>
	 * Creates an HBox which includes a DatePicker, three Spinners and a Button. The DatePicker is used to select the
	 * date. The first Spinner is used to select the time. The second Spinner is used to select the minutes. The third
	 * Spinner is used to select the seconds. The Button is used to set the property to the Unix time corresponding to
	 * the date and time (GMT).
	 *
	 * @param propertyClass
	 *            the extended property class for DateTimePicker.
	 */
	public DateTimePicker(Class<? extends MockProperty<Long>> propertyClass) {
		super(propertyClass);

		final javafx.scene.control.DatePicker customDatePicker = new javafx.scene.control.DatePicker(LocalDate.now());
		customDatePicker.setPrefWidth(this.textWidth(" @@/@@/@@@@ "));

		Label labelHour = new Label("Time(hh:mm:ss)");
		final double spinnerWidth = this.textWidth("@@ @@@");
		final javafx.scene.control.Spinner<Integer> hourSpinner = new javafx.scene.control.Spinner<>(HOUR_MIN_VALUE,
				HOUR_MAX_VALUE, HOUR_MIN_VALUE);
		hourSpinner.setPrefWidth(spinnerWidth);
		hourSpinner.setEditable(true);
		final javafx.scene.control.Spinner<Integer> minutesSpinner = new javafx.scene.control.Spinner<>(0,
				MINUTES_SECONDS_MAX_VALUE, 0);
		minutesSpinner.setPrefWidth(spinnerWidth);
		minutesSpinner.setEditable(true);
		final javafx.scene.control.Spinner<Integer> secondsSpinner = new javafx.scene.control.Spinner<>(0,
				MINUTES_SECONDS_MAX_VALUE, 0);
		secondsSpinner.setPrefWidth(spinnerWidth);
		secondsSpinner.setEditable(true);

		final javafx.scene.control.ComboBox<String> comboBox = new javafx.scene.control.ComboBox<>();
		comboBox.getItems().add("AM");
		comboBox.getItems().add("PM");
		comboBox.getSelectionModel().selectFirst();

		Button updateButton = new Button("Update");
		updateButton.setPrefWidth(UPDATE_BUTTON_WIDTH);
		updateButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int hour = hourSpinner.getValue().intValue();
				if (hour == 12) {
					hour = 0;
				}
				if (!(comboBox.getValue().equals("AM"))) {
					hour += 12;
				}
				hour = hour % 24;

				setUnixTime(customDatePicker.getValue(), hour, minutesSpinner.getValue().intValue(),
						secondsSpinner.getValue().intValue());
			}
		});

		HBox hbox = new HBox(BOX_VALUE);
		hbox.setPadding(new Insets(PADDING_VALUE, PADDING_VALUE, PADDING_VALUE, PADDING_VALUE));
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(customDatePicker, labelHour, hourSpinner, new Label(COLON_VALUE), minutesSpinner,
				new Label(COLON_VALUE), secondsSpinner, comboBox, updateButton);

		setContent(hbox);
	}

	/**
	 * Calculates the width of the text in pixels when displayed using the default font.
	 *
	 * @param text
	 *            the string to be measured in pixels
	 * @return the calculated width in pixels
	 */
	private double textWidth(String text) {
		return new Text(text).getBoundsInLocal().getWidth();
	}

	private void setUnixTime(LocalDate date, int hour, int minute, int second) {
		final long unixTime = LocalDateTime.of(date, LocalTime.of(hour, minute, second))
				.toEpochSecond(ZonedDateTime.now(ZoneId.of("GMT")).getOffset());

		DateTimePicker.this.setValue(Long.valueOf(unixTime));
	}
}
