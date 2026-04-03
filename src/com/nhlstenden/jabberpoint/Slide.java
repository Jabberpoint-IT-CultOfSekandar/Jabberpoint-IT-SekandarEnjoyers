package com.nhlstenden.jabberpoint;

/**
 * <p>A slide containing multiple slide items.</p>
 * <p>This class has drawing functionality and acts as a Composite in the Composite pattern,
 * managing a collection of SlideItems (TextItem, BitmapItem, CompositeSlideItem).</p>
 * <p>Slides treat all items uniformly through the SlideItem interface, allowing for
 * recursive rendering without special case handling.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 2.0 - Composite Pattern (Container)
 */
public class Slide
{
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;
    protected String title; // title is saved separately
    protected Vector<SlideItem> items; // slide items are saved in a Vector

    /**
     * Constructor for Slide.
     */
    public Slide()
    {
        this.items = new Vector<SlideItem>();
    }

    /**
     * Add a slide item.
     *
     * @param anItem the SlideItem to add
     */
    public void append(SlideItem anItem)
    {
        this.items.addElement(anItem);
    }

    /**
     * Gets the title of the slide.
     *
     * @return the slide title
     */
    public String getTitle()
    {
        return this.title;
    }

    /**
     * Sets the title of the slide.
     *
     * @param newTitle the new title
     */
    public void setTitle(String newTitle)
    {
        this.title = newTitle;
    }

    /**
     * Create TextItem from String and add it.
     *
     * @param level   the level of the item
     * @param message the text content
     */
    public void append(int level, String message)
    {
        this.append(new TextItem(level, message));
    }

    /**
     * Get a specific SlideItem by index.
     *
     * @param number the index of the item
     * @return the SlideItem at that index
     */
    public SlideItem getSlideItem(int number)
    {
        return this.items.elementAt(number);
    }

    /**
     * Get all SlideItems in a Vector.
     *
     * @return the vector of slide items
     */
    public Vector<SlideItem> getSlideItems()
    {
        return this.items;
    }

    /**
     * Get the size of the Slide.
     *
     * @return the number of items on the slide
     */
    public int getSize()
    {
        return this.items.size();
    }

    /**
     * Get a specific SlideItem by index (Composite pattern access).
     * @param index the index of the item
     * @return the SlideItem at that index
     */
    public SlideItem getItem(int index)
    {
        if (index >= 0 && index < this.items.size())
        {
            return this.items.elementAt(index);
        }

        return null;
    }

    /**
     * Check if a specific item is a composite (container) or leaf node.
     * Useful for generic processing of mixed item hierarchies.
     * @param index the index of the item to check
     * @return true if the item is a composite, false if it's a leaf
     */
    public boolean isCompositeItem(int index)
    {
        SlideItem item = this.getItem(index);
        return item != null && item.isComposite();
    }

    /**
     * Draws the slide on the given Graphics context.
     *
     * @param g    the Graphics context
     * @param area the area to draw in
     * @param view the ImageObserver
     */
    public void draw(Graphics g, Rectangle area, ImageObserver view)
    {
        float scale = this.getScale(area);
        int y = area.y;
        // Title is handled separately
        SlideItem slideItem = new TextItem(0, this.getTitle());
        Style style = Style.getStyle(slideItem.getLevel());
        slideItem.draw(area.x, y, scale, g, style, view);
        y += slideItem.getBoundingBox(g, view, scale, style).height;
        for (int number = 0; number < this.getSize(); number++)
        {
            slideItem = this.getSlideItems().elementAt(number);
            style = Style.getStyle(slideItem.getLevel());
            slideItem.draw(area.x, y, scale, g, style, view);
            y += slideItem.getBoundingBox(g, view, scale, style).height;
        }
    }

    /**
     * Get the scale for drawing based on the area.
     *
     * @param area the area to scale to
     * @return the scale factor
     */
    private float getScale(Rectangle area)
    {
        return Math.min(((float) area.width) / ((float) WIDTH), ((float) area.height) / ((float) HEIGHT));
    }
}
