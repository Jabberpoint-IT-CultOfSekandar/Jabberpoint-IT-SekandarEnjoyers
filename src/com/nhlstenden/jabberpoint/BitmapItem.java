package com.nhlstenden.jabberpoint;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;

import java.io.IOException;


/**
 * <p>A bitmap/image item on a slide.</p>
 * <p>Bitmap items have the responsibility to draw themselves. This class is a Leaf node in the Composite pattern.</p>
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

public class BitmapItem extends SlideItem
{
    private BufferedImage bufferedImage;
    private String imageName;

    protected static final String FILE = "File ";
    protected static final String NOTFOUND = " not found";

    /**
     * Constructor for BitmapItem.
     * Level is equal to item-level; name is the name of the file with the Image
     *
     * @param level the level of the item
     * @param name  the name of the image file
     */
    public BitmapItem(int level, String name)
    {
        super(level);
        this.imageName = name;
        try
        {
            this.bufferedImage = ImageIO.read(new File(this.imageName));
            if (this.bufferedImage == null)
            {
                throw new IOException("Cannot read image file");
            }
        }
        catch (IOException e)
        {
            System.err.println(FILE + this.imageName + NOTFOUND);
            this.bufferedImage = null;
        }
    }

    /**
     * Constructor for an empty bitmap-item with default values.
     */
    public BitmapItem()
    {
        this(0, null);
    }

    /**
     * Gets the filename of the image.
     *
     * @return the image filename
     */
    public String getName()
    {
        return this.imageName;
    }

    /**
     * Gets the bounding box of the image.
     *
     * @param g        the Graphics context
     * @param observer the ImageObserver
     * @param scale    the scaling factor
     * @param myStyle  the Style to apply
     * @return the bounding rectangle
     */
    public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle)
    {
        if (this.bufferedImage == null)
        {
            int w = (int) (myStyle.indent * scale);
            int h = (int) (myStyle.leading * scale);

            return new Rectangle(w, 0, 0, h);
        }

        return new Rectangle((int) (myStyle.indent * scale), 0,
                (int) (this.bufferedImage.getWidth(observer) * scale),
                ((int) (myStyle.leading * scale)) +
                (int) (this.bufferedImage.getHeight(observer) * scale));
    }

    /**
     * Draws the image on the slide.
     *
     * @param x        the x coordinate
     * @param y        the y coordinate
     * @param scale    the scaling factor
     * @param g        the Graphics context
     * @param myStyle  the Style to apply
     * @param observer the ImageObserver
     */
    public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer)
    {
        int width = x + (int) (myStyle.indent * scale);
        int height = y + (int) (myStyle.leading * scale);
        if (this.bufferedImage == null)
        {
            String fallback = "Image not found: " + this.imageName;
            g.drawString(fallback, width, height + g.getFontMetrics().getAscent());

            return;
        }
        g.drawImage(this.bufferedImage, width, height,
                (int) (this.bufferedImage.getWidth(observer) * scale),
                (int) (this.bufferedImage.getHeight(observer) * scale), observer);
    }

    @Override
    public String toString()
    {
        return "BitmapItem[" + getLevel() + "," + this.imageName + "]";
    }
}
