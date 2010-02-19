package fr.upmf.animaths.client.gin;

import java.util.List;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.inject.Inject;

import fr.upmf.animaths.client.mvp.AniMathsPresenter;
import fr.upmf.animaths.client.mvp.MODynamicPresenter;
import fr.upmf.animaths.client.mvp.MOStaticPresenter;
import fr.upmf.animaths.client.mvp.AniMathsPresenter.Display;
import fr.upmf.animaths.client.mvp.MathObject.MOAddContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOEquation;
import fr.upmf.animaths.client.mvp.MathObject.MOIdentifier;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyContainer;
import fr.upmf.animaths.client.mvp.MathObject.MOMultiplyElement;
import fr.upmf.animaths.client.mvp.MathObject.MONumber;
import fr.upmf.animaths.client.mvp.MathObject.MOSignedElement;

/**
 * Represents the main application
 * 
 * @author Maxime Lefran�ois
 * 
 */
public class AppPresenter {
	private HasWidgets container;
	private AniMathsPresenter aniMathsPresenter;

	// Ed
	private MODynamicPresenter mODynamicPresenter;
	public List<MOStaticPresenter> mOStaticPresenters;
	
	@Inject
	public AppPresenter(final AniMathsPresenter aniMathsPresenter) {
		this.aniMathsPresenter = aniMathsPresenter;
	}

	public void go(final HasWidgets container) {
		this.container = container;
		showMain();
	}

	private void showMain() {
		container.clear();

		//		buildEquation();
		container.add(aniMathsPresenter.getDisplay().asWidget());
	}

//	private void buildEquation() {
//		MOIdentifier x = new MOIdentifier("x");
//		MOEquation eq = new MOEquation();
//		eq
//				.setLeftHandSide(new MOAddContainer(new MOSignedElement(
//						new MOIdentifier("x"), true), new MOSignedElement(
//						new MONumber(10)), new MOSignedElement(
//						new MOMultiplyContainer(new MOMultiplyElement(
//								new MONumber(2)), new MOMultiplyElement(
//								new MOAddContainer(new MOSignedElement(
//										new MOIdentifier("x"), false),
//										new MOSignedElement(new MONumber(1),
//												true)))))));
//		eq.setRightHandSide(new MOMultiplyContainer(new MOMultiplyElement(
//				new MOSignedElement(new MOSignedElement(new MOSignedElement(
//						new MONumber(3), true), false), true)),
//				new MOMultiplyElement(new MOAddContainer(new MOSignedElement(
//						new MOMultiplyContainer(new MOMultiplyElement(
//								new MONumber(2)), new MOMultiplyElement(
//								new MOIdentifier("x")))), new MOSignedElement(
//						new MONumber(1))))));
//		Display display = aniMathsPresenter.getDisplay();
//
//		RootPanel.get("exerciseWording").add(exerciseWordingWidget);		
//		display.getExerciseWordingWidget().pack("Isoler ", x,
//				" dans l'équation ", eq);
//
//		mODynamicPresenter = new MODynamicPresenter();
//		mODynamicPresenter.setElement(eq);
//	}

}