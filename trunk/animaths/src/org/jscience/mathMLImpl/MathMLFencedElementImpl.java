package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLFencedElement;


/**
 * Implements a MathML fenced element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLFencedElementImpl extends MathMLPresentationContainerImpl
    implements MathMLFencedElement {
/**
     * Constructs a MathML fenced element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLFencedElementImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getOpen() {
        return getAttribute("open");
    }

    /**
     * DOCUMENT ME!
     *
     * @param open DOCUMENT ME!
     */
    public void setOpen(String open) {
        setAttribute("open", open);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getClose() {
        return getAttribute("close");
    }

    /**
     * DOCUMENT ME!
     *
     * @param close DOCUMENT ME!
     */
    public void setClose(String close) {
        setAttribute("close", close);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSeparators() {
        return getAttribute("separators");
    }

    /**
     * DOCUMENT ME!
     *
     * @param separators DOCUMENT ME!
     */
    public void setSeparators(String separators) {
        setAttribute("separators", separators);
    }
}
