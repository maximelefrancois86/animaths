package fr.upmf.animaths.client.interaction.process;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import fr.upmf.animaths.client.mvp.MathWordingWidget;

public class MessageBox extends DialogBox {
	
	VerticalPanel mPanel = new VerticalPanel();
	HorizontalPanel hPanel = new HorizontalPanel();
	private MathWordingWidget message = new MathWordingWidget("<span>message absent.<span>");
	private HTML icon = new HTML(""); 

	public MessageBox() {
		super(false);
		setText("Message");
		setAnimationEnabled(false);

//		mPanel.setHorizontalAlignment(HorizontalmPanel);
		mPanel.getElement().setId("message");
		setWidget(mPanel);
		mPanel.add(hPanel);
		hPanel.add(icon);
		hPanel.add(message);
	}
	
	public void updateWidget(Widget w, Widget update) {
		hPanel.remove(w);
		hPanel.add(update);
		hPanel.setCellVerticalAlignment(update, VerticalPanel.ALIGN_MIDDLE);
	}
	
	public void setAsLoading(MathWordingWidget message) {
		setText("Chargement...");
		updateWidget(icon, new HTML("<img id='box-icon' src='images/loading.gif' alt='loading' />"));
		updateWidget(this.message, message);
		center();
	}
	
	public void setAsWarning(MathWordingWidget message) {
		setText("Alerte");
		updateWidget(icon, new HTML("<img id='box-icon' src='images/warning.png' alt='warning' />"));
		updateWidget(this.message, message);
		center();
	}
	
	public void setAsError(MathWordingWidget message) {
		setText("Erreur !");
		updateWidget(icon, new HTML("<img id='box-icon' src='images/error.png' alt='error' />"));
		updateWidget(this.message, message);
		center();
	}
		
	public void setAsCorrect(MathWordingWidget message) {
		setText("Correct !");
		updateWidget(icon, new HTML("<img id='box-icon' src='images/ok.png' alt='correct' />"));
		updateWidget(this.message, message);
		center();
	}

	public void setWithCode(int answer, MathWordingWidget message) {
		if(answer>0)
			setAsCorrect(message);
		else
			setAsWarning(message);
	}

	public void center() {
		super.center();
	}
	
	public void setMessage(MathWordingWidget message) {		
		this.message = message;
	}
	
	public void addCloseButton(ClickHandler clickHandler) {
		Button close = new Button("Fermer");
		mPanel.add(close);
		close.addClickHandler(clickHandler);
	}
	
	public void setAsLoading() {
		setAsLoading(new MathWordingWidget(""));
	}
	public void setAsWarning() {
		setAsWarning(new MathWordingWidget(""));
	}
	public void setAsError() {
		setAsError(new MathWordingWidget(""));
	}
	public void setAsCorrect() {
		setAsCorrect(new MathWordingWidget(""));
	}

	
}
