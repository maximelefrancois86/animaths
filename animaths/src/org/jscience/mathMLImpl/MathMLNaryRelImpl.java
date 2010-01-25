package org.jscience.mathMLImpl;

/**
 * Implements a MathML predefined symbol (n-ary relation).
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLNaryRelImpl extends MathMLPredefinedSymbolImpl {
/**
     * Constructs a MathML predefined symbol (n-ary relation).
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLNaryRelImpl(MathMLDocumentImpl owner, String qualifiedName) {
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
