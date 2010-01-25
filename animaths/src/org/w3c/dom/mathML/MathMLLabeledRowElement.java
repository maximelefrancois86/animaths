package org.w3c.dom.mathML;

import org.w3c.dom.DOMException;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLLabeledRowElement extends MathMLTableRowElement {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getLabel();

    /**
     * DOCUMENT ME!
     *
     * @param label DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public void setLabel(MathMLElement label) throws DOMException;
}
;
