package org.w3c.dom.mathML;

import org.w3c.dom.DOMException;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.2 $
 */
public interface MathMLScriptElement extends MathMLPresentationElement {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSubscriptshift();

    /**
     * DOCUMENT ME!
     *
     * @param subscriptshift DOCUMENT ME!
     */
    public void setSubscriptshift(String subscriptshift);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSuperscriptshift();

    /**
     * DOCUMENT ME!
     *
     * @param superscriptshift DOCUMENT ME!
     */
    public void setSuperscriptshift(String superscriptshift);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getBase();

    /**
     * DOCUMENT ME!
     *
     * @param base DOCUMENT ME!
     */
    public void setBase(MathMLElement base);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getSubscript();

    /**
     * DOCUMENT ME!
     *
     * @param subscript DOCUMENT ME!
     * @throws DOMException DOCUMENT ME!
     */
    public void setSubscript(MathMLElement subscript) throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getSuperscript();

    /**
     * DOCUMENT ME!
     *
     * @param superscript DOCUMENT ME!
     * @throws DOMException DOCUMENT ME!
     */
    public void setSuperscript(MathMLElement superscript)
            throws DOMException;
}
;
