/*
 * Copyright (c) NeoForged and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package net.neoforged.neoforge.client.event;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.fml.LogicalSide;
import net.neoforged.neoforge.client.gui.overlay.GuiElementState;
import net.neoforged.neoforge.common.NeoForge;
import org.jetbrains.annotations.Nullable;

/**
 * Fired when an overlay's individual element(s) is/are about to be rendered to the screen to allow the user to modify it.
 *
 * @see Armor
 * @see Air
 * @see Absorption
 * @see Health
 * @see Hunger
 * @see MountHealth
 */
public abstract class CustomizeGuiOverlayElementEvent extends Event implements ICancellableEvent {
    private final Window window;
    private final GuiGraphics guiGraphics;
    private final RandomSource random;
    private final int tickCount;
    private final int index;
    private final GuiElementState guiState;
    private ResourceLocation texture;
    private int posX;
    private int posY;
    private int width;
    private int height;

    public CustomizeGuiOverlayElementEvent(Window window, GuiGraphics guiGraphics, RandomSource random, int tickCount, int index, ResourceLocation texture, int posX, int posY, int width, int height, GuiElementState guiState) {
        this.window = window;
        this.guiGraphics = guiGraphics;
        this.random = random;
        this.tickCount = tickCount;
        this.index = index;
        this.guiState = guiState;
        this.texture = texture;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public Window getWindow() {
        return window;
    }

    public GuiGraphics getGuiGraphics() {
        return guiGraphics;
    }

    public RandomSource getRandom() {
        return this.random;
    }

    public int getTickCount() {
        return this.tickCount;
    }

    /**
     * @return the index of the given element starting from bottom left, from left to right upwards
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * @return whether the given element is supposed to be {@linkplain GuiElementState#FULL full}, {@linkplain GuiElementState#HALF half} or {@linkplain GuiElementState#EMPTY empty}
     */
    public GuiElementState getGuiState() {
        return this.guiState;
    }

    /**
     * @return the texture of the element
     */
    @Nullable
    public ResourceLocation getTexture() {
        return this.texture;
    }

    /**
     * Sets the new texture for rendering the element
     *
     * @param texture the new texture to be used
     */
    public void setTexture(ResourceLocation texture) {
        this.texture = texture;
    }

    /**
     * @return the X position of the contained element
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Sets the new X position for rendering the contained element
     *
     * @param posX the new X position
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * @return the Y position of the contained element
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Sets the new Y position for rendering the contained element
     *
     * @param posY the new y position
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * @return the width/u of the element in pixels
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Sets the width/u of the element in pixels
     *
     * @param width the new width/u of the element
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height/v of the element in pixels
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Sets the height/v of the element in pixels
     *
     * @param height the new height/v of the element
     */
    public void setHeight(int height) {
        this.height = height;
    }

    protected abstract static class Contained extends CustomizeGuiOverlayElementEvent {
        private ResourceLocation containerTexture;

        public Contained(Window window, GuiGraphics guiGraphics, RandomSource random, int tickCount, int index, ResourceLocation containerTexture, ResourceLocation texture, int posX, int posY, int width, int height, GuiElementState guiState) {
            super(window, guiGraphics, random, tickCount, index, texture, posX, posY, width, height, guiState);
            this.containerTexture = containerTexture;
        }

        /**
         * @return the texture of the element's container
         */
        @Nullable
        public ResourceLocation getContainerTexture() {
            return this.containerTexture;
        }

        /**
         * Sets the new texture for rendering the element's container
         * When set to null, it will not be rendered
         *
         * @param texture the new texture to be used
         */
        public void setContainerTexture(ResourceLocation texture) {
            this.containerTexture = texture;
        }
    }

    /**
     * Fired <b>before</b> each individual armor indicator is rendered to the screen.
     *
     * <p>This event is {@linkplain ICancellableEvent cancellable}, and does not {@linkplain HasResult have a result}.
     * Cancelling this event will prevent the given hunger hunch from rendering.</p>
     *
     * <p>This event is fired on the {@linkplain NeoForge#EVENT_BUS main Forge event bus},
     * only on the {@linkplain LogicalSide#CLIENT logical client}.</p>
     */
    public static class Armor extends CustomizeGuiOverlayElementEvent {
        public Armor(Window window, GuiGraphics guiGraphics, RandomSource random, int tickCount, int index, ResourceLocation texture, int posX, int posY, int width, int height, GuiElementState guiState) {
            super(window, guiGraphics, random, tickCount, index, texture, posX, posY, width, height, guiState);
        }
    }

    /**
     * Fired <b>before</b> each individual air bubble is rendered to the screen.
     *
     * <p>This event is {@linkplain ICancellableEvent cancellable}, and does not {@linkplain HasResult have a result}.
     * Cancelling this event will prevent the given air bubble from rendering.</p>
     *
     * <p>This event is fired on the {@linkplain NeoForge#EVENT_BUS main Forge event bus},
     * only on the {@linkplain LogicalSide#CLIENT logical client}.</p>
     */
    public static class Air extends CustomizeGuiOverlayElementEvent {
        public Air(Window window, GuiGraphics guiGraphics, RandomSource random, int tickCount, int index, ResourceLocation texture, int posX, int posY, int width, int height, GuiElementState guiState) {
            super(window, guiGraphics, random, tickCount, index, texture, posX, posY, width, height, guiState);
        }
    }

    /**
     * Fired <b>before</b> each individual {@linkplain net.minecraft.world.effect.MobEffects#ABSORPTION absorption} heart is rendered to the screen.
     *
     * <p>This event is {@linkplain ICancellableEvent cancellable}, and does not {@linkplain HasResult have a result}.
     * Cancelling this event will prevent the given {@linkplain net.minecraft.world.effect.MobEffects#ABSORPTION absorption} heart from rendering.</p>
     *
     * <p>This event is fired on the {@linkplain NeoForge#EVENT_BUS main Forge event bus},
     * only on the {@linkplain LogicalSide#CLIENT logical client}.</p>
     */
    public static class Absorption extends Contained {
        private final boolean highlight;
        private int lowHealthBobOffset;

        public Absorption(Window window, GuiGraphics guiGraphics, RandomSource random, int tickCount, int index, ResourceLocation containerTexture, ResourceLocation texture, int posX, int posY, int width, int height, GuiElementState guiState, boolean highlight, int lowHealthBob) {
            super(window, guiGraphics, random, tickCount, index, containerTexture, texture, posX, posY, width, height, guiState);
            this.highlight = highlight;
            this.lowHealthBobOffset = lowHealthBob;
        }

        public boolean isHighlighted() {
            return this.highlight;
        }

        /**
         * @return The intensity at which the health bar will bob at 2 hearts or less
         */
        public int getLowHealthBobOffset() {
            return this.lowHealthBobOffset;
        }

        /**
         * When health and {@linkplain net.minecraft.world.effect.MobEffects#ABSORPTION absorption} reach a value of 2 hearts or less, the health bar will begin to shake
         * Use this to modify it or suppress it entirely.
         *
         * @param value The new intensity at which the health bar will bob at 2 hearts or less
         */
        public void setLowHealthBobOffset(int value) {
            this.lowHealthBobOffset = value;
        }
    }

    /**
     * Fired <b>before</b> each individual heart is rendered to the screen.
     *
     * <p>This event is {@linkplain ICancellableEvent cancellable}, and does not {@linkplain HasResult have a result}.
     * Cancelling this event will prevent the given heart from rendering.</p>
     *
     * <p>This event is fired on the {@linkplain NeoForge#EVENT_BUS main Forge event bus},
     * only on the {@linkplain LogicalSide#CLIENT logical client}.</p>
     */
    public static class Health extends Contained {
        private final GuiElementState highlightGuiState;
        private final boolean highlight;
        private ResourceLocation highlightTexture;
        private int lowHealthBobOffset;
        private int regenWaveOffset;

        public Health(Window window, GuiGraphics guiGraphics, RandomSource random, int tickCount, int index, ResourceLocation containerTexture, ResourceLocation texture, ResourceLocation highlightTexture, int posX, int posY, int width, int height, GuiElementState highlightGuiState, GuiElementState guiState, boolean highlight, int lowHealthBobOffset, int regenWaveOffset) {
            super(window, guiGraphics, random, tickCount, index, containerTexture, texture, posX, posY, width, height, guiState);
            this.highlightGuiState = highlightGuiState;
            this.highlight = highlight;
            this.highlightTexture = highlightTexture;
            this.lowHealthBobOffset = lowHealthBobOffset;
            this.regenWaveOffset = regenWaveOffset;
        }

        /**
         * @return whether the given highlighted element is supposed to be {@linkplain GuiElementState#FULL full}, {@linkplain GuiElementState#HALF half} or {@linkplain GuiElementState#EMPTY empty}
         */
        public GuiElementState getHighlightGuiState() {
            return this.highlightGuiState;
        }

        public boolean isHighlighted() {
            return this.highlight;
        }

        /**
         * @return the texture of the highlighted element
         */
        @Nullable
        public ResourceLocation getHighlightTexture() {
            return this.highlightTexture;
        }

        /**
         * Sets the new texture for rendering the highlighted element
         * When set to null, it will not be rendered
         *
         * @param texture the new texture to be used
         */
        public void setHighlightTexture(ResourceLocation texture) {
            this.highlightTexture = texture;
        }

        /**
         * @return The intensity at which the health bar will bob at 2 hearts or less
         */
        public int getLowHealthBobOffset() {
            return this.lowHealthBobOffset;
        }

        /**
         * When health and {@linkplain net.minecraft.world.effect.MobEffects#ABSORPTION absorption} reach a value of 2 hearts or less, the health bar will begin to shake
         * Use this to modify it or suppress it entirely.
         *
         * @param value The new intensity at which the health bar will bob at 2 hearts or less
         */
        public void setLowHealthBobOffset(int value) {
            this.lowHealthBobOffset = value;
        }

        /**
         * @return The intensity at which the health bar will do waves whenever the player has the {@linkplain net.minecraft.world.effect.MobEffects#REGENERATION regeneration} effect
         */
        public int getRegenerationWaveOffset() {
            return this.regenWaveOffset;
        }

        /**
         * When the player has the {@linkplain net.minecraft.world.effect.MobEffects#REGENERATION regeneration} effect, the health bar will continuously perform a wave.
         * Use this to modify it or suppress it entirely.
         *
         * @param value The new intensity at which the health bar will do waves whenever the player has the {@linkplain net.minecraft.world.effect.MobEffects#REGENERATION regeneration} effect
         */
        public void setRegenerationWaveOffset(int value) {
            this.regenWaveOffset = value;
        }
    }

    /**
     * Fired <b>before</b> each individual hunger indicator is rendered to the screen.
     *
     * <p>This event is {@linkplain ICancellableEvent cancellable}, and does not {@linkplain HasResult have a result}.
     * Cancelling this event will prevent the given hunger indicator from rendering.</p>
     *
     * <p>This event is fired on the {@linkplain NeoForge#EVENT_BUS main Forge event bus},
     * only on the {@linkplain LogicalSide#CLIENT logical client}.</p>
     */
    public static class Hunger extends Contained {
        private int starvationBobOffset;

        public Hunger(Window window, GuiGraphics guiGraphics, RandomSource random, int tickCount, int index, ResourceLocation containerTexture, ResourceLocation texture, int posX, int posY, int width, int height, GuiElementState guiState, int starvationBobOffset) {
            super(window, guiGraphics, random, tickCount, index, containerTexture, texture, posX, posY, width, height, guiState);
            this.starvationBobOffset = starvationBobOffset;
        }

        /**
         * @return The intensity at which the hunger bar can bob at saturation 0
         */
        public int getStarvationBobOffset() {
            return this.starvationBobOffset;
        }

        /**
         * When saturation reaches 0, the hunger bar will increasingly get more agitated the lower the player's food level is.
         * Use this to modify it or suppress it entirely, keep in mind this also includes constant shaking when starving.
         *
         * @param value The new intensity at which the hunger bar can bob at saturation 0
         */
        public void setStarvationBobOffset(int value) {
            this.starvationBobOffset = value;
        }
    }

    /**
     * Fired <b>before</b> each individual mount heart is rendered to the screen.
     *
     * <p>This event is {@linkplain ICancellableEvent cancellable}, and does not {@linkplain HasResult have a result}.
     * Cancelling this event will prevent the given mount heart from rendering.</p>
     *
     * <p>This event is fired on the {@linkplain NeoForge#EVENT_BUS main Forge event bus},
     * only on the {@linkplain LogicalSide#CLIENT logical client}.</p>
     */
    public static class MountHealth extends Contained {
        public MountHealth(Window window, GuiGraphics guiGraphics, RandomSource random, int tickCount, int index, ResourceLocation containerTexture, ResourceLocation texture, int posX, int posY, int width, int height, GuiElementState guiState) {
            super(window, guiGraphics, random, tickCount, index, containerTexture, texture, posX, posY, width, height, guiState);
        }
    }
}
