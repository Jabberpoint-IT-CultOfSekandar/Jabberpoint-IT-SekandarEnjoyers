package com.nhlstenden.jabberpoint;

/**
 * Command to go to the next slide.
 */
public class NextSlideCommand implements Command
{
    private final Presentation presentation;

    /**
     * Constructor for NextSlideCommand.
     *
     * @param presentation the Presentation to navigate
     */
    public NextSlideCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void execute()
    {
        this.presentation.nextSlide();
    }
}
