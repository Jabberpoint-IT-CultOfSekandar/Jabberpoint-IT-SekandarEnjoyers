package com.nhlstenden.jabberpoint;

/**
 * Command to go to a specific slide.
 */
public class GotoSlideCommand implements Command
{
    private final Presentation presentation;
    private final int targetSlide; // zero-based

    /**
     * Constructor for GotoSlideCommand.
     *
     * @param presentation the Presentation to navigate
     * @param targetSlide  the zero-based slide index
     */
    public GotoSlideCommand(Presentation presentation, int targetSlide)
    {
        this.presentation = presentation;
        this.targetSlide = targetSlide;
    }

    @Override
    public void execute()
    {
        this.presentation.setSlideNumber(this.targetSlide);
    }
}
