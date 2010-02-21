package fr.upmf.animaths.client.interaction;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class QuestionDisplay extends DialogBox implements QuestionPresenter.Display {
	
	public QuestionDisplay() {
		super(false);
		this.setText("Quelle sera le résultat de cette manipulation ?");
		this.setAnimationEnabled(true);
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		this.setWidget(dialogVPanel);
	}

	@Override
	public void initializeAndCenter() {
		this.center();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void startProcessing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopProcessing() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		// TODO Auto-generated method stub
		
	}

}
