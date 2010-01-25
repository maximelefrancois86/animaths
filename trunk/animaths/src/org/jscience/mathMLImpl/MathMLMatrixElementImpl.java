package org.jscience.mathMLImpl;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.mathML.MathMLMatrixElement;
import org.w3c.dom.mathML.MathMLMatrixrowElement;
import org.w3c.dom.mathML.MathMLNodeList;


/**
 * Implements a MathML <code>matrix</code> element.
 *
 * @author Mark Hale
 * @version 1.0
 */
public class MathMLMatrixElementImpl extends MathMLElementImpl
    implements MathMLMatrixElement {
/**
     * Constructs a MathML <code>matrix</code> element.
     *
     * @param owner         DOCUMENT ME!
     * @param qualifiedName DOCUMENT ME!
     */
    public MathMLMatrixElementImpl(MathMLDocumentImpl owner,
        String qualifiedName) {
        super(owner, qualifiedName);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getNrows() {
        return getRowsGetLength();
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public int getNcols() {
        return getRow(1).getNEntries();
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    public MathMLNodeList getRows() {
        return new MathMLNodeList() {
                public int getLength() {
                    return getRowsGetLength();
                }

                public Node item(int index) {
                    return getRowsItem(index);
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
    public MathMLMatrixrowElement getRow(int index) throws DOMException {
        Node row = getRowsItem(index - 1);

        if (row == null) {
            throw new DOMException(DOMException.INDEX_SIZE_ERR,
                "Index out of bounds");
        }

        return (MathMLMatrixrowElement) row;
    }

    /**
     * DOCUMENT ME!
     *
     * @param newRow DOCUMENT ME!
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLMatrixrowElement setRow(MathMLMatrixrowElement newRow,
        int index) throws DOMException {
        final int rowsLength = getRowsGetLength();

        if ((index < 1) || (index > (rowsLength + 1))) {
            throw new DOMException(DOMException.INDEX_SIZE_ERR,
                "Index out of bounds");
        }

        if (index == (rowsLength + 1)) {
            return (MathMLMatrixrowElement) appendChild(newRow);
        } else {
            return (MathMLMatrixrowElement) replaceChild(newRow,
                getRowsItem(index - 1));
        }
    }

    /**
     * DOCUMENT ME!
     *
     * @param newRow DOCUMENT ME!
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public MathMLMatrixrowElement insertRow(MathMLMatrixrowElement newRow,
        int index) throws DOMException {
        final int rowsLength = getRowsGetLength();

        if ((index < 0) || (index > (rowsLength + 1))) {
            throw new DOMException(DOMException.INDEX_SIZE_ERR,
                "Index out of bounds");
        }

        if ((index == 0) || (index == (rowsLength + 1))) {
            return (MathMLMatrixrowElement) appendChild(newRow);
        } else {
            return (MathMLMatrixrowElement) insertBefore(newRow,
                getRowsItem(index - 1));
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
    public MathMLMatrixrowElement removeRow(int index)
        throws DOMException {
        Node row = getRowsItem(index - 1);

        if (row == null) {
            throw new DOMException(DOMException.INDEX_SIZE_ERR,
                "Index out of bounds");
        }

        return (MathMLMatrixrowElement) removeChild(row);
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @throws DOMException DOCUMENT ME!
     */
    public void deleteRow(int index) throws DOMException {
        removeRow(index);
    }

    /**
     * DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    private int getRowsGetLength() {
        final int length = getLength();
        int numRows = 0;

        for (int i = 0; i < length; i++) {
            if (item(i) instanceof MathMLMatrixrowElement) {
                numRows++;
            }
        }

        return numRows;
    }

    /**
     * DOCUMENT ME!
     *
     * @param index DOCUMENT ME!
     *
     * @return DOCUMENT ME!
     */
    private Node getRowsItem(int index) {
        final int rowsLength = getRowsGetLength();

        if ((index < 0) || (index >= rowsLength)) {
            return null;
        }

        Node node = null;
        int n = -1;

        for (int i = 0; n < index; i++) {
            node = item(i);

            if (node instanceof MathMLMatrixrowElement) {
                n++;
            }
        }

        return node;
    }
}
