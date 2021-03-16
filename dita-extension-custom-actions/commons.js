/**
 * Sorts a table according to the content of the current column.
 */
function sortTable() {
    var controller = authorAccess.getDocumentController();
    var nodeAtSelection = controller.getNodeAtOffset(authorAccess.getEditorAccess().getCaretOffset());
    var ancestorEntries = findByXPath(nodeAtSelection, "ancestor-or-self::entry");
    sortTableAroundEntry(ancestorEntries[0], authorAccess);
}

/**
 * Sort table according to the content of the given entry.
 * @param {AuthorNode} entry The entry.
 */
function sortTableAroundEntry(entry) {
    var index = findByXPath(entry, "preceding-sibling::*").length;
    var tbody = findByXPath(entry, "ancestor-or-self::tbody")[0];
    var rows = findByXPath(tbody, "./row");
    rows.sort(function (row1, row2) {
        return compareRows(row1, row2, index);
    });

    var documentController = authorAccess.getDocumentController();
    var rowFrags = rows.map(function(row) {
        return documentController.createDocumentFragment(row.getStartOffset(), row.getEndOffset());
    });
    
    for (var i = 0; i < rows.length; i++) {
        documentController.deleteNode(rows[i]);
    }
    rowFrags.forEach(function (rowFrag) {
        documentController.insertFragment(tbody.getStartOffset() + 1, rowFrag);
    });
}

/**
 * Compare two rows according to the content of the cell at the given index.
 * @param {AuthorNode} row1 The first row.
 * @param {AuthorNode} row2 The second row.
 * @param {number} index The index of the cell used as sort criteria.
 */
function compareRows(row1, row2, index) {
    var textContent1 = getCellContent(row1, index);
    var textContent2 = getCellContent(row2, index);
    if (textContent1 == textContent2) {
        return 0;
    }
    return textContent1 < textContent2 ? -1 : 1;
}

/**
 * Return the content of the cell at the given index.
 * @param {AuthorNode} row The row.
 * @param {number} index The index.
 */
function getCellContent(row, index) {
    try {
        var entry1 = findByXPath(row, "./entry[" + index + "]")[0];
        return entry1.getTextContent();
    } catch (e) {
        return "";
    }
}

/**
 * Utility function to find a node by executing an XPath expression using the given node as reference.
 * @param {AuthorNode} node The reference node.
 * @param {string} expression The XPath expression.
 */
function findByXPath(node, expression) {
    var documentController = authorAccess.getDocumentController();
    return documentController.findNodesByXPath(expression, node, true, true, true, true)
}
