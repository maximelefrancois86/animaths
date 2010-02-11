package fr.upmf.animaths.client.mvp;

import fr.upmf.animaths.client.mvp.MathML.MMLMath;
import net.customware.gwt.presenter.client.widget.WidgetDisplay;

public interface MOAbstractDisplay extends WidgetDisplay {

	public MMLMath getWrapper();
	public void clearWrapper();
}
