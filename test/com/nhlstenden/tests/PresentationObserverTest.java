package com.nhlstenden.tests;

import com.nhlstenden.jabberpoint.*;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for Presentation observer functionality.
 */
public class PresentationObserverTest
{
    /**
     * An observer that records when updates are received.
     */
    private static class RecordingObserver implements Observer
    {
        int called;
        Presentation lastPresentation;

        @Override
        public void update(Presentation presentation)
        {
            this.called++;
            this.lastPresentation = presentation;
        }
    }

    @Test
    public void testAddAndNotifyObserverViaSetSlideNumber()
    {
        Presentation presentation = new Presentation();
        RecordingObserver observer = new RecordingObserver();

        presentation.addObserver(observer);
        presentation.setSlideNumber(2);

        assertEquals(1, observer.called);
        assertSame(presentation, observer.lastPresentation);
        assertEquals(2, presentation.getSlideNumber());
    }

    @Test
    public void testRemoveObserverStopsNotifications()
    {
        Presentation presentation = new Presentation();
        RecordingObserver observer = new RecordingObserver();

        presentation.addObserver(observer);
        presentation.removeObserver(observer);
        presentation.setSlideNumber(1);

        assertEquals(0, observer.called);
    }
}
