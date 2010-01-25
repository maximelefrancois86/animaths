package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLSpaceElement;


/**
 * Implements a MathML space element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLSpaceElementImpl extends MathMLElementImpl
    implements MathMLSpaceElement {
/**
     * Constructs a MathML space element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLSpaceElementImpl(MathMLDocumentImpl owner, String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getWidth() {
        return getAttribute("width");
    }

    /**
     * DOCUMENT ME!
     *
     * @param width DOCUMENT ME!
     */
    public void setWidth(String width) {
        setAttribute("width", width);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getHeight() {
        return getAttribute("height");
    }

    /**
     * DOCUMENT ME!
     *
     * @param height DOCUMENT ME!
     */
    public void setHeight(String height) {
        setAttribute("height", height);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDepth() {
        return getAttribute("depth");
    }

    /**
     * DOCUMENT ME!
     *
     * @param depth DOCUMENT ME!
     */
    public void setDepth(String depth) {
        setAttribute("depth", depth);
    }
}
