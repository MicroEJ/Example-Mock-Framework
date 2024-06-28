/*
 * Copyright 2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */

import com.microej.gradle.plugins.MicroejApplicationGradlePlugin
import com.microej.gradle.tasks.BuildVirtualDeviceTask
import com.microej.gradle.tasks.RunOnSimulatorTask
import com.microej.gradle.util.FileToolbox
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.attributes.Bundling
import org.gradle.api.attributes.Category
import org.gradle.api.attributes.LibraryElements
import org.gradle.api.attributes.Usage
import org.gradle.api.attributes.java.TargetJvmEnvironment
import org.gradle.api.plugins.jvm.JvmTestSuite
import org.gradle.kotlin.dsl.*
import org.gradle.nativeplatform.MachineArchitecture
import org.gradle.nativeplatform.OperatingSystemFamily
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardOpenOption
import java.util.*

class SimulationClasspathsPlugin : Plugin<Project> {
	override fun apply(project: Project) {
		val microejMock: Configuration by project.configurations.creating {
			isCanBeResolved = false
			isCanBeConsumed = false
		}
		val microejFrontPanel: Configuration by project.configurations.creating {
			isCanBeResolved = false
			isCanBeConsumed = false
		}

		val hilClasspath = project.configurations.register("hilClasspath") {
			isCanBeConsumed = false
			assert(isCanBeResolved)
			extendsFrom(microejMock)
			// Copy attributes from runtimeClasspath configuration
			val runtimeAttributes = project.configurations.getByName("runtimeClasspath").attributes
			attributes {
				attribute(Category.CATEGORY_ATTRIBUTE, project.objects.named(Category::class, Category.LIBRARY))
				attribute(
					LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE,
					project.objects.named(LibraryElements::class, LibraryElements.JAR)
				)
				runtimeAttributes.getAttribute(MachineArchitecture.ARCHITECTURE_ATTRIBUTE)
					?.let { attribute(MachineArchitecture.ARCHITECTURE_ATTRIBUTE, it) }
				runtimeAttributes.getAttribute(OperatingSystemFamily.OPERATING_SYSTEM_ATTRIBUTE)
					?.let { attribute(OperatingSystemFamily.OPERATING_SYSTEM_ATTRIBUTE, it) }
				attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage::class, Usage.JAVA_RUNTIME))
				attribute(Bundling.BUNDLING_ATTRIBUTE, project.objects.named(Bundling::class, Bundling.EXTERNAL))
				attribute(
					TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
					project.objects.named(TargetJvmEnvironment::class, TargetJvmEnvironment.STANDARD_JVM)
				)
			}
		}
		val s3Classpath = project.configurations.register("s3Classpath") {
			isCanBeConsumed = false
			assert(isCanBeResolved)
			extendsFrom(microejFrontPanel)
			// Copy attributes from runtimeClasspath configuration
			val runtimeAttributes = project.configurations.getByName("runtimeClasspath").attributes
			attributes {
				attribute(Category.CATEGORY_ATTRIBUTE, project.objects.named(Category::class, Category.LIBRARY))
				attribute(
					LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE,
					project.objects.named(LibraryElements::class, LibraryElements.JAR)
				)
				runtimeAttributes.getAttribute(MachineArchitecture.ARCHITECTURE_ATTRIBUTE)
					?.let { attribute(MachineArchitecture.ARCHITECTURE_ATTRIBUTE, it) }
				runtimeAttributes.getAttribute(OperatingSystemFamily.OPERATING_SYSTEM_ATTRIBUTE)
					?.let { attribute(OperatingSystemFamily.OPERATING_SYSTEM_ATTRIBUTE, it) }
				attribute(Usage.USAGE_ATTRIBUTE, project.objects.named(Usage::class, Usage.JAVA_RUNTIME))
				attribute(Bundling.BUNDLING_ATTRIBUTE, project.objects.named(Bundling::class, Bundling.EXTERNAL))
				attribute(
					TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
					project.objects.named(TargetJvmEnvironment::class, TargetJvmEnvironment.STANDARD_JVM)
				)
			}
		}

		if (project.plugins.hasPlugin(MicroejApplicationGradlePlugin::class)) {
			project.tasks.named<RunOnSimulatorTask>("runOnSimulator") {
				inputs.files(hilClasspath, s3Classpath)
				systemProperties.put("hil.mockup.custom.classpath", hilClasspath.get().asPath)
				systemProperties.put("frontpanel.workbench.classpath", s3Classpath.get().asPath)
			}

			project.tasks.named("buildVirtualDevice") {
				inputs.files(hilClasspath, s3Classpath)
				doLast {
					val properties = Properties()
					val virtualDeviceDir = project.layout.buildDirectory.dir("virtualDevice").get().asFile.toPath()

					val customMocks = virtualDeviceDir.resolve("custom-mocks")
					Files.createDirectories(customMocks)
					properties["hil.mockup.custom.classpath"] = hilClasspath.get().files.map {
						val target = customMocks.resolve(it.name)
						Files.copy(it.toPath(), target)
						virtualDeviceDir.resolve("scripts").relativize(target)
					}.joinToString(File.pathSeparator)

					// hil.mockup.custom.classpath must be set before init scripts are loaded: use scripts/s3.properties
					Files.newBufferedWriter(
						virtualDeviceDir.resolve("scripts/s3.properties"),
						StandardOpenOption.APPEND
					).use { it.newLine(); properties.store(it, null) }

					// we override the virtual device, we need to regenerate the archive
					val vdPath: String = (this as BuildVirtualDeviceTask).virtualDeviceFile.asFile.get().absolutePath
					val vdFiles: MutableMap<String, String> = HashMap()
					val virtualDevice: File = virtualDeviceDir.toFile()
					vdFiles[virtualDevice.name] = virtualDevice.absolutePath
					FileToolbox.zipFile(vdPath, vdFiles, null)
				}
			}
		}
	}
}

fun JvmTestSuite.configureSimulationClasspathsForTests() {
	this@configureSimulationClasspathsForTests.targets {
		all {
			testTask.configure {
				val hilClasspath = project.configurations.getByName("hilClasspath")
				val s3Classpath = project.configurations.getByName("s3Classpath")
				inputs.files(hilClasspath, s3Classpath)
				systemProperties["microej.testsuite.properties.hil.mockup.custom.classpath"] = hilClasspath.asPath
				systemProperties["microej.testsuite.properties.frontpanel.workbench.classpath"] = s3Classpath.asPath
			}
		}
	}
}
