package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLStringLitElement;


/**
 * Implements a MathML string literal element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLStringLitElementImpl extends MathMLPresentationTokenImpl
    implements MathMLStringLitElement {
/**
     * Constructs a MathML string literal element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLStringLitElementImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getLquote() {
        return getAttribute("lquote");
    }

    /**
     * DOCUMENT ME!
     *
     * @param lquote DOCUMENT ME!
     */
    public void setLquote(String lquote) {
        setAttribute("lquote", lquote);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getRquote() {
        return getAttribute("rquote");
    }

    /**
     * DOCUMENT ME!
     *
     * @param rquote DOCUMENT ME!
     */
    public void setRquote(String rquote) {
        setAttribute("rquote", rquote);
    }
}
