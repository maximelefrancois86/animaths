package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLElement;
import org.w3c.dom.mathML.MathMLRadicalElement;


/**
 * Implements a MathML radical element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLRadicalElementImpl extends MathMLElementImpl
    implements MathMLRadicalElement {
/**
     * Constructs a MathML radical element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLRadicalElementImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getRadicand() {
        return (MathMLElement) getFirstChild();
    }

    /**
     * DOCUMENT ME!
     *
     * @param radicand DOCUMENT ME!
     */
    public void setRadicand(MathMLElement radicand) {
        replaceChild(radicand, getFirstChild());
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getIndex() {
        if (getLocalName().equals("msqrt")) {
            return null;
        }

        return (MathMLElement) item(1);
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     */
    public void setIndex(MathMLElement index) {
        replaceChild(index, item(1));
    }
}
