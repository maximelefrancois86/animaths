package org.w3c.dom.mathML;

import org.w3c.dom.Document;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.1 $
 */
public interface MathMLDocument extends Document {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getReferrer();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDomain();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getURI();
}
;
