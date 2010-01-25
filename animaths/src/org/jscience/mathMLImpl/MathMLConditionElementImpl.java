package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLApplyElement;
import org.w3c.dom.mathML.MathMLConditionElement;


/**
 * Implements a MathML condition element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLConditionElementImpl extends MathMLElementImpl
    implements MathMLConditionElement {
/**
     * Constructs a MathML condition element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLConditionElementImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLApplyElement getCondition() {
        return (MathMLApplyElement) getFirstChild();
    }

    /**
     * DOCUMENT ME!
     *
     * @param condition DOCUMENT ME!
     */
    public void setCondition(MathMLApplyElement condition) {
        replaceChild(condition, getFirstChild());
    }
}
