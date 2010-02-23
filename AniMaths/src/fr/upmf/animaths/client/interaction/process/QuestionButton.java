package fr.upmf.animaths.client.interaction.process;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.upmf.animaths.client.mvp.MOBasicPresenter;
import fr.upmf.animaths.client.mvp.MOFocusWidget;
import fr.upmf.animaths.client.mvp.MathWordingWidget;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;

public class QuestionButton extends DialogBox {


	private List<MOFocusWidget> answers = new ArrayList<MOFocusWidget>();
	VerticalPanel dialogVPanel = new VerticalPanel();
	MOAbstractProcess process;
	
	public QuestionButton(MOAbstractProcess process, MathWordingWidget wording) {
		super(false);
		this.process = process;
		setText("Question...");
		setAnimationEnabled(true);
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		setWidget(dialogVPanel);
		dialogVPanel.add(wording);
	}

	public void addAnswer(MOElement<?> element, boolean goodAnswer) {
		MOFocusWidget button = new MOFocusWidget(new MOBasicPresenter(element));
		if(goodAnswer)
			button.addClickHandler(new CorrectAnswerClickHandler());
		else
			button.addClickHandler(new WrongAnswerClickHandler());
		answers.add(button);
	}
	
	public void center() {
		int i = (int)(Math.random()*answers.size());
		dialogVPanel.add(answers.get(i));
		answers.remove(i);
		while(answers.size()!=0) {
			i = (int)(Math.random()*answers.size());
			dialogVPanel.add(new Label("ou"));
			dialogVPanel.add(answers.get(i));
			answers.remove(i);
		}
		super.center();
	}
	
	private class WrongAnswerClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			hide();
		}
	}

	private class CorrectAnswerClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {			hide();
			process.executeProcess();
		}
	}

}
