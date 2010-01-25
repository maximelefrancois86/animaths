package org.jscience.mathMLImpl;

import org.apache.xerces.dom.DOMImplementationImpl;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.mathML.MathMLDOMImplementation;
import org.w3c.dom.mathML.MathMLDocument;


/**
 * Implements a MathML DOM implementation.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLDOMImplementationImpl extends DOMImplementationImpl
    implements MathMLDOMImplementation {
    /** DOCUMENT ME! */
    private static final MathMLDOMImplementation singleton = new MathMLDOMImplementationImpl();

/**
     * Creates a new MathMLDOMImplementationImpl object.
     */
    private MathMLDOMImplementationImpl() {
        super();
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public final MathMLDocument createMathMLDocument() {
        return new MathMLDocumentImpl();
    }

    /**
     * NON-DOM: Obtain and return a single shared object
     *
     * @return DOCUMENT ME!
     */
    public static DOMImplementation getDOMImplementation() {
        return singleton;
    }
}
