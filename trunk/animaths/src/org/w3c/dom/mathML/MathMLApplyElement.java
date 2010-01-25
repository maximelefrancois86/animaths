package org.w3c.dom.mathML;

import org.w3c.dom.DOMException;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.2 $
 */
public interface MathMLApplyElement extends MathMLContentContainer {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getOperator();

    /**
     * DOCUMENT ME!
     *
     * @param operator DOCUMENT ME!
     */
    public void setOperator(MathMLElement operator);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getLowLimit();

    /**
     * DOCUMENT ME!
     *
     * @param lowLimit DOCUMENT ME!
     * @throws DOMException DOCUMENT ME!
     */
    public void setLowLimit(MathMLElement lowLimit) throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getUpLimit();

    /**
     * DOCUMENT ME!
     *
     * @param upLimit DOCUMENT ME!
     * @throws DOMException DOCUMENT ME!
     */
    public void setUpLimit(MathMLElement upLimit) throws DOMException;
}
;
