package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLXMLAnnotationElement;


/**
 * Implements a MathML XML annotation element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLXMLAnnotationElementImpl extends MathMLElementImpl
    implements MathMLXMLAnnotationElement {
/**
     * Constructs a MathML XML annotation element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLXMLAnnotationElementImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
        super(owner, qualifiedName);
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
