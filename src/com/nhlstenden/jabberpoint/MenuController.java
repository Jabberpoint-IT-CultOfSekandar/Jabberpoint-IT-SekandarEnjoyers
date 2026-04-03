package com.nhlstenden.jabberpoint;

import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * <p>The controller for the menu.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuController extends MenuBar
{
    private Frame parent; // the frame, only used as parent for the Dialogs
    private Presentation presentation; // Commands are given to the presentation
    private final Command nextCommand;
    private final Command prevCommand;
    private final Command exitCommand;

    private static final long serialVersionUID = 227L;

    protected static final String ABOUT = "About";
    protected static final String FILE = "File";
    protected static final String EXIT = "Exit";
    protected static final String GOTO = "Go to";
    protected static final String HELP = "Help";
    protected static final String NEW = "New";
    protected static final String NEXT = "Next";
    protected static final String OPEN = "Open";
    protected static final String PAGENR = "Page number?";
    protected static final String PREV = "Prev";
    protected static final String SAVE = "Save";
    protected static final String VIEW = "View";

    protected static final String TESTFILE = "test.xml";
    protected static final String SAVEFILE = "dump.xml";

    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Load Error";
    protected static final String SAVEERR = "Save Error";

    /**
     * Constructor for MenuController.
     *
     * @param frame       the parent frame
     * @param pres        the presentation
     * @param nextCommand the next slide command
     * @param prevCommand the previous slide command
     * @param exitCommand the exit command
     */
    public MenuController(Frame frame, Presentation pres, Command nextCommand,
                         Command prevCommand, Command exitCommand)
    {
        this.parent = frame;
        this.presentation = pres;
        this.nextCommand = nextCommand;
        this.prevCommand = prevCommand;
        this.exitCommand = exitCommand;
        MenuItem menuItem;

        Menu fileMenu = new Menu(FILE);
        fileMenu.add(menuItem = this.mkMenuItem(OPEN));
        menuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                MenuController.this.presentation.clear();
                Accessor xmlAccessor = new XMLAccessor();
                try
                {
                    xmlAccessor.loadFile(MenuController.this.presentation, TESTFILE);
                    MenuController.this.presentation.setSlideNumber(0);
                }
                catch (IOException exc)
                {
                    JOptionPane.showMessageDialog(MenuController.this.parent, IOEX + exc,
                            LOADERR, JOptionPane.ERROR_MESSAGE);
                }
                MenuController.this.parent.repaint();
            }
        });
        fileMenu.add(menuItem = this.mkMenuItem(NEW));
        menuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                MenuController.this.presentation.clear();
                MenuController.this.parent.repaint();
            }
        });
        fileMenu.add(menuItem = this.mkMenuItem(SAVE));
        menuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Accessor xmlAccessor = new XMLAccessor();
                try
                {
                    xmlAccessor.saveFile(MenuController.this.presentation, SAVEFILE);
                }
                catch (IOException exc)
                {
                    JOptionPane.showMessageDialog(MenuController.this.parent, IOEX + exc,
                            SAVEERR, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fileMenu.addSeparator();
        fileMenu.add(menuItem = this.mkMenuItem(EXIT));
        menuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                if (MenuController.this.exitCommand != null)
                {
                    MenuController.this.exitCommand.execute();
                }
            }
        });
        this.add(fileMenu);

        Menu viewMenu = new Menu(VIEW);
        viewMenu.add(menuItem = this.mkMenuItem(NEXT));
        menuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                if (MenuController.this.nextCommand != null)
                {
                    MenuController.this.nextCommand.execute();
                }
            }
        });
        viewMenu.add(menuItem = this.mkMenuItem(PREV));
        menuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                if (MenuController.this.prevCommand != null)
                {
                    MenuController.this.prevCommand.execute();
                }
            }
        });
        viewMenu.add(menuItem = this.mkMenuItem(GOTO));
        menuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                String pageNumberStr = JOptionPane.showInputDialog((Object) PAGENR);
                int pageNumber = Integer.parseInt(pageNumberStr);
                Command gotoCmd = new GotoSlideCommand(MenuController.this.presentation, pageNumber - 1);
                gotoCmd.execute();
            }
        });
        this.add(viewMenu);

        Menu helpMenu = new Menu(HELP);
        helpMenu.add(menuItem = this.mkMenuItem(ABOUT));
        menuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                AboutBox.show(MenuController.this.parent);
            }
        });
        this.setHelpMenu(helpMenu);        // needed for portability (Motif, etc.).
    }

    /**
     * Create a menu item.
     *
     * @param name the name of the menu item
     * @return the created MenuItem
     */
    public MenuItem mkMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
