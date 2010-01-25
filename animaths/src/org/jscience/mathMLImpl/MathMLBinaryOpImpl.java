package org.jscience.mathMLImpl;

/**
 * Implements a MathML predefined symbol (binary operation).
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLBinaryOpImpl extends MathMLPredefinedSymbolImpl {
/**
     * Constructs a MathML predefined symbol (binary operation).
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLBinaryOpImpl(MathMLDocumentImpl owner, String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getArity() {
        return "2";
    }
}
