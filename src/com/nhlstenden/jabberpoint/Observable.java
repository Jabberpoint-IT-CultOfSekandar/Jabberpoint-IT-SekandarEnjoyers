package com.nhlstenden.jabberpoint;

/**
 * The Observable interface for the Observer pattern.
 */
public interface Observable
{
    /**
     * Add an observer to this observable.
     *
     * @param observer the observer to add
     */
    void addObserver(com.nhlstenden.jabberpoint.Observer observer);

    /**
     * Remove an observer from this observable.
     *
     * @param observer the observer to remove
     */
    void removeObserver(com.nhlstenden.jabberpoint.Observer observer);

    /**
     * Notify the observer of a change.
     *
     * @param presentation the new presentation state
     */
    void notifyObserver(com.nhlstenden.jabberpoint.Presentation presentation);
}
