package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLTendsToElement;


/**
 * Implements a MathML <code>tendsto</code> element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLTendsToElementImpl extends MathMLPredefinedSymbolImpl
    implements MathMLTendsToElement {
/**
     * Constructs a MathML <code>tendsto</code> element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLTendsToElementImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
        super(owner, qualifiedName);
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
