package org.w3c.dom.mathML;

import org.w3c.dom.DOMException;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLSemanticsElement extends MathMLElement {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getBody();

    /**
     * DOCUMENT ME!
     *
     * @param body DOCUMENT ME!
     */
    public void setBody(MathMLElement body);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getNAnnotations();

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement getAnnotation(int index);

    /**
     * DOCUMENT ME!
     *
     * @param newAnnotation DOCUMENT ME!
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLElement insertAnnotation(MathMLElement newAnnotation, int index)
        throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param newAnnotation DOCUMENT ME!
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLElement setAnnotation(MathMLElement newAnnotation, int index)
        throws DOMException;

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     */
    public void deleteAnnotation(int index);

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLElement removeAnnotation(int index);
}
;
