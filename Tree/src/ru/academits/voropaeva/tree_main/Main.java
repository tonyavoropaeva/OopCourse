package ru.academits.voropaeva.tree_main;

import ru.academits.voropaeva.tree.Tree;

import java.util.Comparator;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> comparator = Integer::compareTo;

        Tree<Integer> tree = new Tree<>(comparator);
        tree.add(11);
        tree.add(3);
        tree.add(12);
        tree.add(1);
        tree.add(null);
        tree.add(8);
        tree.add(9);
        tree.add(10);
        tree.add(6);
        tree.add(4);
        tree.add(5);
        tree.add(15);
        tree.add(14);

        Consumer<Integer> print = x -> System.out.print(x + " ");

        System.out.println("Поиск по значению: " + tree.contains(null));

        System.out.println("Удаление по значению: " + tree.deleteByData(3));

        System.out.print("Обход в ширину:               ");
        tree.printBreadthFirst(print);
        System.out.println(System.lineSeparator() + "-----------------------");

        System.out.print("Обход в глубину с рекурсией:  ");
        tree.printDepthFirstRecursive(print);
        System.out.println(System.lineSeparator() + "-----------------------");

        System.out.print("Обход в глубину без рекурсии: ");
        tree.printDepthFirst(print);
        System.out.println(System.lineSeparator() + "-----------------------");

        System.out.println("Размер дерева: " + tree.getCount());
    }
}