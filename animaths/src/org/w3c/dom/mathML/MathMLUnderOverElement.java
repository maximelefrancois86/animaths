package org.w3c.dom.mathML;

import org.w3c.dom.DOMException;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLUnderOverElement extends MathMLPresentationElement {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getAccentunder();

    /**
     * DOCUMENT ME!
     *
     * @param accentunder DOCUMENT ME!
     */
    public void setAccentunder(String accentunder);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getAccent();

    /**
     * DOCUMENT ME!
     *
     * @param accent DOCUMENT ME!
     */
    public void setAccent(String accent);

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
    public MathMLElement getUnderscript();

    /**
     * DOCUMENT ME!
     *
     * @param underscript DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public void setUnderscript(MathMLElement underscript)
        throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getOverscript();

    /**
     * DOCUMENT ME!
     *
     * @param overscript DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public void setOverscript(MathMLElement overscript)
        throws DOMException;
}
;
