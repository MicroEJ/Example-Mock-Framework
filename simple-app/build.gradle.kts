/*
 * Kotlin
 *
 * Copyright 2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
plugins {
    id("com.microej.gradle.application")
    id("com.microej.example.plugin.application-simulation-classpaths")
}

group="com.microej.example.mock-framework"
version="1.0.0"

microej {
  applicationEntryPoint = "com.microej.example.mockframework.Main"
}

dependencies {
    implementation("ej.library.ui:mwt:3.5.3")

    /*
    * Specify which mock to use for this application.
    */
    microejMock(project(":simple-mock"))

    /*
     * Use an evaluation VEE Port published in the Developer Repository
     */
    microejVee("com.microej.veeport.st.stm32f7508-dk:M5QNX_eval:2.2.0")
}

