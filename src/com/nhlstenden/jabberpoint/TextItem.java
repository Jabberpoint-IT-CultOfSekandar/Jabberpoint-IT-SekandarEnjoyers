package com.nhlstenden.jabberpoint;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.TextLayout;
import java.awt.font.TextAttribute;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * <p>A text item on a slide.</p>
 * <p>A TextItem has drawing functionality and represents a leaf node in the Composite pattern.</p>
 * <p>This class is a Leaf component - it cannot contain child items.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 * @version 2.0 - Composite Pattern (Leaf Node)
 */
public class TextItem extends SlideItem
{
    private String text;

    private static final String EMPTYTEXT = "No Text Given";

    /**
     * Constructor for TextItem with level and text.
     *
     * @param level  the level of this item
     * @param string the text content
     */
    public TextItem(int level, String string)
    {
        super(level);
        this.text = string;
    }

    /**
     * Constructor for empty TextItem with default text.
     */
    public TextItem()
    {
        this(0, EMPTYTEXT);
    }

    /**
     * Gets the text of this item.
     *
     * @return the text content
     */
    public String getText()
    {
        return this.text == null ? "" : this.text;
    }

    /**
     * Gets the AttributedString for this item.
     *
     * @param style the style to apply
     * @param scale the scale factor
     * @return the attributed string
     */
    public AttributedString getAttributedString(Style style, float scale)
    {
        AttributedString attrStr = new AttributedString(this.getText());
        attrStr.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, this.text.length());

        return attrStr;
    }

    @Override
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle)
    {
        List<TextLayout> layouts = this.getLayouts(g, myStyle, scale);
        int xsize = 0, ysize = (int) (myStyle.leading * scale);
        Iterator<TextLayout> iterator = layouts.iterator();
        while (iterator.hasNext())
        {
            TextLayout layout = iterator.next();
            Rectangle2D bounds = layout.getBounds();
            if (bounds.getWidth() > xsize)
            {
                xsize = (int) bounds.getWidth();
            }
            if (bounds.getHeight() > 0)
            {
                ysize += bounds.getHeight();
            }
            ysize += layout.getLeading() + layout.getDescent();
        }

        return new Rectangle((int) (myStyle.indent * scale), 0, xsize, ysize);
    }

    @Override
    public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver o)
    {
        if (this.text == null || this.text.length() == 0)
        {
            return;
        }
        List<TextLayout> layouts = this.getLayouts(g, myStyle, scale);
        Point pen = new Point(x + (int) (myStyle.indent * scale),
                y + (int) (myStyle.leading * scale));
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(myStyle.color);
        Iterator<TextLayout> it = layouts.iterator();
        while (it.hasNext())
        {
            TextLayout layout = it.next();
            pen.y += layout.getAscent();
            layout.draw(g2d, pen.x, pen.y);
            pen.y += layout.getDescent();
        }
    }

    /**
     * Gets the text layouts for this item.
     *
     * @param g     the Graphics context
     * @param s     the style to apply
     * @param scale the scale factor
     * @return a list of text layouts
     */
    private List<TextLayout> getLayouts(Graphics g, Style s, float scale)
    {
        List<TextLayout> layouts = new ArrayList<TextLayout>();
        AttributedString attrStr = this.getAttributedString(s, scale);
        Graphics2D g2d = (Graphics2D) g;
        FontRenderContext frc = g2d.getFontRenderContext();
        LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
        float wrappingWidth = (Slide.WIDTH - s.indent) * scale;
        while (measurer.getPosition() < this.getText().length())
        {
            TextLayout layout = measurer.nextLayout(wrappingWidth);
            layouts.add(layout);
        }

        return layouts;
    }

    @Override
    public String toString()
    {
        return "TextItem[" + getLevel() + "," + getText() + "]";
    }
}
