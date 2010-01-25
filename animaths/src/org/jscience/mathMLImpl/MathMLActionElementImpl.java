package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLActionElement;


/**
 * Implements a MathML action element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLActionElementImpl extends MathMLPresentationContainerImpl
    implements MathMLActionElement {
/**
     * Constructs a MathML action element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLActionElementImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getActiontype() {
        return getAttribute("actiontype");
    }

    /**
     * DOCUMENT ME!
     *
     * @param actiontype DOCUMENT ME!
     */
    public void setActiontype(String actiontype) {
        setAttribute("actiontype", actiontype);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSelection() {
        return getAttribute("selection");
    }

    /**
     * DOCUMENT ME!
     *
     * @param selection DOCUMENT ME!
     */
    public void setSelection(String selection) {
        setAttribute("selection", selection);
    }
}
