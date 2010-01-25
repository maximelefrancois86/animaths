package org.jscience.mathMLImpl;

import org.w3c.dom.DOMException;
import org.w3c.dom.mathML.MathMLElement;
import org.w3c.dom.mathML.MathMLUnderOverElement;


/**
 * Implements a MathML under-over element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLUnderOverElementImpl extends MathMLElementImpl
    implements MathMLUnderOverElement {
/**
     * Constructs a MathML under-over element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLUnderOverElementImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getAccentunder() {
        if (getLocalName().equals("mover")) {
            return null;
        }

        return getAttribute("accentunder");
    }

    /**
     * DOCUMENT ME!
     *
     * @param accentunder DOCUMENT ME!
     */
    public void setAccentunder(String accentunder) {
        setAttribute("accentunder", accentunder);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getAccent() {
        if (getLocalName().equals("munder")) {
            return null;
        }

        return getAttribute("accent");
    }

    /**
     * DOCUMENT ME!
     *
     * @param accent DOCUMENT ME!
     */
    public void setAccent(String accent) {
        setAttribute("accent", accent);
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
    public MathMLElement getUnderscript() {
        if (getLocalName().equals("mover")) {
            return null;
        }

        return (MathMLElement) item(1);
    }

    /**
     * DOCUMENT ME!
     *
     * @param underscript DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public void setUnderscript(MathMLElement underscript)
        throws DOMException {
        if (getLocalName().equals("mover")) {
            throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR,
                "Cannot set a subscript for msup");
        }

        replaceChild(underscript, item(1));
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getOverscript() {
        if (getLocalName().equals("munder")) {
            return null;
        }

        if (getLocalName().equals("mover")) {
            return (MathMLElement) item(1);
        } else {
            return (MathMLElement) item(2);
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param overscript DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public void setOverscript(MathMLElement overscript)
        throws DOMException {
        if (getLocalName().equals("munder")) {
            throw new DOMException(DOMException.HIERARCHY_REQUEST_ERR,
                "Cannot set a superscript for msub");
        }

        if (getLocalName().equals("mover")) {
            replaceChild(overscript, item(1));
        } else {
            replaceChild(overscript, item(2));
        }
    }
}
