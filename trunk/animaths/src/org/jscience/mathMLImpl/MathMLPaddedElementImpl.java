package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLPaddedElement;


/**
 * Implements a MathML padded element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLPaddedElementImpl extends MathMLPresentationContainerImpl
    implements MathMLPaddedElement {
/**
     * Constructs a MathML padded element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLPaddedElementImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
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
    public String getLspace() {
        return getAttribute("lspace");
    }

    /**
     * DOCUMENT ME!
     *
     * @param lspace DOCUMENT ME!
     */
    public void setLspace(String lspace) {
        setAttribute("lspace", lspace);
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
