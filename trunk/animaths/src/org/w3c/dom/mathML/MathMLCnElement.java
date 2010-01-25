package org.w3c.dom.mathML;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLCnElement extends MathMLContentToken {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getType();

    /**
     * DOCUMENT ME!
     *
     * @param type DOCUMENT ME!
     */
    public void setType(String type);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getBase();

    /**
     * DOCUMENT ME!
     *
     * @param base DOCUMENT ME!
     */
    public void setBase(String base);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getNargs();
}
;
