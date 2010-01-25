package org.w3c.dom.mathML;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLIntervalElement extends MathMLContentElement {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getClosure();

    /**
     * DOCUMENT ME!
     *
     * @param closure DOCUMENT ME!
     */
    public void setClosure(String closure);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLCnElement getStart();

    /**
     * DOCUMENT ME!
     *
     * @param start DOCUMENT ME!
     */
    public void setStart(MathMLCnElement start);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLCnElement getEnd();

    /**
     * DOCUMENT ME!
     *
     * @param end DOCUMENT ME!
     */
    public void setEnd(MathMLCnElement end);
}
;
