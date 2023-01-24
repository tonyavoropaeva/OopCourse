package ru.academits.voropaeva.tree;

public class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private TreeNode<T> parent;
    private boolean isLeftChild;
    private T data;

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public boolean isLeftChild() {
        return isLeftChild;
    }

    public void isLeftChild(boolean leftChild) {
        isLeftChild = leftChild;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int compareTo(TreeNode<T> treeNode) {
        return data.compareTo(treeNode.getData());
    }
}