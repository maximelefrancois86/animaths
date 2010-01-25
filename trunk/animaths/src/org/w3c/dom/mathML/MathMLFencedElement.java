package org.w3c.dom.mathML;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLFencedElement extends MathMLPresentationContainer {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getOpen();

    /**
     * DOCUMENT ME!
     *
     * @param open DOCUMENT ME!
     */
    public void setOpen(String open);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getClose();

    /**
     * DOCUMENT ME!
     *
     * @param close DOCUMENT ME!
     */
    public void setClose(String close);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSeparators();

    /**
     * DOCUMENT ME!
     *
     * @param separators DOCUMENT ME!
     */
    public void setSeparators(String separators);
}
;
