//package fr.upmf.animaths.client.interaction.process.core;
//
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.DialogBox;
//import com.google.gwt.user.client.ui.FocusWidget;
//import com.google.gwt.user.client.ui.HTML;
//import com.google.gwt.user.client.ui.VerticalPanel;
//
//import fr.upmf.animaths.client.interaction.process.MOAbstractProcess;
//import fr.upmf.animaths.client.mvp.MOBasicPresenter;
//import fr.upmf.animaths.client.mvp.MOFocusWidget;
//import fr.upmf.animaths.client.mvp.MathObject.MOElement;
//import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyElement;
//import fr.upmf.animaths.client.mvp.MathObject.MOSignedElement;
//
//public final class SEs_SEs_ChangeSign extends MOAbstractProcess{
//
//	private static final SEs_SEs_ChangeSign instance = new SEs_SEs_ChangeSign();
//	protected SEs_SEs_ChangeSign() {}
//	public static void setEnabled() {
//		MOAbstractProcess.setEnabled(instance);
//	}
//
//	@Override
//	protected boolean isProcessInvolved() {
//		if(selectedElement instanceof MOSignedElement) {
//			if(((MOSignedElement) selectedElement).getChild() instanceof MOSignedElement)
//				return true;
//			if(((MOSignedElement) selectedElement).getMathObjectParent() instanceof MOSignedElement)
//				return true;
//		}
//		if(selectedElement instanceof MOMultiplyElement) {
//			MOElement<?> child = ((MOMultiplyElement) selectedElement).getChild();
//			if(child instanceof MOSignedElement) {
//				MOElement<?> greatChild = ((MOSignedElement) child).getChild();
//				if(greatChild instanceof MOSignedElement) {
//					selectedElement = child;
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	@Override
//	protected short getTagOfProcess() {
//		if(whereElement instanceof MOSignedElement) {
//			MOSignedElement where = (MOSignedElement) whereElement;
//			if(where.getChild()==selectedElement || where.getMathObjectParent()==selectedElement) {
//				return PROCESS_CAUTION;
//			}
//		}
//		return PROCESS_NO;
//	}
//
//	@Override
//	protected void executeProcess() {
//		assert whereElement instanceof MOSignedElement;
//		assert selectedElement instanceof MOSignedElement;
//		
//		final DialogBox question  = new DialogBox(false);
//		question.setText("Quelle sera le résultat de cette manipulation ?");
//		question.setAnimationEnabled(true);
//		VerticalPanel dialogVPanel = new VerticalPanel();
//		dialogVPanel.addStyleName("dialogVPanel");
//
//		MOFocusWidget button1 = new MOFocusWidget(new MOBasicPresenter(whereElement.clone()));
//		MOFocusWidget button2 = new MOFocusWidget(new MOBasicPresenter(selectedElement.clone()));
//		
//		dialogVPanel.add(button1);
//		dialogVPanel.add(button2);
//
//		button1.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				question.hide();
//			}
//		});
//		button2.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				question.hide();
//				MOSignedElement where = (MOSignedElement) whereElement;
//				MOSignedElement selected = (MOSignedElement) selectedElement;
//				if(where.getChild()==selected) {
//					where.setMinus(where.isMinus()^selected.isMinus());
//					where.setChild(selected.getChild());
//				}	
//				else if(where.getMathObjectParent()==selected) {
//					selected.setMinus(where.isMinus()^selected.isMinus());
//					selected.setChild(where.getChild());
//				}
//			}
//		});
//		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
//		question.setWidget(dialogVPanel);
//		question.center();
//	}
//
//	@Override
//	public boolean askQuestion() {
//		return false;
//	}
//
//}
