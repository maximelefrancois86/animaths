package org.jscience.mathMLImpl;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.mathML.MathMLDeclareElement;
import org.w3c.dom.mathML.MathMLElement;
import org.w3c.dom.mathML.MathMLMathElement;
import org.w3c.dom.mathML.MathMLNodeList;


/**
 * Implements a MathML <code>math</code> element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLMathElementImpl extends MathMLElementImpl
    implements MathMLMathElement {
/**
     * Constructs a MathML <code>math</code> element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLMathElementImpl(MathMLDocumentImpl owner, String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getMacros() {
        return getAttribute("macros");
    }

    /**
     * DOCUMENT ME!
     *
     * @param macros DOCUMENT ME!
     */
    public void setMacros(String macros) {
        setAttribute("macros", macros);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public String getDisplay() {
        return getAttribute("display");
    }

    /**
     * DOCUMENT ME!
     *
     * @param display DOCUMENT ME!
     */
    public void setDisplay(String display) {
        setAttribute("display", display);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getNArguments() {
        return getArgumentsGetLength();
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
     * @return DOCUMENT ME!
     */
    public MathMLNodeList getDeclarations() {
        return new MathMLNodeList() {
                public int getLength() {
                    return getDeclarationsGetLength();
                }

                public Node item(int index) {
                    return getDeclarationsItem(index);
                }
            };
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLElement getArgument(int index) throws DOMException {
        Node arg = getArgumentsItem(index - 1);

        if (arg == null) {
            throw new DOMException(DOMException.INDEX_SIZE_ERR,
                "Index out of bounds");
        }

        return (MathMLElement) arg;
    }

    /**
     * DOCUMENT ME!
     *
     * @param newArgument DOCUMENT ME!
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLElement setArgument(MathMLElement newArgument, int index)
        throws DOMException {
        final int argsLength = getArgumentsGetLength();

        if ((index < 1) || (index > (argsLength + 1))) {
            throw new DOMException(DOMException.INDEX_SIZE_ERR,
                "Index out of bounds");
        }

        if (index == (argsLength + 1)) {
            return (MathMLElement) appendChild(newArgument);
        } else {
            return (MathMLElement) replaceChild(newArgument,
                getArgumentsItem(index - 1));
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param newArgument DOCUMENT ME!
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLElement insertArgument(MathMLElement newArgument, int index)
        throws DOMException {
        final int argsLength = getArgumentsGetLength();

        if ((index < 0) || (index > (argsLength + 1))) {
            throw new DOMException(DOMException.INDEX_SIZE_ERR,
                "Index out of bounds");
        }

        if ((index == 0) || (index == (argsLength + 1))) {
            return (MathMLElement) appendChild(newArgument);
        } else {
            return (MathMLElement) insertBefore(newArgument,
                getArgumentsItem(index - 1));
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLElement removeArgument(int index) throws DOMException {
        Node arg = getArgumentsItem(index - 1);

        if (arg == null) {
            throw new DOMException(DOMException.INDEX_SIZE_ERR,
                "Index out of bounds");
        }

        return (MathMLElement) removeChild(arg);
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public void deleteArgument(int index) throws DOMException {
        removeArgument(index);
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLDeclareElement getDeclaration(int index)
        throws DOMException {
        Node decl = getDeclarationsItem(index - 1);

        if (decl == null) {
            throw new DOMException(DOMException.INDEX_SIZE_ERR,
                "Index out of bounds");
        }

        return (MathMLDeclareElement) decl;
    }

    /**
     * DOCUMENT ME!
     *
     * @param newDeclaration DOCUMENT ME!
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLDeclareElement setDeclaration(
        MathMLDeclareElement newDeclaration, int index)
        throws DOMException {
        final int declsLength = getDeclarationsGetLength();

        if ((index < 1) || (index > (declsLength + 1))) {
            throw new DOMException(DOMException.INDEX_SIZE_ERR,
                "Index out of bounds");
        }

        if (index == (declsLength + 1)) {
            return (MathMLDeclareElement) appendChild(newDeclaration);
        } else {
            return (MathMLDeclareElement) replaceChild(newDeclaration,
                getDeclarationsItem(index - 1));
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param newDeclaration DOCUMENT ME!
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLDeclareElement insertDeclaration(
        MathMLDeclareElement newDeclaration, int index)
        throws DOMException {
        final int declsLength = getDeclarationsGetLength();

        if ((index < 0) || (index > (declsLength + 1))) {
            throw new DOMException(DOMException.INDEX_SIZE_ERR,
                "Index out of bounds");
        }

        if ((index == 0) || (index == (declsLength + 1))) {
            return (MathMLDeclareElement) appendChild(newDeclaration);
        } else {
            return (MathMLDeclareElement) insertBefore(newDeclaration,
                getDeclarationsItem(index - 1));
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLDeclareElement removeDeclaration(int index)
        throws DOMException {
        Node decl = getDeclarationsItem(index - 1);

        if (decl == null) {
            throw new DOMException(DOMException.INDEX_SIZE_ERR,
                "Index out of bounds");
        }

        return (MathMLDeclareElement) removeChild(decl);
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public void deleteDeclaration(int index) throws DOMException {
        removeDeclaration(index);
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
            if (!(item(i) instanceof MathMLDeclareElement)) {
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

            if (!(node instanceof MathMLDeclareElement)) {
                n++;
            }
        }

        return node;
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    private int getDeclarationsGetLength() {
        final int length = getLength();
        int numDecls = 0;

        for (int i = 0; i < length; i++) {
            if (item(i) instanceof MathMLDeclareElement) {
                numDecls++;
            }
        }

        return numDecls;
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    private Node getDeclarationsItem(int index) {
        final int declLength = getDeclarationsGetLength();

        if ((index < 0) || (index >= declLength)) {
            return null;
        }

        Node node = null;
        int n = -1;

        for (int i = 0; n < index; i++) {
            node = item(i);

            if (node instanceof MathMLDeclareElement) {
                n++;
            }
        }

        return node;
    }
}
