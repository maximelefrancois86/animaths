package org.jscience.mathMLImpl;

/**
 * Implements a MathML predefined symbol (n-ary operation).
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLNaryOpImpl extends MathMLPredefinedSymbolImpl {
/**
     * Constructs a MathML predefined symbol (n-ary operation).
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLNaryOpImpl(MathMLDocumentImpl owner, String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getArity() {
        return "variable";
    }
}
