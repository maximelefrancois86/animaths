package org.w3c.dom.mathML;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLDeclareElement extends MathMLContentElement {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getType();

    /**
     * DOCUMENT ME!
     *
     * @param type DOCUMENT ME!
     */
    public void setType(String type);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getNargs();

    /**
     * DOCUMENT ME!
     *
     * @param nargs DOCUMENT ME!
     */
    public void setNargs(int nargs);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getOccurrence();

    /**
     * DOCUMENT ME!
     *
     * @param occurrence DOCUMENT ME!
     */
    public void setOccurrence(String occurrence);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDefinitionURL();

    /**
     * DOCUMENT ME!
     *
     * @param definitionURL DOCUMENT ME!
     */
    public void setDefinitionURL(String definitionURL);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getEncoding();

    /**
     * DOCUMENT ME!
     *
     * @param encoding DOCUMENT ME!
     */
    public void setEncoding(String encoding);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLCiElement getIdentifier();

    /**
     * DOCUMENT ME!
     *
     * @param identifier DOCUMENT ME!
     */
    public void setIdentifier(MathMLCiElement identifier);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getConstructor();

    /**
     * DOCUMENT ME!
     *
     * @param constructor DOCUMENT ME!
     */
    public void setConstructor(MathMLElement constructor);
}
;
