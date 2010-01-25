package org.w3c.dom.mathML;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLListElement extends MathMLContentContainer {
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
    public String getOrdering();

    /**
     * DOCUMENT ME!
     *
     * @param ordering DOCUMENT ME!
     */
    public void setOrdering(String ordering);
}
;
