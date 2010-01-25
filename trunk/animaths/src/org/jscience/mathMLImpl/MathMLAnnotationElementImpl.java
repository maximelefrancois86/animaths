package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLAnnotationElement;


/**
 * Implements a MathML annotation element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLAnnotationElementImpl extends MathMLElementImpl
    implements MathMLAnnotationElement {
/**
     * Constructs a MathML annotation element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLAnnotationElementImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getBody() {
        return getFirstChild().getNodeValue();
    }

    /**
     * DOCUMENT ME!
     *
     * @param body DOCUMENT ME!
     */
    public void setBody(String body) {
        getFirstChild().setNodeValue(body);
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
