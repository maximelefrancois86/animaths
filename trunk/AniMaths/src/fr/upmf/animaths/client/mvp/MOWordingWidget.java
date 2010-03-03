package fr.upmf.animaths.client.mvp;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

import fr.upmf.animaths.client.mvp.MathML.MMLMath;
import fr.upmf.animaths.client.mvp.MathObject.MOElement;

/**
 * The wording of an exercise...
 * 
 * @author Maxime Lefrançois & Édouard Lopez
 *
 */
public class MOWordingWidget extends Composite {

	private ComplexPanel panel;

	public MOWordingWidget(ComplexPanel panel) {
		initWidget(panel);
		this.panel = panel;
	}
	
	public MOWordingWidget(Object... args) {
		panel = new FlowPanel();
		initWidget(panel);
		setWording(args);
	}
	
	public void setWording(Object... args) {
		clear();
		for(Object arg : args) {
			if(arg instanceof String)
				panel.add(new HTML((String) arg)); //		  panel.add(new InlineHTML((String) arg));
			else if(arg instanceof MOElement<?>) {
				MMLMath wrapper = new MMLMath(false);
				((MOElement<?>) arg).clone().pack(wrapper,null);
				panel.add(wrapper);
			}
			else
				throw new IllegalArgumentException("Problème d'arguments, "+
						"seuls les chaines de caractères et les objets mathématiques sont acceptés.");
		}
	}

	public void parse(Element element) {
//		clear();
//		NodeList children = element.getChildNodes();
//		for(int i=0;i<children.getLength();i++) {
//			Node n = children.item(i);
//			if(n.getNodeType() == Node.COMMENT_NODE)
//				panel.add(new InlineHTML(n.getNodeValue()));
//			if(n.getNodeType() == Node.ELEMENT_NODE) {
//				MMLMath wrapper = new MMLMath(false);
//				MOElement<?> mOElement = MOElement.parse((Element)n);
//				if(mOElement!=null)
//					mOElement.pack(wrapper,null);
//				panel.add(wrapper);
//			}
//		}
		clear();
		NodeList children = element.getChildNodes();
		for(int i=0;i<children.getLength();i++) {
			
			Node n = children.item(i);
			if(n.getNodeType() == Node.COMMENT_NODE)
				panel.add(new InlineHTML(n.getNodeValue()));
			if(n.getNodeType() == Node.ELEMENT_NODE) {
				MMLMath wrapper = new MMLMath(false);
				MOElement<?> mOElement = MOElement.parse((Element)n);
				if(mOElement!=null)
					mOElement.pack(wrapper,null);
				panel.add(wrapper);
			}
		}
	}

	
	
	public void clear() {
		panel.clear();
		while(panel.getElement().hasChildNodes())
			panel.getElement().getFirstChild().removeFromParent();
	}
	
}
