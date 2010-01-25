package org.w3c.dom.mathML;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLPresentationToken extends MathMLPresentationElement {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getMathvariant();

    /**
     * DOCUMENT ME!
     *
     * @param mathvariant DOCUMENT ME!
     */
    public void setMathvariant(String mathvariant);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getMathsize();

    /**
     * DOCUMENT ME!
     *
     * @param mathsize DOCUMENT ME!
     */
    public void setMathsize(String mathsize);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getMathcolor();

    /**
     * DOCUMENT ME!
     *
     * @param mathcolor DOCUMENT ME!
     */
    public void setMathcolor(String mathcolor);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getMathbackground();

    /**
     * DOCUMENT ME!
     *
     * @param mathbackground DOCUMENT ME!
     */
    public void setMathbackground(String mathbackground);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLNodeList getContents();
}
;
