package org.jscience.mathMLImpl;

/**
 * Implements a MathML predefined symbol (function).
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLFunctionImpl extends MathMLPredefinedSymbolImpl {
/**
     * Constructs a MathML predefined symbol (function).
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLFunctionImpl(MathMLDocumentImpl owner, String qualifiedName) {
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
