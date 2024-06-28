/*
 * Kotlin
 *
 * Copyright 2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
plugins {
    `java-library`
}

group="com.microej.example.mock-framework"
version="1.0.0"

tasks.jar {
    val dependencies = configurations
            .runtimeClasspath
            .get()
            .map(::zipTree) // OR .map { zipTree(it) }
    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

dependencies {
    implementation("com.microej.library.mock:mock-framework:1.0.1")
    annotationProcessor("com.microej.library.mock:mock-framework:1.0.1")
    implementation(files("lib/HILEngine.jar"))
    compileOnly(group="com.microej.tool", name="javafx", version="1.2.0", configuration="provided")
    annotationProcessor(group="com.microej.tool", name="javafx", version="1.2.0", configuration="provided")
}
