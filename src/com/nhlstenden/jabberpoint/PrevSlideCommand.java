package com.nhlstenden.jabberpoint;

/**
 * Command to go to the previous slide.
 */
public class PrevSlideCommand implements Command
{
    private final Presentation presentation;

    /**
     * Constructor for PrevSlideCommand.
     *
     * @param presentation the Presentation to navigate
     */
    public PrevSlideCommand(Presentation presentation)
    {
        this.presentation = presentation;
    }

    @Override
    public void execute()
    {
        this.presentation.prevSlide();
    }
}
