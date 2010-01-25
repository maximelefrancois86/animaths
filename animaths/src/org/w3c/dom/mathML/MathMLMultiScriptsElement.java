package org.w3c.dom.mathML;

import org.w3c.dom.DOMException;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLMultiScriptsElement extends MathMLPresentationElement {
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
    public MathMLNodeList getPrescripts();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLNodeList getScripts();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getNumprescriptcolumns();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getNumscriptcolumns();

    /**
     * DOCUMENT ME!
     *
     * @param colIndex DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getPreSubScript(int colIndex);

    /**
     * DOCUMENT ME!
     *
     * @param colIndex DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getSubScript(int colIndex);

    /**
     * DOCUMENT ME!
     *
     * @param colIndex DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getPreSuperScript(int colIndex);

    /**
     * DOCUMENT ME!
     *
     * @param colIndex DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getSuperScript(int colIndex);

    /**
     * DOCUMENT ME!
     *
     * @param colIndex DOCUMENT ME!
     * @param newScript DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLElement insertPreSubScriptBefore(int colIndex,
        MathMLElement newScript) throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param colIndex DOCUMENT ME!
     * @param newScript DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLElement setPreSubScriptAt(int colIndex, MathMLElement newScript)
        throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param colIndex DOCUMENT ME!
     * @param newScript DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLElement insertSubScriptBefore(int colIndex,
        MathMLElement newScript) throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param colIndex DOCUMENT ME!
     * @param newScript DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLElement setSubScriptAt(int colIndex, MathMLElement newScript)
        throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param colIndex DOCUMENT ME!
     * @param newScript DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLElement insertPreSuperScriptBefore(int colIndex,
        MathMLElement newScript) throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param colIndex DOCUMENT ME!
     * @param newScript DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLElement setPreSuperScriptAt(int colIndex,
        MathMLElement newScript) throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param colIndex DOCUMENT ME!
     * @param newScript DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLElement insertSuperScriptBefore(int colIndex,
        MathMLElement newScript) throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param colIndex DOCUMENT ME!
     * @param newScript DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLElement setSuperScriptAt(int colIndex, MathMLElement newScript)
        throws DOMException;
}
;
