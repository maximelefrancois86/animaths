package org.jscience.mathMLImpl;

import org.w3c.dom.DOMException;
import org.w3c.dom.mathML.MathMLElement;
import org.w3c.dom.mathML.MathMLScriptElement;


/**
 * Implements a MathML script element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLScriptElementImpl extends MathMLElementImpl
    implements MathMLScriptElement {
/**
     * Constructs a MathML script element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLScriptElementImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSubscriptshift() {
        if (getLocalName().equals("msup")) {
            return null;
        }

        return getAttribute("subscriptshift");
    }

    /**
     * DOCUMENT ME!
     *
     * @param subscriptshift DOCUMENT ME!
     */
    public void setSubscriptshift(String subscriptshift) {
        setAttribute("subscriptshift", subscriptshift);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getSuperscriptshift() {
        if (getLocalName().equals("msub")) {
            return null;
        }

        return getAttribute("superscriptshift");
    }

    /**
     * DOCUMENT ME!
     *
     * @param superscriptshift DOCUMENT ME!
     */
    public void setSuperscriptshift(String superscriptshift) {
        setAttribute("superscriptshift", superscriptshift);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getBase() {
        return (MathMLElement) getFirstChild();
    }

    /**
     * DOCUMENT ME!
     *
     * @param base DOCUMENT ME!
     */
    public void setBase(MathMLElement base) {
        replaceChild(base, getFirstChild());
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getSubscript() {
        if (getLocalName().equals("msup")) {
            return null;
        }

        return (MathMLElement) item(1);
    }

    /**
     * DOCUMENT ME!
     *
     * @param subscript DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public void setSubscript(MathMLElement subscript) throws DOMException {
        if (getLocalName().equals("msup")) {
            throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR,
                "Cannot set a subscript for msup");
        }

        replaceChild(subscript, item(1));
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getSuperscript() {
        if (getLocalName().equals("msub")) {
            return null;
        }

        if (getLocalName().equals("msup")) {
            return (MathMLElement) item(1);
        } else {
            return (MathMLElement) item(2);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param superscript DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public void setSuperscript(MathMLElement superscript)
        throws DOMException {
        if (getLocalName().equals("msub")) {
            throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR,
                "Cannot set a superscript for msub");
        }

        if (getLocalName().equals("msup")) {
            replaceChild(superscript, item(1));
        } else {
            replaceChild(superscript, item(2));
        }
    }
}
