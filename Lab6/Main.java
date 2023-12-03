    import java.util.*;

    /**
     * This is a simple program for managing a tree data structure with various
     * operations.
     */
    public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter Root Element : ");
            String rooT = scanner.nextLine();
            Tree<String> tree = new Tree<>(rooT);

            System.out.println("\nMenu:");
            System.out.println("1. Display Tree");
            System.out.println("2. Add Child Node");
            System.out.println("3. Find Siblings");
            System.out.println("4. List Leaves");
            System.out.println("5. List Internal Nodes");
            System.out.println("6. Exit");
            System.out.println("7. List of Edges");
            System.out.println("8. Path for given Node");
            System.out.println("9. Depth of Node");
            System.out.println("10. Height of Tree");
            System.out.println("11. Subtree rooted at given node");
            System.out.println();

            while (true) {
                try {
                    System.out.print("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    switch (choice) {
                        case 1:
                            tree.displayTree();
                            System.out.println();
                            break;
                        case 2:
                            System.out.print("Enter the value of the parent node: ");
                            String parentValue = scanner.nextLine();
                            Position<String> parent = findNodeWithValue(tree, parentValue);

                            if (parent == null) {
                                System.out.println("Parent node not found.");
                            } else {
                                System.out.print("Enter the value of the new child node: ");
                                String childValue = scanner.nextLine();
                                Position<String> newChild = tree.addChild(parent, childValue);
                                System.out.println("Added " + childValue + " Successfully");
                            }
                            System.out.println();
                            break;
                        case 3:
                            System.out.print("Enter the value of the node to find its siblings: ");
                            String findSib = scanner.nextLine();

                            Position<String> targetNode = findNodeWithValue(tree, findSib);
                            if (targetNode != null) {
                                List<Position<String>> siblings = tree.getSiblingPositions(targetNode);
                                System.out.print("Siblings of " + findSib + ": ");
                                for (Position<String> sibling : siblings) {
                                    System.out.print(sibling.getElement() + " ");
                                }
                                System.out.println();
                            } else {
                                System.out.println("Node not found.");
                            }
                            System.out.println();
                            break;
                        case 4:
                            List<Position<String>> leaves = tree.getLeaves();
                            System.out.print("Leaves in the tree: ");
                            for (Position<String> leaf : leaves) {
                                System.out.print(leaf.getElement() + " ");
                            }
                            System.out.println();
                            break;
                        case 5:
                            List<Position<String>> internalNodes = tree.getInternalNodes();
                            System.out.print("Internal nodes in the tree: ");
                            for (Position<String> internalNode : internalNodes) {
                                System.out.print(internalNode.getElement() + " ");
                            }
                            System.out.println();
                            break;
                        case 6:
                            System.out.println("Exiting the program.");
                            scanner.close();
                            System.exit(0);
                            break;
                        case 7:
                            List<String> edges = getEdges(tree);
                            System.out.println("List of Edges in the Tree:");
                            for (String edge : edges) {
                                System.out.println(edge);
                            }
                            System.out.println();
                            break;
                        case 8:
                            System.out.print("Enter the value of the node to find its path: ");
                            String nodeValue = scanner.nextLine();
                            Position<String> targetNodePath = findNodeWithValue(tree, nodeValue);

                            if (targetNodePath != null) {
                                List<Position<String>> path = getPath(tree, targetNodePath);
                                System.out.print("Path for " + nodeValue + ": ");
                                for (Position<String> pathNode : path) {
                                    System.out.print(pathNode.getElement() + " ");
                                }
                                System.out.println();
                            } else {
                                System.out.println("Node not found.");
                            }
                            System.out.println();
                            break;
                        case 9:
                            System.out.print("Enter the value of the node to find its depth: ");
                            String depthNodeValue = scanner.nextLine();
                            Position<String> depthNode = findNodeWithValue(tree, depthNodeValue);

                            if (depthNode != null) {
                                int depth = getDepth(tree, depthNode);
                                System.out.println("Depth of " + depthNodeValue + ": " + depth);
                            } else {
                                System.out.println("Node not found.");
                            }
                            System.out.println();
                            break;
                        case 10:
                            int treeHeight = getHeight(tree);
                            System.out.println("Height of the Tree: " + treeHeight);
                            System.out.println();
                            break;
                        case 11:
                            System.out.print("Enter the value of the node to get its subtree: ");
                            String subtreeRootValue = scanner.nextLine();
                            Position<String> subtreeRoot = findNodeWithValue(tree, subtreeRootValue);

                            if (subtreeRoot != null) {
                                Tree<String> subtree = getSubtree(tree, subtreeRoot);
                                System.out.println("Subtree rooted at " + subtreeRootValue + ":");
                                subtree.displayTree();
                            } else {
                                System.out.println("Node not found.");
                            }
                            System.out.println();
                            break;
                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                    }
                } catch (Exception e) {
                    // Handle exceptions here, for example, print an error message
                    System.err.println("An error occurred: " + e.getMessage());
                    System.out.println();
                    scanner.nextLine(); // Consume the invalid input
                }
            }
        }

        /**
         * Find a node with a specific value in the tree.
         *
         * @param tree  The tree in which to search for the node.
         * @param value The value to search for.
         * @return The Position of the node with the specified value, or null if not
         *         found.
         */
        private static Position<String> findNodeWithValue(Tree<String> tree, String value) {
            for (Position<String> position : tree.positions()) {
                if (position.getElement().equals(value)) {
                    return position;
                }
            }
            return null;
        }
        private static List<String> getEdges(Tree<String> tree) {
        List<String> edges = new LinkedList<>();
        for (Position<String> position : tree.positions()) {
            Node<String> node = (Node<String>) position;
            for (Node<String> child : node.getChildren()) {
                edges.add(node.getElement() + " -> " + child.getElement());
            }
        }
        return edges;
    }

    private static List<Position<String>> getPath(Tree<String> tree, Position<String> targetNode) {
        List<Position<String>> path = new LinkedList<>();
        while (targetNode != null) {
            path.add(targetNode);
            targetNode = tree.parent(targetNode);
        }
        Collections.reverse(path);
        return path;
    }

    private static int getDepth(Tree<String> tree, Position<String> targetNode) {
        int depth = 0;
        while (targetNode != null && !tree.isRoot(targetNode)) {
            targetNode = tree.parent(targetNode);
            depth++;
        }
        return depth;
    }

    private static int getHeight(Tree<String> tree) {
        int maxHeight = 0;
        for (Position<String> leaf : tree.getLeaves()) {
            int height = getDepth(tree, leaf);
            maxHeight = Math.max(maxHeight, height);
        }
        return maxHeight;
    }

    private static Tree<String> getSubtree(Tree<String> tree, Position<String> subtreeRoot) {
        Tree<String> subtree = new Tree<>(subtreeRoot.getElement());
        buildSubtree(tree, subtree, (Node<String>) subtreeRoot);
        return subtree;
    }

    private static void buildSubtree(Tree<String> tree, Tree<String> subtree, Node<String> subtreeRoot) {
        for (Node<String> child : subtreeRoot.getChildren()) {
            Position<String> newChild = subtree.addChild(subtreeRoot, child.getElement());
            buildSubtree(tree, subtree, child);
        }
    }

    }
