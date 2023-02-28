package ru.academits.voropaeva.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private int nodesCount;
    private final Comparator<T> comparator;

    public Tree() {
        comparator = null;
    }

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public int getCount() {
        return nodesCount;
    }

    private int compare(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null && data2 == null) {
            return 0;
        }

        if (data1 == null) {
            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        return ((Comparable<T>) data1).compareTo(data2);
    }

    public void add(T data) {
        TreeNode<T> newNode = new TreeNode<>(data);

        if (root == null) {
            root = newNode;
            ++nodesCount;

            return;
        }

        TreeNode<T> currentNode = root;

        while (true) {
            if (compare(data, currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(newNode);
                    ++nodesCount;

                    return;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(newNode);
                    ++nodesCount;

                    return;
                }
            }
        }
    }

    public boolean contains(T data) {
        if (root == null) {
            return false;
        }

        TreeNode<T> currentNode = root;

        while (true) {
            int comparisonResult = compare(data, currentNode.getData());

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return false;
                }
            }
        }
    }

    public boolean deleteByData(T data) {
        if (root == null) {
            return false;
        }

        TreeNode<T> nodeToDelete = root;
        TreeNode<T> nodeToDeleteParent = null;
        boolean nodeToDeleteIsLeftChild = false;
        int comparisonResult;

        while ((comparisonResult = compare(data, nodeToDelete.getData())) != 0) {
            nodeToDeleteParent = nodeToDelete;

            if (comparisonResult < 0) {
                nodeToDeleteIsLeftChild = true;
                nodeToDelete = nodeToDelete.getLeft();
            } else {
                nodeToDeleteIsLeftChild = false;
                nodeToDelete = nodeToDelete.getRight();
            }

            if (nodeToDelete == null) {
                return false;
            }
        }

        --nodesCount;

        if (nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null) {
            if (nodeToDeleteParent == null) {
                root = null;
            } else {
                if (nodeToDeleteIsLeftChild) {
                    nodeToDeleteParent.setLeft(null);
                } else {
                    nodeToDeleteParent.setRight(null);
                }
            }

            return true;
        }

        if (nodeToDelete.getLeft() == null) {
            if (nodeToDeleteParent == null) {
                root = nodeToDelete.getRight();
            } else if (nodeToDeleteIsLeftChild) {
                nodeToDeleteParent.setLeft(nodeToDelete.getRight());
            } else {
                nodeToDeleteParent.setRight(nodeToDelete.getRight());
            }

            return true;
        }

        if (nodeToDelete.getRight() == null) {
            if (nodeToDeleteParent == null) {
                root = nodeToDelete.getLeft();
            } else if (nodeToDeleteIsLeftChild) {
                nodeToDeleteParent.setLeft(nodeToDelete.getLeft());
            } else {
                nodeToDeleteParent.setRight(nodeToDelete.getLeft());
            }

            return true;
        }

        TreeNode<T> heirNode = nodeToDelete.getRight();
        TreeNode<T> heirNodeParent = nodeToDelete;

        while (heirNode.getLeft() != null) {
            heirNodeParent = heirNode;
            heirNode = heirNode.getLeft();
        }

        if (heirNode != nodeToDelete.getRight()) {
            heirNodeParent.setLeft(heirNode.getRight());
            heirNode.setRight(nodeToDelete.getRight());
        }

        heirNode.setLeft(nodeToDelete.getLeft());

        if (nodeToDeleteParent == null) {
            root = heirNode;
        } else if (nodeToDeleteIsLeftChild) {
            nodeToDeleteParent.setLeft(heirNode);
        } else {
            nodeToDeleteParent.setRight(heirNode);
        }

        return true;
    }

    // Обход в ширину +
    public void bypassBreadthFirst(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.poll();

            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.offer(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.offer(currentNode.getRight());
            }
        }
    }

    // Обход в глубину с рекурсией +
    public void bypassDepthFirstRecursive(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        visit(root, consumer);
    }

    private void visit(TreeNode<T> node, Consumer<T> consumer) {
        consumer.accept(node.getData());

        if (node.getLeft() != null) {
            visit(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            visit(node.getRight(), consumer);
        }
    }

    // Обход в глубину без рекурсии +
    public void bypassDepthFirst(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<T> currentNode = stack.pop();

            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.push(currentNode.getLeft());
            }
        }
    }
}