package com.nhlstenden.jabberpoint;

/**
 * The Observer interface for the Observer pattern.
 */
public interface Observer
{
    /**
     * Update this observer with new presentation state.
     *
     * @param presentation the new presentation state
     */
    void update(com.nhlstenden.jabberpoint.Presentation presentation);
}
