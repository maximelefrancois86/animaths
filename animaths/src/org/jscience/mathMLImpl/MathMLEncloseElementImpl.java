package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLEncloseElement;


/**
 * Implements a MathML enclose element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLEncloseElementImpl extends MathMLPresentationContainerImpl
    implements MathMLEncloseElement {
/**
     * Constructs a MathML enclose element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLEncloseElementImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getNotation() {
        return getAttribute("notation");
    }

    /**
     * DOCUMENT ME!
     *
     * @param notation DOCUMENT ME!
     */
    public void setNotation(String notation) {
        setAttribute("notation", notation);
    }
}
