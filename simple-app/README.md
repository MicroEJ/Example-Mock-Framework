# Overview

The Simple Mock example shows the implementation of SNI natives using the Mock Framework.

The example implements a random value generator which bounds can be set with a bounds setter widget.

The number generation can be enabled/disabled with a checkbox widget.

![Example_screenshot.png](README_images/Example_screenshot.png)

This module contains the application part of the example.

# Requirements

* [MICROEJ SDK 6](https://docs.microej.com/en/latest/SDK6UserGuide/index.html).
* A [VEE Port](https://github.com/MicroEJ/?q=VEEPort&type=all&language=&sort=) that contains:
    * EDC-1.3 or higher.

This example has been tested on:

* Android Studio with MicroEJ plugin for Android Studio 0.1.2.
* [STM32F7508-DK VEE Port 2.2.0](https://github.com/MicroEJ/VEEPort-STMicroelectronics-STM32F7508-DK/tree/2.2.0).

# Usage

Open the Example-Mock-Framework project in Android Studio.

Make sure to properly setup the VEE Port.

By default, the sample will use the STM32F7508-DK VEE Port.

## Run the example on Simulator

In Android Studio:
- Open the Gradle tool window by clicking on the elephant icon on the right side,
- Expand the `simple-app` > `Tasks` > `microej` lists,
- Double-click on `runOnSimulator`,
- The application starts, the traces are visible in the Run view.

Alternative ways to run in simulation are described in the [Run on Simulator](https://docs.microej.com/en/latest/SDK6UserGuide/runOnSimulator.html) documentation.

## Play with the example

* The random number generator can be enabled/disabled either by clicking on the simulated device display or by clicking on the mock's `enabled` checkbox.
* The bounds of the generated number can be set using the bounds setter widget.

# Dependencies

_All dependencies are retrieved transitively by Gradle_.

# Source

N/A.

# Restrictions

None.

---
_Markdown_   
_Copyright 2024 MicroEJ Corp. All rights reserved._  
_Use of this source code is governed by a BSD-style license that can be found with this software._