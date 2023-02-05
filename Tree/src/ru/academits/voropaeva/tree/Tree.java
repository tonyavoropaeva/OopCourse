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

    private int getComparison(T dataOne, T dataTwo) {
        if (dataOne == null && dataTwo == null) {
            return 0;
        }

        if (dataOne == null) {
            return -1;
        }

        if (dataTwo == null) {
            return 1;
        }

        //noinspection unchecked
        return comparator == null ? ((Comparable<T>) dataOne).compareTo(dataTwo) : comparator.compare(dataOne, dataTwo);
    }

    public void add(T data) {
        TreeNode<T> newNode = new TreeNode<>(data);
        TreeNode<T> currentNode = root;

        if (currentNode == null) {
            root = newNode;
            ++nodesCount;

            return;
        }

        while (true) {
            if (getComparison(data, currentNode.getData()) < 0) {
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
            T currentData = currentNode.getData();

            if (getComparison(data, currentData) == 0) {
                return true;
            }

            if (getComparison(data, currentData) < 0) {
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
        TreeNode<T> nodeToDeleteParent = root;
        boolean nodeToDeleteIsLeftChild = false;
        T nodeToDeleteData;

        while (!Objects.equals((nodeToDeleteData = nodeToDelete.getData()), (data))) {
            nodeToDeleteParent = nodeToDelete;

            if (getComparison(data, nodeToDeleteData) < 0) {
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
            if (nodeToDelete == root) {
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
            if (nodeToDelete == root) {
                root = nodeToDelete.getRight();
            } else if (nodeToDeleteIsLeftChild) {
                nodeToDeleteParent.setLeft(nodeToDelete.getRight());
            } else {
                nodeToDeleteParent.setRight(nodeToDelete.getRight());
            }

            return true;
        }

        if (nodeToDelete.getRight() == null) {
            if (nodeToDelete == root) {
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

        if (nodeToDelete == root) {
            root = heirNode;
        } else if (nodeToDeleteIsLeftChild) {
            nodeToDeleteParent.setLeft(heirNode);
        } else {
            nodeToDeleteParent.setRight(heirNode);
        }

        return true;
    }

    // Обход в ширину +
    public void printBreadthFirst(Consumer<T> function) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.poll();

            function.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.offer(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.offer(currentNode.getRight());
            }
        }
    }

    // Обход в глубину с рекурсией +
    public void printDepthFirstRecursive(Consumer<T> function) {
        if (root == null) {
            return;
        }

        visit(root, function);
    }

    private void visit(TreeNode<T> node, Consumer<T> function) {
        function.accept(node.getData());

        if (node.getLeft() != null) {
            visit(node.getLeft(), function);
        }

        if (node.getRight() != null) {
            visit(node.getRight(), function);
        }
    }

    // Обход в глубину без рекурсии +
    public void printDepthFirst(Consumer<T> function) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<T> currentNode = stack.pop();

            function.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.push(currentNode.getLeft());
            }
        }
    }
}