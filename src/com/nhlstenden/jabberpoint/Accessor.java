package com.nhlstenden.jabberpoint;

import java.io.IOException;

/**
 * <p>An Accessor provides the ability to read or write presentation data.</p>
 * <p>Non-abstract subclasses must implement the load and save methods.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public abstract class Accessor
{
    public static final String DEMO_NAME = "Demonstration presentation";
    public static final String DEFAULT_EXTENSION = ".xml";

    /**
     * Gets the demo accessor.
     *
     * @return a DemoPresentation accessor instance
     */
    public static Accessor getDemoAccessor()
    {
        return new DemoPresentation();
    }

    /**
     * Constructor for Accessor.
     */
    public Accessor()
    {
    }

    /**
     * Load a presentation from a file.
     *
     * @param p  the Presentation to load into
     * @param fn the filename to load from
     * @throws IOException if an IO error occurs
     */
    public abstract void loadFile(com.nhlstenden.jabberpoint.Presentation p, String fn) throws IOException;

    /**
     * Save a presentation to a file.
     *
     * @param p  the Presentation to save
     * @param fn the filename to save to
     * @throws IOException if an IO error occurs
     */
    public abstract void saveFile(com.nhlstenden.jabberpoint.Presentation p, String fn) throws IOException;
}
