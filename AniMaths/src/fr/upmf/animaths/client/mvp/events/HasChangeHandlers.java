package fr.upmf.animaths.client.mvp.events;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

/**
 * A widget that implements this interface is a public source of
 * {@link ValueChangeEvent} events.
 *
 * @param <I> the value about to be changed
 */
public interface HasChangeHandlers extends HasHandlers {
  HandlerRegistration addChangeHandler(ChangeHandler handler);
}
