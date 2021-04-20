package sk.stu.fiit;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import sk.stu.fiit.AVLTree.AVLTree;
import sk.stu.fiit.hashtable.HashTable;

public class Main {

    public static void main(String[] args) {
        System.out.println("Spúšťam DSA projekt: Vyhľadávanie v dynamických množinách\n");

        // premenné potebné pre testovač
        int findFrom = 0, findTo = 0;
        int items = 0;
        Person[] peopleToAdd = new Person[0];

        // otvorenie a načítanie dát zo súboru
        try {
            File file = new File("testBig2.txt");// TU môžeme zmeniť vstup na iný texťák s iným počtom záznamov
            Scanner scan = new Scanner(file);

            if(file.getName() == "test1.txt")
                items = 1000;
            
            else if(file.getName() == "test2.txt")
                items = 8000;
            
            else if(file.getName() == "test3.txt")
                items = 50000;
            
            else if(file.getName() == "testBig.txt")
                items = 100000;

            else if(file.getName() == "testBig2.txt")
                items = 200000;
            
            peopleToAdd = new Person[items];

            // nastavenie indexov pre vyhľadávanie;
            findFrom = items / 3;
            findTo = findFrom * 2;

            // načítanie dát zo súboru do pola
            for (int i=0; i<items; i++) {

                String s = scan.next();
                String s2 = scan.next();
                int number = Integer.parseInt(s2);

                peopleToAdd[i] = new Person(s, number);
            }

            scan.close();

        } catch (FileNotFoundException e) {
            System.out.println("Chyba vstupného súboru s dátami");
            e.printStackTrace();
        }





        // realizácia testovania
        long start = 0, end = 0;





        System.out.println("Vlastná implementácia, AVLTree: začiatok");
        start = System.currentTimeMillis();

        AVLTree avlTreeVar = new AVLTree();
        for(int i=0; i<items; i++)
            avlTreeVar.root = avlTreeVar.insert(avlTreeVar.root, peopleToAdd[i].fullName, peopleToAdd[i].birthYear);

        AVLTree avlTreeVar2 = new AVLTree();
        for(int i=0; i<items; i++)
            avlTreeVar2.root = avlTreeVar2.insert(avlTreeVar2.root, peopleToAdd[i].fullName, peopleToAdd[i].birthYear);

        end = System.currentTimeMillis();

        if(avlTreeVar.root != null && avlTreeVar2.root != null)
            System.out.println("Dáta by mali byť načítané v AVLstrome s časom " + (end - start) + "ms, prechádzam k vyhľadávaniu");

        start = System.currentTimeMillis();

        for(int i=findFrom; i<findTo; i++){
            Person test = avlTreeVar.search(peopleToAdd[i].fullName);
            if(test == null)
                System.out.println("AVLTree problém, nenašiel som value: " + peopleToAdd[i].fullName);
        }
        for(int i=findFrom; i<findTo; i++){
            Person test = avlTreeVar2.search(peopleToAdd[i].fullName);
            if(test == null)
                System.out.println("AVLTree problém, nenašiel som value: " + peopleToAdd[i].fullName);
        }

        end = System.currentTimeMillis();
        System.out.println("Vlastná implementácia, AVLTree: koniec vyhľadávania s časom " + (end - start) + "ms\n");





        System.out.println("Vlastná implementácia, HashTable: začiatok");
        start = System.currentTimeMillis();

        HashTable hashTableVar = new HashTable();
        hashTableVar.doubleHashFunction(peopleToAdd, hashTableVar.table);

        HashTable hashTableVar2 = new HashTable();
        hashTableVar2.doubleHashFunction(peopleToAdd, hashTableVar2.table);

        end = System.currentTimeMillis();

        if(hashTableVar.table != null && hashTableVar2.table != null)
            System.out.println("Dáta by mali byť načítané v HasTable s časom " + (end - start) + "ms, prechádzam k vyhľadávaniu");

        start = System.currentTimeMillis();

        for(int i=findFrom; i<findTo; i++){
            Person test = hashTableVar.findValue(peopleToAdd[i].fullName);
            if(test == null)
                System.out.println("HashTable problém, nenašiel som value: " + peopleToAdd[i].fullName);
        }
        for(int i=findFrom; i<findTo; i++){
            Person test = hashTableVar2.findValue(peopleToAdd[i].fullName);
            if(test == null)
                System.out.println("HashTable problém, nenašiel som value: " + peopleToAdd[i].fullName);
        }

        end = System.currentTimeMillis();
        System.out.println("Vlastná implementácia, HashTable: koniec s časom " + (end - start) + "ms\n");





        System.out.println("Úspešne ukončujem DSA projekt: Vyhľadávanie v dynamických množinách");
    }
}