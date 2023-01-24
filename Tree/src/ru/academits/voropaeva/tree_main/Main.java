package ru.academits.voropaeva.tree_main;

import ru.academits.voropaeva.tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.addNode(10);
        tree.addNode(3);
        tree.addNode(11);
        tree.addNode(1);
        tree.addNode(5);
        tree.addNode(4);
        tree.addNode(8);
        tree.addNode(6);
        tree.addNode(7);
        tree.addNode(14);
        tree.addNode(13);

        Integer data = (tree.searchByData(20) == null) ? null : (tree.searchByData(20).getData());
        System.out.println(data);

        System.out.println(tree.deleteByData(5));

        System.out.println(tree.getNodeCount());

        System.out.println("-----------------------");
        tree.printDepthFirstRecursive(tree.getRoot());
        System.out.println("-----------------------");
        tree.printBreadthFirst();
        System.out.println("-----------------------");
        tree.printDepthFirst();
    }
}