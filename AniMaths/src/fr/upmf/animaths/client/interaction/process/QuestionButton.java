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
import fr.upmf.animaths.client.mvp.MOWordingWidget;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;

public class QuestionButton extends DialogBox {


	private List<MOFocusWidget> answers = new ArrayList<MOFocusWidget>();
	VerticalPanel dialogVPanel = new VerticalPanel();
	MOAbstractProcess process;
	
	public QuestionButton(MOAbstractProcess process, MOWordingWidget wording) {
		super(false);
		this.process = process;
		setText("Question...");
		setAnimationEnabled(false);
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.getElement().setId("question-button");
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		setWidget(dialogVPanel);
		dialogVPanel.add(wording);
	}

	public void addAnswer(MOElement<?> element, int answer) {
		MOFocusWidget button = new MOFocusWidget(new MOBasicPresenter(element));
		button.addClickHandler(new AnswerClickHandler(answer));
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
	
	private class AnswerClickHandler implements ClickHandler {
		private int answer;
		public AnswerClickHandler(int answer) {
			this.answer = answer;
		}
		public void onClick(ClickEvent event) {
			process.executeProcess(answer);
			hide();
		}
	}

}
