package org.w3c.dom.mathML;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLTableCellElement extends MathMLPresentationContainer {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getRowspan();

    /**
     * DOCUMENT ME!
     *
     * @param rowspan DOCUMENT ME!
     */
    public void setRowspan(String rowspan);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getColumnspan();

    /**
     * DOCUMENT ME!
     *
     * @param columnspan DOCUMENT ME!
     */
    public void setColumnspan(String columnspan);

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
    public boolean getHasaligngroups();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getCellindex();
}
;
