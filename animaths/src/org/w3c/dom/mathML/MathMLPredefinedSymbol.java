package org.w3c.dom.mathML;

/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLPredefinedSymbol extends MathMLContentElement {
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
    public String getArity();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSymbolName();
}
;
