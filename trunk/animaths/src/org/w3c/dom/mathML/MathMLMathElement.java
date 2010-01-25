package org.w3c.dom.mathML;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLMathElement extends MathMLElement, MathMLContainer {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getMacros();

    /**
     * DOCUMENT ME!
     *
     * @param macros DOCUMENT ME!
     */
    public void setMacros(String macros);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDisplay();

    /**
     * DOCUMENT ME!
     *
     * @param display DOCUMENT ME!
     */
    public void setDisplay(String display);
}
;
