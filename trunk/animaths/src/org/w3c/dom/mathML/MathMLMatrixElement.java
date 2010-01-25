package org.w3c.dom.mathML;

import org.w3c.dom.DOMException;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLMatrixElement extends MathMLContentElement {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getNrows();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getNcols();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLNodeList getRows();

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLMatrixrowElement getRow(int index) throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param newRow DOCUMENT ME!
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLMatrixrowElement insertRow(MathMLMatrixrowElement newRow,
        int index) throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param newRow DOCUMENT ME!
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLMatrixrowElement setRow(MathMLMatrixrowElement newRow,
        int index) throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public void deleteRow(int index) throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLMatrixrowElement removeRow(int index)
        throws DOMException;
}
;
