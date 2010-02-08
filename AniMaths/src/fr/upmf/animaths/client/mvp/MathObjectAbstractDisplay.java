package fr.upmf.animaths.client.mvp;

import fr.upmf.animaths.client.mvp.MathML.MathMLMath;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;

public interface MathObjectAbstractDisplay extends WidgetDisplay {

	public MathMLMath getWrapper();
	public void clearWrapper();
}
