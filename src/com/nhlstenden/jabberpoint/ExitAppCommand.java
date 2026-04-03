package com.nhlstenden.jabberpoint;

/**
 * Command to exit the application.
 */
public class ExitAppCommand implements Command
{
    private final Runnable exitAction;

    /**
     * Constructor with custom exit action.
     *
     * @param exitAction the action to perform on execute
     */
    public ExitAppCommand(Runnable exitAction)
    {
        this.exitAction = exitAction;
    }

    /**
     * Constructor with default exit action (System.exit(0)).
     */
    public ExitAppCommand()
    {
        this(() -> System.exit(0));
    }

    @Override
    public void execute()
    {
        this.exitAction.run();
    }
}
