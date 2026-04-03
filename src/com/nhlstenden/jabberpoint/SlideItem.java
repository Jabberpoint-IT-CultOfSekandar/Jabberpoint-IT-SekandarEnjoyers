package com.nhlstenden.jabberpoint;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.List;

/**
 * <p>The abstract class for an item on a slide.</p>
 * <p>All SlideItems have drawing functionality.</p>
 * <p>Implements the Component part of the Composite design pattern.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 2.0 - Composite Pattern Implementation
 */
public abstract class SlideItem
{
    private int level = 0; // level of the slide item

    /**
     * Constructor for SlideItem with a given level.
     *
     * @param lev the level of this item
     */
    public SlideItem(int lev)
    {
        this.level = lev;
    }

    /**
     * Constructor for SlideItem with default level (0).
     */
    public SlideItem()
    {
        this(0);
    }

    /**
     * Gets the level of this slide item.
     *
     * @return the level
     */
    public int getLevel()
    {
        return this.level;
    }

    /**
     * Gets the bounding box of this item.
     *
     * @param g        the Graphics context
     * @param observer the image observer
     * @param scale    the scale factor
     * @param style    the style to apply
     * @return the bounding rectangle
     */
    public abstract Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, com.nhlstenden.jabberpoint.Style style);

    /**
     * Draws this item.
     *
     * @param x        the x coordinate
     * @param y        the y coordinate
     * @param scale    the scale factor
     * @param g        the Graphics context
     * @param style    the style to apply
     * @param observer the image observer
     */
    public abstract void draw(int x, int y, float scale, Graphics g, com.nhlstenden.jabberpoint.Style style, ImageObserver observer);

    /**
     * Add a child component to this item.
     * Default implementation for leaf nodes - does nothing.
     * Override in composite classes.
     * @param item the SlideItem to add as a child
     */
    public void addChild(SlideItem item)
    {
        // Default implementation for leaf nodes - no children
    }

    /**
     * Remove a child component from this item.
     * Default implementation for leaf nodes - does nothing.
     * Override in composite classes.
     * @param item the SlideItem to remove from children
     */
    public void removeChild(SlideItem item)
    {
        // Default implementation for leaf nodes - no children
    }

    /**
     * Get all child components of this item.
     * Default implementation for leaf nodes - returns empty list.
     * Override in composite classes.
     * @return list of child SlideItems, or empty list for leaf nodes
     */
    public List<SlideItem> getChildren()
    {
        // Default implementation for leaf nodes - no children
        return new java.util.ArrayList<>();
    }

    /**
     * Check if this item is a composite (container) or a leaf.
     * @return true if this is a composite/container, false if it's a leaf
     */
    public boolean isComposite()
    {
        return false;
    }
}
