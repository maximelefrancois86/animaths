package org.jscience.mathMLImpl;

/**
 * Implements a MathML predefined symbol (binary relation).
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLBinaryRelImpl extends MathMLPredefinedSymbolImpl {
/**
     * Constructs a MathML predefined symbol (binary relation).
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLBinaryRelImpl(MathMLDocumentImpl owner, String qualifiedName) {
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
