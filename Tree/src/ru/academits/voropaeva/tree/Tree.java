package ru.academits.voropaeva.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int nodeCount;

    public TreeNode<T> getRoot() {
        return root;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void addNode(T data) {
        TreeNode<T> newNode = new TreeNode<>(data);
        TreeNode<T> currentNode = root;

        if (currentNode == null) {
            root = newNode;
            ++nodeCount;
        } else {
            while (true) {
                if (data.compareTo(currentNode.getData()) < 0) {   // (data < currentNode.getData())
                    if (currentNode.getLeft() != null) {
                        currentNode = currentNode.getLeft();
                    } else {
                        currentNode.setLeft(newNode);
                        currentNode.getLeft().setData(data);
                        currentNode.getLeft().setParent(currentNode);
                        currentNode.getLeft().isLeftChild(true);
                        ++nodeCount;

                        break;
                    }
                } else {
                    if (currentNode.getRight() != null) {
                        currentNode = currentNode.getRight();
                    } else {
                        currentNode.setRight(newNode);
                        currentNode.getRight().setData(data);
                        currentNode.getRight().setParent(currentNode);
                        ++nodeCount;

                        break;
                    }
                }
            }
        }
    }

    public TreeNode<T> searchByData(T data) {
        TreeNode<T> currentNode = root;

        if (currentNode == null) {
            return null;
        }

        while (true) {
            if (currentNode.getData().compareTo(data) == 0) {
                return currentNode;
            } else if (currentNode.getData().compareTo(data) > 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return null;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return null;
                }
            }
        }
    }

    public boolean deleteByData(T data) {
        TreeNode<T> nodeToDelete = searchByData(data);

        if (nodeToDelete == null) {
            return false;
        }

        TreeNode<T> nodeToDeleteParent = nodeToDelete.getParent();

        if (nodeToDelete.getLeft() == null && nodeToDelete.getRight() == null) {
            if (nodeToDelete == root) {
                root = null;
            } else {
                if (nodeToDelete.isLeftChild()) {
                    nodeToDeleteParent.setLeft(null);
                } else {
                    nodeToDeleteParent.setRight(null);
                }
            }

            --nodeCount;
        } else if (nodeToDelete.getLeft() == null) {
            if (nodeToDelete == root) {
                root = nodeToDelete.getRight();
            } else if (nodeToDelete.isLeftChild()) {
                nodeToDeleteParent.setLeft(nodeToDelete.getRight());
            } else {
                nodeToDeleteParent.setRight(nodeToDelete.getRight());
            }

            --nodeCount;
        } else if (nodeToDelete.getRight() == null) {
            if (nodeToDelete == root) {
                root = nodeToDelete.getLeft();
            } else if (nodeToDelete.isLeftChild()) {
                nodeToDeleteParent.setLeft(nodeToDelete.getLeft());
            } else {
                nodeToDeleteParent.setRight(nodeToDelete.getLeft());
            }

            --nodeCount;
        } else {
            TreeNode<T> currentNode = nodeToDelete.getRight();

            while (currentNode.getLeft() != null) {
                currentNode = currentNode.getLeft();
            }

            TreeNode<T> heirNode = currentNode;

            if (heirNode != nodeToDelete.getRight()) {
                if (heirNode.getRight() != null) {
                    heirNode.getParent().setLeft(heirNode.getRight());
                } else {
                    heirNode.getParent().setLeft(null);
                }

                heirNode.setRight(nodeToDelete.getRight());

            }

            heirNode.setLeft(nodeToDelete.getLeft());

            if (nodeToDelete == root) {
                root = heirNode;
            } else if (nodeToDelete.isLeftChild()) {
                nodeToDeleteParent.setLeft(heirNode);
            } else {
                nodeToDeleteParent.setRight(heirNode);
            }

            --nodeCount;
        }

        return true;
    }

    // Обход в ширину
    public void printBreadthFirst() {
        Queue<TreeNode<T>> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.poll();

            System.out.println(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.offer(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.offer(currentNode.getRight());
            }
        }
    }

    // Обход в глубину с рекурсией
    public void printDepthFirstRecursive(TreeNode<T> node) {
        System.out.println(node.getData());
        // вызываем функцию рекурсивно для всех детей
        ArrayList<TreeNode<T>> children = new ArrayList<>();

        if (node.getLeft() != null) {
            children.add(node.getLeft());
        }

        if (node.getRight() != null) {
            children.add(node.getRight());
        }

        for (TreeNode<T> child : children) {
            printDepthFirstRecursive(child);
        }
    }

    // Обход в глубину без рекурсии
    public void printDepthFirst() {
        Stack<TreeNode<T>> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<T> currentNode = stack.pop();

            System.out.println(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.push(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.push(currentNode.getLeft());
            }
        }
    }
}