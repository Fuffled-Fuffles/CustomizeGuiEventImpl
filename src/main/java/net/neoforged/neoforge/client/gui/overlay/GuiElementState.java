/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.neoforged.neoforge.client.gui.overlay;

public enum GuiElementState {
    EMPTY,
    HALF,
    FULL;

    public <T> T pick(T empty, T half, T full) {
        if (this == FULL)
            return full;
        else if (this == HALF)
            return half;
        else
            return empty;
    }

    public static GuiElementState calculate(int idx, int level) {
        if (idx < level)
            return FULL;
        else if (idx == level)
            return HALF;
        else
            return EMPTY;
    }
}
