package sk.stu.fiit.hashtable;

import sk.stu.fiit.Person;

public class HashTable {

    public Person[] table; // theArray
    public int tableSize; // arraySize
    public int tableSizeIndex = 0;
    private final int offset = 1000000021; // čím väčšie prvočíslo je nastavené tým rýchlejšie to zbehne
    public int itemsInTable = 0; // itemsInArray

    //int[] primes = new int[]{131, 313, 751, 1031, 10061, 100151, 1000081, 10000121, 100000541, 1000000021};
    int[] primes = new int[]{100151, 1000081, 10000121, 100000541, 1000000021};

    public HashTable() { // constructor

        tableSize = primes[tableSizeIndex];
        table = new Person[tableSize];

        for (int i = 0; i < tableSize; i++) { // na všetky mená sa zapíše -1 akože je index voľný
            table[i] = new Person();
            table[i].fullName = "-1";
        }
    }

    // funkcia vypočíta a vráti hodnotu Stringu
    public int calculateStringAsciValue(String string) {
        int asciValue = 0;
        for (int i = 0; i < string.length(); i++)
            asciValue += string.charAt(i);
        return asciValue;
    }

    public void doubleHashFunction(Person[] stringsForTable, Person[] table) {

        itemsInTable = 0;
        if (stringsForTable == null) {
            System.out.println("Nezadali ste vstupné elementy");
            System.exit(0);
        }

        for (int i = 0; i < stringsForTable.length; i++) {

            Person newElementVal = new Person(stringsForTable[i].fullName, stringsForTable[i].birthYear); // pojendom berieme osoby kt sa budú vkladať to tabuľky

            int arrayIndex = calculateStringAsciValue(newElementVal.fullName) % tableSize; // podľa mena vypočítame arrayIndex pre vkladaný element

            int stepDistance = offset - calculateStringAsciValue(newElementVal.fullName) % offset; // vypočítanie posunutia v tabuľke

            while (!table[arrayIndex].fullName.equals("-1")) { // kontrola či je index voľný ak nie hľadá sa kým sa nenájde najbližší voľný

                // posúvanie nového indexu nakoľko pôvodný idex mal kolíziu
                arrayIndex += stepDistance;
                arrayIndex %= tableSize;

            }

            // vloženie záznamu na nájdený voľný index
            table[arrayIndex] = newElementVal;
            itemsInTable++;

            if (itemsInTable == tableSize)  // zväčšenie tabuľký v prípade potreby
                table = increaseTableSize();
        }

    }

    public Person[] increaseTableSize() {

        if (tableSizeIndex < primes.length)
            tableSizeIndex += 1;
        else
            System.out.println("Viac už nieje možné zväčšiť tabulku");

        table = moveOldTableValues(primes[tableSizeIndex]);
        return table;
    }

    public Person[] moveOldTableValues(int newTableSize) {

        Person[] cleanArray = new Person[itemsInTable];

        // zo záznamov v aktuálnej tabulke sa urobí jedno pole bez medzier
        int i = 0;
        for (Person string : table)
            if (!string.equals("-1") && !string.equals("")) {
                cleanArray[i] = new Person();
                cleanArray[i] = string;
                i++;
            }

        // zväčšenie pôvodnej tabuľky
        table = new Person[newTableSize];
        tableSize = newTableSize;

        for (i = 0; i < tableSize; i++) { // na všetky mená sa zapíše -1 akože je index voľný
            table[i] = new Person();
            table[i].fullName = "-1";
        }

        System.out.println("Nová tabuľka bola vytvorená");

        // prehashuj staré záznami do novej tabuľky
        doubleHashFunction(cleanArray, table);

        return table;
    }

    public Person findValue(String wantedValue) {

        int arrayIndexHash = calculateStringAsciValue(wantedValue) % tableSize; // podľa mena vypočítame kde je osoba uložená

        int stepDistance = offset - calculateStringAsciValue(wantedValue) % offset; // vypočítanie posunutia v tabuľke

        while (table[arrayIndexHash].fullName != "-1") {

            if (table[arrayIndexHash].fullName.equals(wantedValue))
                return table[arrayIndexHash];

            arrayIndexHash += stepDistance;
            arrayIndexHash %= tableSize;
        }

        // System.out.println("Dáta: " + wantedValue + " sa v tabulke nenašli");
        return null;
    }

}