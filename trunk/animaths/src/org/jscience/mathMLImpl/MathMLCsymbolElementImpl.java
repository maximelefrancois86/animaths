package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLCsymbolElement;


/**
 * Implements a MathML content symbol element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLCsymbolElementImpl extends MathMLContentTokenImpl
    implements MathMLCsymbolElement {
/**
     * Constructs a MathML content symbol element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLCsymbolElementImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
        super(owner, qualifiedName);
    }
}
