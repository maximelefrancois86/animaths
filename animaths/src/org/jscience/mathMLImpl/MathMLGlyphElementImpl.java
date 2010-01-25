package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLGlyphElement;


/**
 * Implements a MathML glyph element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLGlyphElementImpl extends MathMLElementImpl
    implements MathMLGlyphElement {
/**
     * Constructs a MathML glyph element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLGlyphElementImpl(MathMLDocumentImpl owner, String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getAlt() {
        return getAttribute("alt");
    }

    /**
     * DOCUMENT ME!
     *
     * @param alt DOCUMENT ME!
     */
    public void setAlt(String alt) {
        setAttribute("alt", alt);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getFontfamily() {
        return getAttribute("fontfamily");
    }

    /**
     * DOCUMENT ME!
     *
     * @param fontfamily DOCUMENT ME!
     */
    public void setFontfamily(String fontfamily) {
        setAttribute("fontfamily", fontfamily);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getIndex() {
        return Integer.parseInt(getAttribute("index"));
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     */
    public void setIndex(int index) {
        setAttribute("index", Integer.toString(index));
    }
}
