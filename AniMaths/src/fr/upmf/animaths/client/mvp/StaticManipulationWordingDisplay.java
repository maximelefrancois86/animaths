package fr.upmf.animaths.client.mvp;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasValue;

public class StaticManipulationWordingDisplay extends Composite implements HasValue<MathWordingWidget> {
	
	private MathWordingWidget manipulationWordingWidget;
	private Button replay;

	public StaticManipulationWordingDisplay() {
		FlowPanel panel = new FlowPanel();

		manipulationWordingWidget = new MathWordingWidget();
		panel.add(manipulationWordingWidget);
		
		replay = new Button("Rejouer");
		panel.add(replay);

		this.initWidget(panel);
	}
	
	@Override
	public MathWordingWidget getValue() {
		return manipulationWordingWidget;
	}

	@Override
	public void setValue(MathWordingWidget value) {
		setValue(value,false);
	}

	@Override
	public void setValue(MathWordingWidget value, boolean fireEvents) {
		if (value == null) {
	    	throw new IllegalArgumentException("value must not be null");
	    }
		this.manipulationWordingWidget = value;
	    if (fireEvents) {
	      ValueChangeEvent.fire(this, value);
	    }
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<MathWordingWidget> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}
	
	

}
