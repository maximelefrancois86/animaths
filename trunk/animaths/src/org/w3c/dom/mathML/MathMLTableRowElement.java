package org.w3c.dom.mathML;

import org.w3c.dom.DOMException;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLTableRowElement extends MathMLPresentationElement {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getRowalign();

    /**
     * DOCUMENT ME!
     *
     * @param rowalign DOCUMENT ME!
     */
    public void setRowalign(String rowalign);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getColumnalign();

    /**
     * DOCUMENT ME!
     *
     * @param columnalign DOCUMENT ME!
     */
    public void setColumnalign(String columnalign);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getGroupalign();

    /**
     * DOCUMENT ME!
     *
     * @param groupalign DOCUMENT ME!
     */
    public void setGroupalign(String groupalign);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLNodeList getCells();

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLTableCellElement insertEmptyCell(int index)
        throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param newCell DOCUMENT ME!
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLTableCellElement insertCell(MathMLTableCellElement newCell,
        int index) throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param newCell DOCUMENT ME!
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLTableCellElement setCell(MathMLTableCellElement newCell,
        int index);

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     */
    public void deleteCell(int index);
}
;
