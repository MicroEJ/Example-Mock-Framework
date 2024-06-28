/*
 * Java
 *
 * Copyright 2024 MicroEJ Corp. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be found with this software.
 */
package com.microej.example.mockframework;

import com.microej.example.mockframework.service.RandomService;

import ej.annotation.Nullable;
import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.Displayable;
import ej.microui.display.Font;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Painter;
import ej.microui.event.generator.Buttons;
import ej.mwt.util.Alignment;

public class SimpleAppDisplayable extends Displayable implements Runnable {

    private static final int SLEEP_TIME = 1000;
    private boolean isRunning;
    private @Nullable Thread runner;

    @Override
    protected void onShown() {
        this.runner = new Thread(this);
        this.runner.start();
    }

    @Override
    protected void onHidden() {
        this.isRunning = false;
        if(this.runner != null) {
            this.runner.interrupt();
        }
    }

    @Override
    public void run() {
        this.isRunning = true;
        RandomService.setEnable(true);
        while (this.isRunning) {
            try {
                this.requestRender();
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    @Override
    protected void render(GraphicsContext graphicsContext) {
        final int width = Display.getDisplay().getWidth();
        final int height = Display.getDisplay().getHeight();

        graphicsContext.setColor(Colors.WHITE);
        Painter.fillRectangle(graphicsContext, 0, 0, width, height);

        final Font sourceSansPro = Font.getFont("/fonts/source_sans_pro_24.ejf"); //$NON-NLS-1$

        graphicsContext.setColor(Colors.BLACK);
        final String status = "Random generator " + (RandomService.getEnable() ? "enabled" : "disabled"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        int x = Alignment.computeLeftX(sourceSansPro.stringWidth(status), 0, width, Alignment.HCENTER);
        int y = Alignment.computeTopY(sourceSansPro.getHeight(), 0, height, Alignment.VCENTER);
        Painter.drawString(graphicsContext, status, sourceSansPro, x, y - sourceSansPro.getHeight() / 2);

        final String randomValue = "Value: " + RandomService.getInt(); //$NON-NLS-1$
        x = Alignment.computeLeftX(sourceSansPro.stringWidth(randomValue), 0, width, Alignment.HCENTER);
        y = Alignment.computeTopY(sourceSansPro.getHeight(), 0, height, Alignment.VCENTER);

        Painter.drawString(graphicsContext, randomValue, sourceSansPro, x, y + sourceSansPro.getHeight() / 2);
    }

    @Override
    public boolean handleEvent(int event) {
        if (Buttons.isClicked(event)) {
            RandomService.switchEnable();
            this.requestRender();
            return true;
        }
        return false;
    }
}
