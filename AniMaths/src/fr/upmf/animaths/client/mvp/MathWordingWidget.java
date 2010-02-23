package fr.upmf.animaths.client.mvp;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;

import fr.upmf.animaths.client.mvp.MathML.MMLMath;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;

/**
 * The wording of an exercise...
 * 
 * @author Maxime Lefran�ois
 *
 */
public class MathWordingWidget extends Composite {

	private ComplexPanel panel;

	public MathWordingWidget(ComplexPanel panel) {
		initWidget(panel);
		this.panel = panel;
	}
	
	public void setWording(Object... args) {
		clear();
		for(Object arg : args) {
			if(arg instanceof String)
				panel.getElement().appendChild((new Label((String) arg).getElement()).getChildNodes().getItem(0));
			else if(arg instanceof MOElement<?>) {
				MMLMath wrapper = new MMLMath(false);
				((MOElement<?>) arg).clone().pack(wrapper,null);
				panel.add(wrapper);
			}
			else
				throw new IllegalArgumentException("Probl�me d'arguments, "+
						"seuls les chaines de caract�res et les objets math�matiques sont accept�s.");
		}
	}

	public void clear() {
		panel.clear();
		while(panel.getElement().hasChildNodes())
			panel.getElement().getFirstChild().removeFromParent();
	}

}
