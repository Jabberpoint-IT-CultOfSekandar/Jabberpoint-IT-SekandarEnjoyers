package com.nhlstenden.jabberpoint;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

/**
 * <p>The application window for a SlideViewerComponent.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class SlideViewerFrame extends JFrame
{
    private static final long serialVersionUID = 3227L;

    private static final String JABTITLE = "Jabberpoint 1.6 - OU";
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;

    /**
     * Constructor for SlideViewerFrame.
     *
     * @param title          the window title
     * @param presentation   the presentation to display
     */
    public SlideViewerFrame(String title, Presentation presentation)
    {
        super(title);
        SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
        presentation.setShowView(slideViewerComponent);
        this.setupWindow(slideViewerComponent, presentation);
    }

    /**
     * Setup the GUI window.
     *
     * @param slideViewerComponent the component to display
     * @param presentation         the presentation
     */
    public void setupWindow(SlideViewerComponent slideViewerComponent, Presentation presentation)
    {
        this.setTitle(JABTITLE);
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        this.getContentPane().add(slideViewerComponent);
        // create commands and wire controllers
        Command next = new NextSlideCommand(presentation);
        Command prev = new PrevSlideCommand(presentation);
        Command exit = new ExitAppCommand();

        this.addKeyListener(new KeyController(next, prev, exit)); // add a controller
        this.setMenuBar(new MenuController(this, presentation, next, prev, exit));    // add another controller
        this.setSize(new Dimension(WIDTH, HEIGHT)); // Same sizes as Slide has.
        this.setVisible(true);
    }
}
