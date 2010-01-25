package org.jscience.mathMLImpl;

import org.apache.xerces.dom.ElementNSImpl;

import org.w3c.dom.Node;
import org.w3c.dom.mathML.MathMLElement;
import org.w3c.dom.mathML.MathMLMathElement;
import org.w3c.dom.mathML.MathMLNodeList;


/**
 * Implements a MathML element (and node list).
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLElementImpl extends ElementNSImpl implements MathMLElement,
    MathMLNodeList {
    /** DOCUMENT ME! */
    static final String mathmlURI = "http://www.w3.org/1998/Math/MathML";

/**
     * Constructs a MathML element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLElementImpl(MathMLDocumentImpl owner, String qualifiedName) {
        super(owner, mathmlURI, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getClassName() {
        return getAttribute("class");
    }

    /**
     * DOCUMENT ME!
     *
     * @param className DOCUMENT ME!
     */
    public void setClassName(String className) {
        setAttribute("class", className);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getMathElementStyle() {
        return getAttribute("style");
    }

    /**
     * DOCUMENT ME!
     *
     * @param mathElementStyle DOCUMENT ME!
     */
    public void setMathElementStyle(String mathElementStyle) {
        setAttribute("style", mathElementStyle);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getId() {
        return getAttribute("id");
    }

    /**
     * DOCUMENT ME!
     *
     * @param id DOCUMENT ME!
     */
    public void setId(String id) {
        setAttribute("id", id);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getHref() {
        return getAttribute("xlink:href");
    }

    /**
     * DOCUMENT ME!
     *
     * @param href DOCUMENT ME!
     */
    public void setHref(String href) {
        setAttribute("xlink:href", href);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getXref() {
        return getAttribute("xref");
    }

    /**
     * DOCUMENT ME!
     *
     * @param xref DOCUMENT ME!
     */
    public void setXref(String xref) {
        setAttribute("xref", xref);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLMathElement getOwnerMathElement() {
        if (this instanceof MathMLMathElement) {
            return null;
        }

        Node parent = getParentNode();

        while (!(parent instanceof MathMLMathElement)) {
            parent = parent.getParentNode();
        }

        return (MathMLMathElement) parent;
    }
}
