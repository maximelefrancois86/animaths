package fr.upmf.animaths.client.mvp.widgets;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;

import fr.upmf.animaths.client.mvp.modele.MathObject.MathObjectElement;
import fr.upmf.animaths.client.mvp.widgets.MathML.MathMLMath;

public class StaticMathObjectWidget extends Composite implements HasValue<MathObjectElement>{

	private MathMLMath wrapper;
	private MathObjectElement mathObjectElement;

	public StaticMathObjectWidget() {
		wrapper = new MathMLMath(true);
		this.initWidget(wrapper);
	}
	
	@Override
	public MathObjectElement getValue() {
		return mathObjectElement;
	}

	@Override
	public void setValue(MathObjectElement value) {
		setValue(value,false);
	}

	@Override
	public void setValue(MathObjectElement value, boolean fireEvents) {
		if (value == null) {
	    	throw new IllegalArgumentException("value must not be null");
	    }
		this.mathObjectElement = value;
		value.pack(wrapper);
	    if (fireEvents) {
	      ValueChangeEvent.fire(this, value);
	    }
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<MathObjectElement> handler) {
	    return addHandler(handler, ValueChangeEvent.getType());
	}

}
