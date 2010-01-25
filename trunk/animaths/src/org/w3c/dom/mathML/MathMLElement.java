package org.w3c.dom.mathML;

import org.w3c.dom.Element;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLElement extends Element {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getClassName();

    /**
     * DOCUMENT ME!
     *
     * @param className DOCUMENT ME!
     */
    public void setClassName(String className);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getMathElementStyle();

    /**
     * DOCUMENT ME!
     *
     * @param mathElementStyle DOCUMENT ME!
     */
    public void setMathElementStyle(String mathElementStyle);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getId();

    /**
     * DOCUMENT ME!
     *
     * @param id DOCUMENT ME!
     */
    public void setId(String id);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getXref();

    /**
     * DOCUMENT ME!
     *
     * @param xref DOCUMENT ME!
     */
    public void setXref(String xref);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getHref();

    /**
     * DOCUMENT ME!
     *
     * @param href DOCUMENT ME!
     */
    public void setHref(String href);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLMathElement getOwnerMathElement();
}
;
