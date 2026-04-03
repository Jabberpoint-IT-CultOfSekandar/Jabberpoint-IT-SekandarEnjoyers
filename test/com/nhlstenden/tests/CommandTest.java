package com.nhlstenden.tests;

import com.nhlstenden.jabberpoint.*;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Tests for Command implementations.
 */
public class CommandTest
{
    @Test
    public void testNextPrevCommands()
    {
        Presentation p = new Presentation();
        p.append(new Slide());
        p.append(new Slide());
        p.setSlideNumber(0);
        Command next = new NextSlideCommand(p);
        Command prev = new PrevSlideCommand(p);
        next.execute();
        assertEquals(1, p.getSlideNumber());
        prev.execute();
        assertEquals(0, p.getSlideNumber());
    }

    @Test
    public void testGotoSlideCommand()
    {
        Presentation p = new Presentation();
        p.append(new Slide());
        p.append(new Slide());
        Command goto1 = new GotoSlideCommand(p, 1);
        goto1.execute();
        assertEquals(1, p.getSlideNumber());
    }

    @Test
    public void testExitAppCommandRunsRunnable()
    {
        AtomicBoolean ran = new AtomicBoolean(false);
        Command exit = new ExitAppCommand(() -> ran.set(true));
        exit.execute();
        assertTrue(ran.get());
    }
}
