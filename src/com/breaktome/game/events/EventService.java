package com.breaktome.game.events;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO: Potential future optimizations
 *  - cache the methods in the trigger method so we dont have to continually do a lookup on them
 *  - support a distinction between event group types so that we dont have to loop all the listeners every time.
 *
 * TODO: Future improvements
 *  - support priority
 *
 */
public class EventService {

    private Set<IEventListener> eventListeners;

    public EventService() {
        eventListeners = new HashSet<>();
    }

    public void registerListener(IEventListener eventListener)
    {
        eventListeners.add(eventListener);
    }

    public void trigger(IEvent event)
    {
        for(IEventListener eventListener : eventListeners)
        {
            Method method;

            // Get the method from the listener
            try {
                method = eventListener.getClass().getMethod(event.getName(), event.getClass());
            } catch (SecurityException e) {
                e.printStackTrace();
                continue;
            } catch (NoSuchMethodException e) {
                continue;
            }

            // Execute the method
            try {
                method.invoke(eventListener, event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
