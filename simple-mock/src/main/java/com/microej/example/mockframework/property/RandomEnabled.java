/*
 * Java
 *
 * Copyright 2020 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.property;

import com.microej.mock.annotation.Property;
import com.microej.mock.property.BooleanProperty;

/**
 * Send to app if the random generator status is enabled or not.
 */
@Property(getter = "com.microej.example.mockframework.service.RandomService.getEnable", setter = "com.microej.example.mockframework.service.RandomService.setEnable")
public class RandomEnabled extends BooleanProperty {

}
