package org.jscience.mathMLImpl;

/**
 * Implements a MathML predefined symbol (unary operation).
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLUnaryOpImpl extends MathMLPredefinedSymbolImpl {
/**
     * Constructs a MathML predefined symbol (unary operation).
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLUnaryOpImpl(MathMLDocumentImpl owner, String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getArity() {
        return "1";
    }
}
