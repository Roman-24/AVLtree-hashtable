package sk.stu.fiit.AVLTree;

import sk.stu.fiit.Person;

public class Node {

    public Person value;
    int height;
    Node left;
    Node right;

    public Node(String fullName, int birthYear) {

        this.value = new Person();
        this.height = 0; // na začiatku bude mať každý NODE výšku nula
        this.value.fullName = fullName;
        this.value.birthYear = birthYear;
    }

}