package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLCnElement;


/**
 * Implements a MathML numeric content element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLCnElementImpl extends MathMLContentTokenImpl
    implements MathMLCnElement {
/**
     * Constructs a MathML numeric content element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLCnElementImpl(MathMLDocumentImpl owner, String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getType() {
        String type = getAttribute("type");

        if (type == "") {
            type = "real";
        }

        return type;
    }

    /**
     * DOCUMENT ME!
     *
     * @param type DOCUMENT ME!
     */
    public void setType(String type) {
        setAttribute("type", type);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getBase() {
        String base = getAttribute("base");

        if (base == "") {
            base = "10";
        }

        return base;
    }

    /**
     * DOCUMENT ME!
     *
     * @param base DOCUMENT ME!
     */
    public void setBase(String base) {
        setAttribute("base", base);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getNargs() {
        final int length = getLength();
        int numArgs = 1;

        for (int i = 0; i < length; i++) {
            String localName = item(i).getLocalName();

            if ((localName != null) && localName.equals("sep")) {
                numArgs++;
            }
        }

        return numArgs;
    }
}
