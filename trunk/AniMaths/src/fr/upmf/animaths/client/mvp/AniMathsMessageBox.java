package fr.upmf.animaths.client.mvp;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;


public class AniMathsMessageBox extends DialogBox {
	
	VerticalPanel mPanel = new VerticalPanel();
	HorizontalPanel hPanel = new HorizontalPanel();
	private MOWordingWidget message = new MOWordingWidget("<span>message absent.<span>");
	private HTML icon = new HTML(""); 

	public AniMathsMessageBox() {
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
	
	public void setAsStart(String html, String buttonText, ClickHandler handler) {
		setText("Bienvenue dans l'application AniMaths");
		hPanel.remove(message);
		hPanel.add(new HTML(html));
		addButton(buttonText, handler, true).addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		center();
	}
	
	public void setAsLoading(MOWordingWidget message) {
		setText("Chargement...");
		updateWidget(icon, new HTML("<img id='box-icon' src='images/loading.gif' alt='loading' />"));
		updateWidget(this.message, message);
		center();
	}
	
	public void setAsWarning(MOWordingWidget message) {
		setText("Alerte");
		updateWidget(icon, new HTML("<img id='box-icon' src='images/warning.png' alt='warning' />"));
		updateWidget(this.message, message);
		center();
	}
	
	public void setAsError(MOWordingWidget message) {
		setText("Erreur !");
		updateWidget(icon, new HTML("<img id='box-icon' src='images/error.png' alt='error' />"));
		updateWidget(this.message, message);
		center();
	}
		
	public void setAsCorrect(MOWordingWidget message) {
		setText("Correct !");
		updateWidget(icon, new HTML("<img id='box-icon' src='images/ok.png' alt='correct' />"));
		updateWidget(this.message, message);
		center();
	}

	public void setWithCode(int answer, MOWordingWidget message) {
		if(answer>0)
			setAsCorrect(message);
		else
			setAsWarning(message);
	}

	public void center() {
		super.center();
	}
	
	public void setMessage(MOWordingWidget message) {		
		this.message = message;
	}
	
	public Button addButton(String text, ClickHandler clickHandler, boolean addNativeEvent) {
		final Button button = new Button(text, clickHandler);
		mPanel.add(button);
		if(addNativeEvent) {
			Event.addNativePreviewHandler(new NativePreviewHandler() {
			 	public void onPreviewNativeEvent(NativePreviewEvent event) {
			 		if(event.getTypeInt() == Event.ONKEYUP && event.getNativeEvent().getKeyCode()==13)
			 			button.click();
			 	}
			});
		}
		return button;
	}
	
	public void setAsLoading() {
		setAsLoading(new MOWordingWidget(""));
	}
	public void setAsWarning() {
		setAsWarning(new MOWordingWidget(""));
	}
	public void setAsError() {
		setAsError(new MOWordingWidget(""));
	}
	public void setAsCorrect() {
		setAsCorrect(new MOWordingWidget(""));
	}

	
}
