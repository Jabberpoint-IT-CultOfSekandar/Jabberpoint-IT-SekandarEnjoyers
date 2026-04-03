package com.nhlstenden.jabberpoint;

import java.util.ArrayList;

/**
 * <p>Presentation maintains the slides in the presentation.</p>
 * <p>There is only one instance of this class.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class Presentation implements Observable
{
    private String showTitle; // title of the presentation
    private ArrayList<Slide> showList = null; // an ArrayList with Slides
private int currentSlideNumber = 0; // the slide number of the current Slide
    private Observer slideViewComponent = null; // the view component of the Slides

    /**
     * Constructor for Presentation with no observer.
     */
    public Presentation()
    {
        this.slideViewComponent = null;
        this.clear();
    }

    /**
     * Constructor for Presentation with an observer.
     *
     * @param slideViewerComponent the observer to notify of changes
     */
    public Presentation(Observer slideViewerComponent)
    {
        this.addObserver(slideViewerComponent);
        this.clear();
    }

    @Override
    public void addObserver(Observer o)
    {
        this.setShowView(o);
    }

    @Override
    public void removeObserver(Observer o)
    {
        if (this.slideViewComponent != o)
        {
            return;
        }
        this.setShowView(null);
    }

    @Override
    public void notifyObserver(Presentation p)
    {
        this.slideViewComponent.update(p);
    }

    /**
     * Gets the number of slides in the presentation.
     *
     * @return the slide count
     */
    public int getSize()
    {
        return this.showList.size();
    }

    /**
     * Gets the title of the presentation.
     *
     * @return the presentation title
     */
    public String getTitle()
    {
        return this.showTitle;
    }

    /**
     * Sets the title of the presentation.
     *
     * @param nt the new title
     */
    public void setTitle(String nt)
    {
        this.showTitle = nt;
    }

    /**
     * Sets the show view component (the observer).
     *
     * @param slideViewerComponent the observer to set
     */
    public void setShowView(Observer slideViewerComponent)
    {
        this.slideViewComponent = slideViewerComponent;
    }

    /**
     * Gets the number of the current slide.
     *
     * @return the current slide number (zero-based)
     */
    public int getSlideNumber()
    {
        return this.currentSlideNumber;
    }

    /**
     * Changes the current slide number and signals it to the observer.
     *
     * @param number the new slide number
     */
    public void setSlideNumber(int number)
    {
        this.currentSlideNumber = number;
        if (this.slideViewComponent != null)
        {
            this.notifyObserver(this);
        }
    }

    /**
     * Go to the previous slide unless at the beginning.
     */
    public void prevSlide()
    {
        if (this.currentSlideNumber > 0)
        {
            this.setSlideNumber(this.currentSlideNumber - 1);
        }
    }

    /**
     * Go to the next slide unless at the end.
     */
    public void nextSlide()
    {
        if (this.currentSlideNumber < (this.showList.size() - 1))
        {
            this.setSlideNumber(this.currentSlideNumber + 1);
        }
    }

    /**
     * Delete the presentation to be ready for the next one.
     */
    public void clear()
    {
        this.showList = new ArrayList<Slide>();
        this.setSlideNumber(-1);
    }

    /**
     * Add a slide to the presentation.
     *
     * @param slide the slide to add
     */
    public void append(Slide slide)
    {
        this.showList.add(slide);
    }

    /**
     * Get a slide with a certain slide number.
     *
     * @param number the zero-based slide index
     * @return the slide at that index, or null if out of bounds
     */
    public Slide getSlide(int number)
    {
        if (number < 0 || number >= this.getSize())
        {
            return null;
        }

        return this.showList.get(number);
    }

    /**
     * Get the current slide.
     *
     * @return the current slide, or null if no slide is selected
     */
    public Slide getCurrentSlide()
    {
        return this.getSlide(this.currentSlideNumber);
    }

    /**
     * Exit the application.
     *
     * @param n the exit code
     */
    public void exit(int n)
    {
        System.exit(n);
    }
}
