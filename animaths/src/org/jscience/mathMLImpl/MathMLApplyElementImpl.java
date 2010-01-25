package org.jscience.mathMLImpl;

import org.w3c.dom.DOMException;
import org.w3c.dom.mathML.MathMLApplyElement;
import org.w3c.dom.mathML.MathMLElement;


/**
 * Implements a MathML <code>apply</code> element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLApplyElementImpl extends MathMLContentContainerImpl
    implements MathMLApplyElement {
/**
     * Constructs a MathML <code>apply</code> element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLApplyElementImpl(MathMLDocumentImpl owner, String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getOperator() {
        return (MathMLElement) getFirstChild();
    }

    /**
     * DOCUMENT ME!
     *
     * @param operator DOCUMENT ME!
     */
    public void setOperator(MathMLElement operator) {
        replaceChild(operator, getFirstChild());
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getLowLimit() {
        return (MathMLElement) getNodeByName("lowlimit");
    }

    /**
     * DOCUMENT ME!
     *
     * @param lowlimit DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public void setLowLimit(MathMLElement lowlimit) throws DOMException {
        setNodeByName(lowlimit, "lowlimit");
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getUpLimit() {
        return (MathMLElement) getNodeByName("uplimit");
    }

    /**
     * DOCUMENT ME!
     *
     * @param uplimit DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public void setUpLimit(MathMLElement uplimit) throws DOMException {
        setNodeByName(uplimit, "uplimit");
    }
}
