package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLFnElement;


/**
 * Implements a MathML function element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLFnElementImpl extends MathMLContentContainerImpl
    implements MathMLFnElement {
/**
     * Constructs a MathML function element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLFnElementImpl(MathMLDocumentImpl owner, String qualifiedName) {
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
}
