package org.w3c.dom.mathML;

import org.w3c.dom.DOMException;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLTableElement extends MathMLPresentationElement {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getAlign();

    /**
     * DOCUMENT ME!
     *
     * @param align DOCUMENT ME!
     */
    public void setAlign(String align);

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
    public String getAlignmentscope();

    /**
     * DOCUMENT ME!
     *
     * @param alignmentscope DOCUMENT ME!
     */
    public void setAlignmentscope(String alignmentscope);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getColumnwidth();

    /**
     * DOCUMENT ME!
     *
     * @param columnwidth DOCUMENT ME!
     */
    public void setColumnwidth(String columnwidth);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getWidth();

    /**
     * DOCUMENT ME!
     *
     * @param width DOCUMENT ME!
     */
    public void setWidth(String width);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getRowspacing();

    /**
     * DOCUMENT ME!
     *
     * @param rowspacing DOCUMENT ME!
     */
    public void setRowspacing(String rowspacing);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getColumnspacing();

    /**
     * DOCUMENT ME!
     *
     * @param columnspacing DOCUMENT ME!
     */
    public void setColumnspacing(String columnspacing);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getRowlines();

    /**
     * DOCUMENT ME!
     *
     * @param rowlines DOCUMENT ME!
     */
    public void setRowlines(String rowlines);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getColumnlines();

    /**
     * DOCUMENT ME!
     *
     * @param columnlines DOCUMENT ME!
     */
    public void setColumnlines(String columnlines);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getFrame();

    /**
     * DOCUMENT ME!
     *
     * @param frame DOCUMENT ME!
     */
    public void setFrame(String frame);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getFramespacing();

    /**
     * DOCUMENT ME!
     *
     * @param framespacing DOCUMENT ME!
     */
    public void setFramespacing(String framespacing);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getEqualrows();

    /**
     * DOCUMENT ME!
     *
     * @param equalrows DOCUMENT ME!
     */
    public void setEqualrows(String equalrows);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getEqualcolumns();

    /**
     * DOCUMENT ME!
     *
     * @param equalcolumns DOCUMENT ME!
     */
    public void setEqualcolumns(String equalcolumns);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDisplaystyle();

    /**
     * DOCUMENT ME!
     *
     * @param displaystyle DOCUMENT ME!
     */
    public void setDisplaystyle(String displaystyle);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSide();

    /**
     * DOCUMENT ME!
     *
     * @param side DOCUMENT ME!
     */
    public void setSide(String side);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getMinlabelspacing();

    /**
     * DOCUMENT ME!
     *
     * @param minlabelspacing DOCUMENT ME!
     */
    public void setMinlabelspacing(String minlabelspacing);

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
    public MathMLTableRowElement insertEmptyRow(int index)
        throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLLabeledRowElement insertEmptyLabeledRow(int index)
        throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLTableRowElement getRow(int index);

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     * @param newRow DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLTableRowElement insertRow(int index,
        MathMLTableRowElement newRow) throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     * @param newRow DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLTableRowElement setRow(int index, MathMLTableRowElement newRow)
        throws DOMException;

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
    public MathMLTableRowElement removeRow(int index) throws DOMException;
}
;
