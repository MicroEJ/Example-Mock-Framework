/*
 * Java
 *
 * Copyright 2020-2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;

import com.microej.example.mockframework.property.FileProvider;
import com.microej.example.mockframework.property.SpeedProvider;
import com.microej.example.mockframework.property.StatusProvider;
import com.microej.example.mockframework.property.TemperatureProvider;
import com.microej.example.mockframework.widget.EvenlyDistributedContainer;
import com.microej.mock.AbstractDashboard;
import com.microej.mock.MockupApplication;
import com.microej.mock.annotation.DashBoard;
import com.microej.mock.widget.Container;
import com.microej.mock.widget.FileChooser;
import com.microej.mock.widget.JavaFxWidget;
import com.microej.mock.widget.TitledWidget;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * Application for the main mock dashboard.
 */
@DashBoard(title = "CSV Reader Example")
public class MockDashBoard extends AbstractDashboard {

	private static final Logger LOG = Logger.getLogger(MockDashBoard.class.getName());
	private static final String WHITESPACES_REG = "\\s*,\\s*";
	private static final String START_LABEL = "Status: started";
	private static final String RESET_LABEL = "Status: started after reset";
	private static final String STATUS_NOFILE = "Status: no file loaded";
	private static final String STATUS_STOP = "Status: stopped";
	private static final String STATUS_ERROR_FILE = "Error: loading file should be csv";

	private static final Label label = new Label(STATUS_NOFILE);
	private static final Label labelStatus = new Label(STATUS_STOP);

	private static final Button resetButton = new Button("Reset");
	private static final Button startButton = new Button("Start");
	private static final Button stopButton = new Button("Stop");

	/**
	 * Constructor of MockDashBoard. Create a main container with 2 child containers.
	 */
	public MockDashBoard() {
		addWidget(createMainContainer());
	}

	private Container createMainContainer() {
		Container mainContainer = new Container();
		mainContainer.addWidget(new TitledWidget("Source File", createTopContainer()));
		mainContainer.addWidget(new TitledWidget("Commands", createBottomContainer()));
		return mainContainer;
	}

	private Container createTopContainer() {
		Container topContainer = new Container(true);
		FileChooser fileChooser = new FileChooser(FileProvider.class);
		topContainer.addWidget(fileChooser);
		topContainer.addWidget(new JavaFxWidget(label));
		return topContainer;
	}

	private Container createBottomContainer() {
		Container bottomContainer = new Container();
		bottomContainer.addWidget(createInnerBottomContainer());
		bottomContainer.addWidget(new JavaFxWidget(labelStatus));
		return bottomContainer;
	}

	private Container createInnerBottomContainer() {
		Container innerBottomContainer = new EvenlyDistributedContainer(true);
		createButtons(innerBottomContainer);
		return innerBottomContainer;
	}

	/**
	 * @param innerBottomContainer
	 */
	private void createButtons(Container innerBottomContainer) {
		createStartButton(innerBottomContainer);
		createResetButton(innerBottomContainer);
		createStopButton(innerBottomContainer);
		MockupApplication.getProperty(StatusProvider.class).setValue(Boolean.FALSE);

		MockupApplication.getProperty(FileProvider.class).addValueListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				label.setText(newValue.getName());
				startButton.setDisable(false);
				resetButton.setDisable(true);
				stopButton.setDisable(true);
			}
		});
	}

	/**
	 * @param innerBottomContainer
	 */
	private void createStartButton(Container innerBottomContainer) {
		startButton.setDisable(true);
		startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

			File file = MockupApplication.getProperty(FileProvider.class).getValue();
			String fileName = file.getName();

			if (!fileName.substring(fileName.lastIndexOf(".") + 1).equals("csv")) {
				labelStatus.setText(STATUS_ERROR_FILE);
			} else {
				if (file != null) {
					try {
						Files.readAllLines(file.toPath()).forEach(line -> {
							String[] tokens = line.trim().split(WHITESPACES_REG);
							MockupApplication.getProperty(SpeedProvider.class)
									.setValue(Integer.valueOf(Integer.parseInt(tokens[0])));
							MockupApplication.getProperty(TemperatureProvider.class)
									.setValue(Integer.valueOf(Integer.parseInt(tokens[1])));
							labelStatus.setText(START_LABEL);
						});

					} catch (IOException e) {
						LOG.severe("IOException on read action : " + e.getMessage());
					}
					resetButton.setDisable(false);
					stopButton.setDisable(false);
				}
			}
			startButton.setDisable(true);
			MockupApplication.getProperty(StatusProvider.class).setValue(Boolean.TRUE);

		});
		innerBottomContainer.addWidget(new JavaFxWidget(startButton));
	}

	/**
	 * @param innerBottomContainer
	 */
	private void createResetButton(Container innerBottomContainer) {

		resetButton.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			File file = MockupApplication.getProperty(FileProvider.class).getValue();
			MockupApplication.getProperty(SpeedProvider.class).clear();
			MockupApplication.getProperty(TemperatureProvider.class).clear();
			if (file != null) {
				try {
					fillQueues(file);
					labelStatus.setText(RESET_LABEL);
				} catch (IOException e) {
					LOG.severe("IOException on click action : " + e.getMessage());
				}
				if (stopButton.isDisabled()) {
					stopButton.setDisable(false);
				}
				if (!startButton.isDisabled()) {
					startButton.setDisable(true);
				}
			}
		});
		innerBottomContainer.addWidget(new JavaFxWidget(resetButton));
	}

	/**
	 * @param innerBottomContainer
	 */
	private void createStopButton(Container innerBottomContainer) {
		stopButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			MockupApplication.getProperty(SpeedProvider.class).clear();
			MockupApplication.getProperty(TemperatureProvider.class).clear();

			stopButton.setDisable(true);
			labelStatus.setText(STATUS_STOP);
			if (!resetButton.isDisabled()) {
				resetButton.setDisable(true);
			}
			if (startButton.isDisabled()) {
				startButton.setDisable(false);
				MockupApplication.getProperty(StatusProvider.class).setValue(Boolean.FALSE);
			}
		});
		stopButton.setDisable(true);
		resetButton.setDisable(true);
		innerBottomContainer.addWidget(new JavaFxWidget(stopButton));
	}

	/**
	 * @param file
	 * @throws IOException
	 */
	private void fillQueues(File file) throws IOException {
		Files.readAllLines(file.toPath()).forEach(line -> {
			String[] tokens = line.trim().split(WHITESPACES_REG);
			MockupApplication.getProperty(SpeedProvider.class).setValue(Integer.valueOf(Integer.parseInt(tokens[0])));
			MockupApplication.getProperty(TemperatureProvider.class)
					.setValue(Integer.valueOf(Integer.parseInt(tokens[1])));
		});
	}
}
