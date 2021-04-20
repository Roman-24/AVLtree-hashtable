package sk.stu.fiit.AVLTree;

import sk.stu.fiit.Person;

public class AVLTree {

    public Node root = null;
    public int duplicate = 0; // počíta sa koľko bolo duplikátov ak by bola táto informáia potrebná vrámci testov alebo tak

    public Node insert(Node node, String inputFullName, int birthYear) {

        // klasické rekurzívne vkladanie do stromu
        if (node == null) { // ak nemáme žiadny strom tak ho vytvor

            Node newNode = new Node(inputFullName, birthYear);
            return (newNode);

        } else if (node.value.fullName.compareTo(inputFullName) < 0) {

            node.right = insert(node.right, inputFullName, birthYear);

        } else if (node.value.fullName.compareTo(inputFullName) > 0) {

            node.left = insert(node.left, inputFullName, birthYear);

        } else { // duplikáty sa ignorujú
            duplicate++;
        }


        // setne sa výška
        node.height = Math.max(height(node.left), height(node.right)) + 1;


        // balancovanie
        int balance = height(node.left) - height(node.right);
        // sa vypočíta ako rozdiel left a right
        // ak je left > right výsledkom je kladná hodnota
        // ak je right > left výsledkom je záporná hodnota


        // Ak je uzol nevyvážený tak sú 4 možnosti čo sa môže stať

        // Rotácia vľavo
        if (balance > 1 && node.left.value.fullName.compareTo(inputFullName) > 0)
            return rotateRight(node);

        // Rotácia vľavo a vpravo
        if (balance > 1 && node.left.value.fullName.compareTo(inputFullName) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Rotácia vpravo
        if (balance < -1 && node.right.value.fullName.compareTo(inputFullName) < 0)
            return rotateLeft(node);

        // Rotácia vpravo a vľavo
        if (balance < -1 && node.right.value.fullName.compareTo(inputFullName) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;

        /*
        s1.compareTo(s2)
        s1 == s2 :0
        s1 > s2  : + value
        s1 < s2  : - value
        */
    }

    public Person search(String stringToFind) {

        Node actual = root;

        while (actual != null) {

            if (actual.value.fullName.equals(stringToFind)) // ak je aktuálny rovnaký s prehľadávaným tak sme našli break -> return
                return actual.value; // vráť nájdený prvok

            else if (actual.value.fullName.compareTo(stringToFind) < 0) // ak je vľavo menší prvok ako ten čo hľadám tak už tam hľadaný nebude preto idem doprava
                actual = actual.right;

            else // inak idem vľavo
                actual = actual.left;
        }

        return null;
    }

    private int height(Node node) { // funkcia na získanie výšky stromu
        if (node == null)
            return -1; // ak node nemáme tak výška je -1
        return node.height;
    }

    private Node rotateRight(Node node) {

        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        temp.height = Math.max(height(temp.left), height(temp.right)) + 1;

        return temp;
    }

    private Node rotateLeft(Node node) {

        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        temp.height = Math.max(height(temp.left), height(temp.right)) + 1;

        return temp;
    }

    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            // System.out.println(root.height + " ");
            System.out.print(node.value.fullName + " ");
            inOrder(node.right);
        }
    }

}