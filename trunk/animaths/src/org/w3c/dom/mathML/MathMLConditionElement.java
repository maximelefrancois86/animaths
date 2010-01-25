package org.w3c.dom.mathML;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLConditionElement extends MathMLContentElement {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLApplyElement getCondition();

    /**
     * DOCUMENT ME!
     *
     * @param condition DOCUMENT ME!
     */
    public void setCondition(MathMLApplyElement condition);
}
;
