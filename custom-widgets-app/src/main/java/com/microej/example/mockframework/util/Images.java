/*
 * Java
 *
 * Copyright 2022 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework.util;

import ej.basictool.map.PackedMap;
import ej.microui.display.Image;

/**
 *
 */
public class Images {

	/** The name of the banner image. **/
	public static final String MICROEJ_BANNER = "microej_banner"; //$NON-NLS-1$
	/** The name of the top corner image. **/
	public static final String CORNER_TOP = "rounded-corner"; //$NON-NLS-1$
	/** The name of the bottom corner image. **/
	public static final String CORNER_BOTTOM = "rounded-corner-bottom"; //$NON-NLS-1$

	/**
	 * The resource URL indicating where the images are located. Should be an absolute path and must contain a leading
	 * slash.
	 */
	private static final String IMAGE_BASE_URL = "/images/"; //$NON-NLS-1$
	private static final String IMAGE_EXTENSION = ".png"; //$NON-NLS-1$

	private static PackedMap<String, Image> cache = new PackedMap<>();

	/**
	 * Hide the constructor in order to prevent instantiating a class containing only static members.
	 */
	private Images() {
		// Prevent instantiation.
	}

	/**
	 * Obtains the fully qualified resource path to the specified image name.
	 *
	 * <p>
	 * The image names can be found as part of the {@link Images} class.
	 *
	 * @param name
	 *            the name of the image.
	 * @return the fully qualified path to the image.
	 */
	public static String getImagePath(String name) {
		StringBuilder builder = new StringBuilder();
		builder.append(IMAGE_BASE_URL);
		builder.append(name);
		builder.append(IMAGE_EXTENSION);
		return builder.toString();
	}

	/**
	 * Gets an image from its path.
	 *
	 * @param path
	 *            the fully qualified path to the image.
	 * @return the image.
	 */
	public static Image getImage(String path) {
		Image image = cache.get(path);
		if (image == null) {
			image = Image.getImage(path);
			cache.put(path, image);
		}
		return image;
	}
}
