package fr.upmf.animaths.client.mvp;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.upmf.animaths.client.interaction.MOAbstractProcess;

public class QuestionTextBox extends DialogBox {


	VerticalPanel dialogVPanel = new VerticalPanel();
	final TextBox answerField;
	final Button sendButton;
	
	public QuestionTextBox(final MOAbstractProcess process, MOWordingWidget wording, final String answer) {
		super(false);
		setText("Question...");
		setAnimationEnabled(true);
		dialogVPanel.getElement().setId("question-text");
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		setWidget(dialogVPanel);
		dialogVPanel.add(wording);

		answerField = new TextBox();
		answerField.setText("");
		dialogVPanel.add(answerField);

		sendButton = new Button("Valider");
		dialogVPanel.add(sendButton);
		sendButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				hide();
				try {
					if(Float.parseFloat(answerField.getText())==Float.parseFloat(answer))				
						process.executeProcess(1);
					else if(Float.parseFloat(answerField.getText())==-Float.parseFloat(answer))
						process.executeProcess(0);
					else
						process.executeProcess(-1);
				} catch(NumberFormatException e) {
					process.executeProcess(-2);
				}
			}
		});
		answerField.addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					hide();
					try{
						Float f = Float.parseFloat(answerField.getText());
						if(f==Float.parseFloat(answer))				
							process.executeProcess(1);
						else if(Float.parseFloat(answerField.getText())==-Float.parseFloat(answer))
							process.executeProcess(0);
						else
							process.executeProcess(-1);
					}catch(NumberFormatException e) {
						process.executeProcess(-2);
					}
				}
			}
		});
	}

	public void center() {
		super.center();
		answerField.setFocus(true);
	}
	
}
