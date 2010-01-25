package org.w3c.dom.mathML;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLSetElement extends MathMLContentContainer {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public boolean getIsExplicit();

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
}
;
