package org.w3c.dom.mathML;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLRadicalElement extends MathMLPresentationElement {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getRadicand();

    /**
     * DOCUMENT ME!
     *
     * @param radicand DOCUMENT ME!
     */
    public void setRadicand(MathMLElement radicand);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getIndex();

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     */
    public void setIndex(MathMLElement index);
}
;
