package fr.upmf.animaths.client.interaction.process;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MessageBox extends DialogBox {
	
	VerticalPanel Panel = new VerticalPanel();
	HorizontalPanel HPanel = new HorizontalPanel();
	private HTML textMessage = new HTML("<span>message absent.<span>");
	private HTML icon = new HTML(""); 

	public MessageBox() {
		super(false);
		setText("Message");
		setAnimationEnabled(true);
				
//		Panel.setHorizontalAlignment(HorizontalPanel);
		Panel.getElement().setId("message");
		setWidget(Panel);
		Panel.add(HPanel);
		HPanel.add(textMessage);
		HPanel.add(icon);
	}
	
	public void updateWidget(Widget w, Widget update) {
		HPanel.remove(w);
		HPanel.add(update);
	}
	
	public void setAsLoading(String msg) {
		setText("Chargement...");
		updateWidget(icon, new HTML("<img id='box-icon' src='images/loading.gif' alt='loading' />"));
		updateWidget(textMessage, new HTML(msg));
		center();
	}
	
	public void setAsWarning(String msg) {
		setText("Alerte");
		updateWidget(icon, new HTML("<img id='box-icon' src='images/warning.png' alt='warning' />"));
		updateWidget(textMessage, new HTML(msg));
		center();
	}
	
	public void setAsError(String msg) {
		setText("Erreur !");
		updateWidget(icon, new HTML("<img id='box-icon' src='images/error.png' alt='error' />"));
		updateWidget(textMessage, new HTML(msg));
		addCloseButton();
		center();
	}
		
	public void setAsCorrect(String msg) {
		setText("Correct !");
		updateWidget(icon, new HTML("<img id='box-icon' src='images/ok.png' alt='warning' />"));
		updateWidget(textMessage, new HTML(msg));
		center();
	}

	public void center() {
		super.center();
	}
	
	public void setTextMessage(String textMessage) {		
		this.textMessage = new HTML(textMessage);
	}
	
	public void addCloseButton() {
		Button close = new Button("Fermer");
		Panel.add(close);
		close.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
			}
		});
	}
	
	public void setAsLoading() {
		setAsLoading("");
	}
	public void setAsWarning() {
		setAsWarning("");
	}
	public void setAsError() {
		setAsError("");
	}
	public void setAsCorrect() {
		setAsCorrect("");
	}

	
}
