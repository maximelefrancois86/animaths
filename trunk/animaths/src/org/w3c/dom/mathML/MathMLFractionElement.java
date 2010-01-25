package org.w3c.dom.mathML;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLFractionElement extends MathMLPresentationElement {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getLinethickness();

    /**
     * DOCUMENT ME!
     *
     * @param linethickness DOCUMENT ME!
     */
    public void setLinethickness(String linethickness);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getNumerator();

    /**
     * DOCUMENT ME!
     *
     * @param numerator DOCUMENT ME!
     */
    public void setNumerator(MathMLElement numerator);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getDenominator();

    /**
     * DOCUMENT ME!
     *
     * @param denominator DOCUMENT ME!
     */
    public void setDenominator(MathMLElement denominator);
}
;
