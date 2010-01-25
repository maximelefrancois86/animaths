package org.w3c.dom.mathML;

import org.w3c.dom.DOMException;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.2 $
 */
public interface MathMLPiecewiseElement extends MathMLContentElement {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLNodeList getPieces();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLContentElement getOtherwise();

    /**
     * DOCUMENT ME!
     *
     * @param otherwise DOCUMENT ME!
     */
    public void setOtherwise(MathMLContentElement otherwise);

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     * @return DOCUMENT ME!
     */
    public MathMLCaseElement getCase(int index);

    /**
     * DOCUMENT ME!
     *
     * @param index   DOCUMENT ME!
     * @param newCase DOCUMENT ME!
     * @return DOCUMENT ME!
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLCaseElement setCase(int index, MathMLCaseElement newCase)
            throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     * @throws DOMException DOCUMENT ME!
     */
    public void deleteCase(int index) throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     * @return DOCUMENT ME!
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLCaseElement removeCase(int index) throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param index   DOCUMENT ME!
     * @param newCase DOCUMENT ME!
     * @return DOCUMENT ME!
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLCaseElement insertCase(int index, MathMLCaseElement newCase)
            throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     * @return DOCUMENT ME!
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLContentElement getCaseValue(int index)
            throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     * @param value DOCUMENT ME!
     * @return DOCUMENT ME!
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLContentElement setCaseValue(int index,
                                             MathMLContentElement value) throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     * @return DOCUMENT ME!
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLContentElement getCaseCondition(int index)
            throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param index     DOCUMENT ME!
     * @param condition DOCUMENT ME!
     * @return DOCUMENT ME!
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLContentElement setCaseCondition(int index,
                                                 MathMLContentElement condition) throws DOMException;
}
;
