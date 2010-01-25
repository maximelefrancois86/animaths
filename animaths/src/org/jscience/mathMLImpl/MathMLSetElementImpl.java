package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLBvarElement;
import org.w3c.dom.mathML.MathMLSetElement;


/**
 * Implements a MathML <code>set</code> element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLSetElementImpl extends MathMLContentContainerImpl
    implements MathMLSetElement {
/**
     * Constructs a MathML <code>set</code> element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLSetElementImpl(MathMLDocumentImpl owner, String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean getIsExplicit() {
        return !(getFirstChild() instanceof MathMLBvarElement);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getType() {
        return getAttribute("type");
    }

    /**
     * DOCUMENT ME!
     *
     * @param type DOCUMENT ME!
     */
    public void setType(String type) {
        setAttribute("type", type);
    }
}
