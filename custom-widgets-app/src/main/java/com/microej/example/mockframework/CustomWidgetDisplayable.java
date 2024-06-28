/*
 * Java
 *
 * Copyright 2022 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.util.Date;

import com.microej.example.mockframework.util.Images;

import ej.microui.display.Displayable;
import ej.microui.display.Font;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.display.Painter;

/**
 * Displays the Mock's data on the screen.
 */
public class CustomWidgetDisplayable extends Displayable {

	private static final int BACKGROUND_COLOR = 0x262a2c;
	private static final int FIRST_COMPONENT_Y = 15;
	private static final int TEXT_COLOR = 0xffffff;
	private static final int TEXT_PADDING = 8;
	private static final int VALUES_COLOR = 0xee512e;
	private static final int LINE_GAP = 13;

	private static final double DEFAULT_SLIDER_VALUE = 50;
	private static final int DEFAULT_COMBOBOX_VALUE = 0;
	private static final long DEFAULT_DATE_VALUE = 0;

	private double sliderValue = DEFAULT_SLIDER_VALUE;
	private int comboboxValue = DEFAULT_COMBOBOX_VALUE;
	private long dateValue = DEFAULT_DATE_VALUE;

	/**
	 * Update the displayed values.
	 *
	 * @param sliderValue
	 *            the new slider value.
	 * @param comboboxValue
	 *            the new combobox value.
	 * @param dateValue
	 *            the new date value.
	 */
	public void updateValues(double sliderValue, int comboboxValue, long dateValue) {
		this.sliderValue = sliderValue;
		this.comboboxValue = comboboxValue;
		this.dateValue = dateValue;
		this.requestRender();
	}

	@Override
	protected void render(GraphicsContext graphicsContext) {
		Font font = Font.getFont("/fonts/SourceSansPro_22px-400.ejf"); //$NON-NLS-1$
		Image banner = Images.getImage(Images.getImagePath(Images.MICROEJ_BANNER));
		Image cornerTop = Images.getImage(Images.getImagePath(Images.CORNER_TOP));
		Image cornerBottom = Images.getImage(Images.getImagePath(Images.CORNER_BOTTOM));
		int initialColor = graphicsContext.getColor();
		graphicsContext.setColor(BACKGROUND_COLOR);
		Painter.fillRectangle(graphicsContext, banner.getWidth(), 0,
				graphicsContext.getClipWidth() - banner.getWidth(), graphicsContext.getClipHeight());
		graphicsContext.setColor(initialColor);
		Painter.drawImage(graphicsContext, banner, 0, 0);
		Painter.drawImage(graphicsContext, cornerTop, banner.getWidth(), 0);
		Painter.drawImage(graphicsContext, cornerBottom, banner.getWidth(),
				graphicsContext.getClipHeight() - cornerBottom.getHeight());

		String sliderValueString = "Slider value: "; //$NON-NLS-1$
		BigDecimal bd = BigDecimal.valueOf(this.sliderValue).setScale(2, RoundingMode.HALF_UP);
		String sliderValue = bd.toString();
		int textHeight = font.getHeight();
		int currentDrawnHeight = FIRST_COMPONENT_Y;
		graphicsContext.setColor(TEXT_COLOR);
		Painter.drawString(graphicsContext, sliderValueString, font,
				banner.getWidth() + cornerBottom.getWidth() + TEXT_PADDING, currentDrawnHeight);
		graphicsContext.setColor(VALUES_COLOR);
		Painter.drawString(graphicsContext, sliderValue, font,
				banner.getWidth() + cornerBottom.getWidth() + TEXT_PADDING + font.stringWidth(sliderValueString),
				currentDrawnHeight);
		graphicsContext.setColor(TEXT_COLOR);
		currentDrawnHeight += textHeight + LINE_GAP;
		Painter.drawLine(graphicsContext, banner.getWidth() + cornerBottom.getWidth(), currentDrawnHeight,
				graphicsContext.getClipWidth() - 10, currentDrawnHeight);
		currentDrawnHeight += LINE_GAP;
		String listValueString = "List value: "; //$NON-NLS-1$
		String listValue = String.valueOf(this.comboboxValue);
		Painter.drawString(graphicsContext, listValueString, font,
				banner.getWidth() + cornerBottom.getWidth() + TEXT_PADDING, currentDrawnHeight);
		graphicsContext.setColor(VALUES_COLOR);
		Painter.drawString(graphicsContext, listValue, font,
				banner.getWidth() + cornerBottom.getWidth() + TEXT_PADDING + font.stringWidth(listValueString),
				currentDrawnHeight);
		graphicsContext.setColor(TEXT_COLOR);
		currentDrawnHeight += textHeight + LINE_GAP;

		Painter.drawLine(graphicsContext, banner.getWidth() + cornerBottom.getWidth(), currentDrawnHeight,
				graphicsContext.getClipWidth() - 10, currentDrawnHeight);
		currentDrawnHeight += LINE_GAP;
		String timePickerValueString = "Time Picker value: "; //$NON-NLS-1$
		String timePickerValue = dateToDisplay(this.dateValue);
		Painter.drawString(graphicsContext, timePickerValueString, font,
				banner.getWidth() + cornerBottom.getWidth() + TEXT_PADDING, currentDrawnHeight);
		graphicsContext.setColor(VALUES_COLOR);
		Painter.drawString(graphicsContext, timePickerValue, font,
				banner.getWidth() + cornerBottom.getWidth() + TEXT_PADDING + font.stringWidth(timePickerValueString),
				currentDrawnHeight);
		graphicsContext.setColor(TEXT_COLOR);
		currentDrawnHeight += textHeight + LINE_GAP;
		Painter.drawLine(graphicsContext, banner.getWidth() + cornerBottom.getWidth(), currentDrawnHeight,
				graphicsContext.getClipWidth() - 10, currentDrawnHeight);
	}

	@Override
	public boolean handleEvent(int event) {
		return false;
	}

	private String dateToDisplay(long time) {
		Date date = new Date(time * 1000);
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

		return dateFormat.format(date);
	}

}
