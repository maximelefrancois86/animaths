package org.jscience.mathMLImpl;

import org.w3c.dom.mathML.MathMLNodeList;
import org.w3c.dom.mathML.MathMLPresentationToken;


/**
 * Implements a MathML presentation token.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLPresentationTokenImpl extends MathMLElementImpl
    implements MathMLPresentationToken {
/**
     * Constructs a MathML presentation token.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLPresentationTokenImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getMathvariant() {
        return getAttribute("mathvariant");
    }

    /**
     * DOCUMENT ME!
     *
     * @param mathvariant DOCUMENT ME!
     */
    public void setMathvariant(String mathvariant) {
        setAttribute("mathvariant", mathvariant);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getMathsize() {
        return getAttribute("mathsize");
    }

    /**
     * DOCUMENT ME!
     *
     * @param mathsize DOCUMENT ME!
     */
    public void setMathsize(String mathsize) {
        setAttribute("mathsize", mathsize);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getMathfamily() {
        return getAttribute("mathfamily");
    }

    /**
     * DOCUMENT ME!
     *
     * @param mathfamily DOCUMENT ME!
     */
    public void setMathfamily(String mathfamily) {
        setAttribute("mathfamily", mathfamily);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getMathcolor() {
        return getAttribute("mathcolor");
    }

    /**
     * DOCUMENT ME!
     *
     * @param mathcolor DOCUMENT ME!
     */
    public void setMathcolor(String mathcolor) {
        setAttribute("mathcolor", mathcolor);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getMathbackground() {
        return getAttribute("mathbackground");
    }

    /**
     * DOCUMENT ME!
     *
     * @param mathbackground DOCUMENT ME!
     */
    public void setMathbackground(String mathbackground) {
        setAttribute("mathbackground", mathbackground);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLNodeList getContents() {
        return (MathMLNodeList) getChildNodes();
    }
}
