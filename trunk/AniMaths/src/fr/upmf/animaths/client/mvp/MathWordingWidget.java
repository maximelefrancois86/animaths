package fr.upmf.animaths.client.mvp;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import fr.upmf.animaths.client.mvp.MathML.MMLMath;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;

/**
 * The wording of an exercise...
 * 
 * @author Maxime Lefran�ois
 *
 */
public class MathWordingWidget extends Composite {

	private RootPanel rootPanel;

	public MathWordingWidget(RootPanel rootPanel) {
		initWidget(rootPanel);
		this.rootPanel = rootPanel;
	}
	
	public void setWording(Object... args) {
		clear();
		for(Object arg : args) {
			if(arg instanceof String)
				rootPanel.getElement().appendChild((new Label((String) arg).getElement()).getChildNodes().getItem(0));
			else if(arg instanceof MOElement<?>) {
				MMLMath wrapper = new MMLMath(false);
				((MOElement<?>) arg).clone().pack(wrapper,null);
				rootPanel.add(wrapper);
			}
			else
				throw new IllegalArgumentException("Probl�me d'arguments, "+
						"seuls les chaines de caract�res et les objets math�matiques sont accept�s.");
		}
	}

	public void clear() {
		rootPanel.clear();
		while(rootPanel.getElement().hasChildNodes())
			rootPanel.getElement().removeChild(rootPanel.getElement().getFirstChild());
	}

}
