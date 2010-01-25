package org.jscience.mathMLImpl;

import org.w3c.dom.Node;
import org.w3c.dom.mathML.MathMLContentToken;
import org.w3c.dom.mathML.MathMLNodeList;


/**
 * Implements a MathML content token.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLContentTokenImpl extends MathMLElementImpl
    implements MathMLContentToken {
/**
     * Constructs a MathML content token.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLContentTokenImpl(MathMLDocumentImpl owner, String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDefinitionURL() {
        return getAttribute("definitionURL");
    }

    /**
     * DOCUMENT ME!
     *
     * @param definitionURL DOCUMENT ME!
     */
    public void setDefinitionURL(String definitionURL) {
        setAttribute("definitionURL", definitionURL);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getEncoding() {
        return getAttribute("encoding");
    }

    /**
     * DOCUMENT ME!
     *
     * @param encoding DOCUMENT ME!
     */
    public void setEncoding(String encoding) {
        setAttribute("encoding", encoding);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLNodeList getArguments() {
        return new MathMLNodeList() {
                public int getLength() {
                    return getArgumentsGetLength();
                }

                public Node item(int index) {
                    return getArgumentsItem(index);
                }
            };
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Node getArgument(int index) {
        Node arg = getArgumentsItem(index - 1);

        return arg;
    }

    /**
     * DOCUMENT ME!
     *
     * @param newArgument DOCUMENT ME!
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Node setArgument(Node newArgument, int index) {
        final int argsLength = getArgumentsGetLength();

        return replaceChild(newArgument, getArgumentsItem(index - 1));
    }

    /**
     * DOCUMENT ME!
     *
     * @param newArgument DOCUMENT ME!
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Node insertArgument(Node newArgument, int index) {
        final int argsLength = getArgumentsGetLength();

        return insertBefore(newArgument, getArgumentsItem(index - 1));
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public Node removeArgument(int index) {
        Node arg = getArgumentsItem(index - 1);

        return removeChild(arg);
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     */
    public void deleteArgument(int index) {
        removeArgument(index);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    private int getArgumentsGetLength() {
        final int length = getLength();
        int numArgs = 0;

        for (int i = 0; i < length; i++) {
            String localName = item(i).getLocalName();

            if (!((localName != null) && localName.equals("sep"))) {
                numArgs++;
            }
        }

        return numArgs;
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    private Node getArgumentsItem(int index) {
        final int argsLength = getArgumentsGetLength();

        if ((index < 0) || (index >= argsLength)) {
            return null;
        }

        Node node = null;
        int n = -1;

        for (int i = 0; n < index; i++) {
            node = item(i);

            String localName = item(i).getLocalName();

            if (!((localName != null) && localName.equals("sep"))) {
                n++;
            }
        }

        return node;
    }
}
