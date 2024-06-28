/*
 * Copyright 2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */

plugins {
	`kotlin-dsl`
}

version="1.0.0"

repositories {
	gradlePluginPortal()
	maven {
		name = "microEJForgeSDK6"
		url = uri("https://forge.microej.com/artifactory/microej-sdk6-repository-release/")
	}
	ivy {
		name = "microEJForgeSDK6Ivy"
		url = uri("https://forge.microej.com/artifactory/microej-sdk6-repository-release/")
		patternLayout {
			artifact("[organisation]/[module]/[revision]/[artifact]-[revision](-[classifier])(.[ext])")
			ivy("[organisation]/[module]/[revision]/ivy-[revision].xml")
			setM2compatible(true)
		}
	}
	ivy {
		name = "microEJForgeCentralIvy"
		url = uri("https://forge.microej.com/artifactory/microej-central-repository-release")
		patternLayout {
			artifact("[organisation]/[module]/[revision]/[artifact]-[revision](-[classifier])(.[ext])")
			ivy("[organisation]/[module]/[revision]/ivy-[revision].xml")
			setM2compatible(true)
		}
	}
}

dependencies {
	implementation("com.microej.gradle.application:com.microej.gradle.application.gradle.plugin:0.17.0")
	implementation("org.jetbrains.gradle.plugin.idea-ext:org.jetbrains.gradle.plugin.idea-ext.gradle.plugin:1.1.8")
}

gradlePlugin {
	plugins {
		create("simulation-classpaths-plugin") {
			id = "com.microej.example.plugin.application-simulation-classpaths"
			implementationClass = "SimulationClasspathsPlugin"
		}
	}
}
