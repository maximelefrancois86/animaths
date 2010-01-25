package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLAlignGroupElement;


/**
 * Implements a MathML align group element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLAlignGroupElementImpl extends MathMLElementImpl
    implements MathMLAlignGroupElement {
/**
     * Constructs a MathML align group element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLAlignGroupElementImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getGroupalign() {
        return getAttribute("groupalign");
    }

    /**
     * DOCUMENT ME!
     *
     * @param groupalign DOCUMENT ME!
     */
    public void setGroupalign(String groupalign) {
        setAttribute("groupalign", groupalign);
    }
}
