package org.w3c.dom.mathML;

import org.w3c.dom.Node;


/**
 * DOCUMENT ME!
 *
 * @author $author$
 * @version $Revision: 1.2 $
 */
public interface MathMLContentToken extends MathMLContentElement {
    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLNodeList getArguments();

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDefinitionURL();

    /**
     * DOCUMENT ME!
     *
     * @param definitionURL DOCUMENT ME!
     */
    public void setDefinitionURL(String definitionURL);

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getEncoding();

    /**
     * DOCUMENT ME!
     *
     * @param encoding DOCUMENT ME!
     */
    public void setEncoding(String encoding);

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     * @return DOCUMENT ME!
     */
    public Node getArgument(int index);

    /**
     * DOCUMENT ME!
     *
     * @param newArgument DOCUMENT ME!
     * @param index       DOCUMENT ME!
     * @return DOCUMENT ME!
     */
    public Node insertArgument(Node newArgument, int index);

    /**
     * DOCUMENT ME!
     *
     * @param newArgument DOCUMENT ME!
     * @param index       DOCUMENT ME!
     * @return DOCUMENT ME!
     */
    public Node setArgument(Node newArgument, int index);

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     */
    public void deleteArgument(int index);

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     * @return DOCUMENT ME!
     */
    public Node removeArgument(int index);
}
;
