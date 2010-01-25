package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLPredefinedSymbol;


/**
 * Implements a MathML predefined symbol.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLPredefinedSymbolImpl extends MathMLElementImpl
    implements MathMLPredefinedSymbol {
/**
     * Constructs a MathML predefined symbol.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLPredefinedSymbolImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDefinitionURL() {
        return getAttribute("definitionURL");
    }

    /**
     * DOCUMENT ME!
     *
     * @param definitionURL DOCUMENT ME!
     */
    public void setDefinitionURL(String definitionURL) {
        setAttribute("definitionURL", definitionURL);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getEncoding() {
        return getAttribute("encoding");
    }

    /**
     * DOCUMENT ME!
     *
     * @param encoding DOCUMENT ME!
     */
    public void setEncoding(String encoding) {
        setAttribute("encoding", encoding);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getArity() {
        return "0";
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSymbolName() {
        return getLocalName();
    }
}
