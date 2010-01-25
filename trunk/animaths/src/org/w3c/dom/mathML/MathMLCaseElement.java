package org.w3c.dom.mathML;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLCaseElement extends MathMLContentElement {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLContentElement getCaseCondition();

    /**
     * DOCUMENT ME!
     *
     * @param caseCondition DOCUMENT ME!
     */
    public void setCaseCondition(MathMLContentElement caseCondition);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLContentElement getCaseValue();

    /**
     * DOCUMENT ME!
     *
     * @param caseValue DOCUMENT ME!
     */
    public void setCaseValue(MathMLContentElement caseValue);
}
;
