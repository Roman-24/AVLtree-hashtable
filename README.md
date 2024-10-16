# AVLtree hashtable

## EN
Custom implementation of AVLtree and Double Hash Table and their mutual comparison through a tester.

### AVLtree
Trees are generally used to represent organizational structures, file systems in an OS, family trees, etc. The basis is always a set of nodes (of parent and child types) that are interconnected and ordered.

The foundation is a binary search tree. However, in the case of an AVL tree, it is enhanced with a key feature—(self)balancing. Balancing is used to reduce the time complexity from O(n) to O(log n). This is achieved by the AVL Tree's ability to "swap nodes" between branches through balancing and rotations, thereby optimizing access to elements.

#### Brief description of the algorithm
The core of the insert method is the classic binary search tree principle. Either a node is created, or data is inserted by recursively traversing the tree. For each node, its height is set, and the so-called balance factor is calculated. This balance factor is used to determine whether rotation is needed to balance the tree. Based on this, and by comparing the key with the value attribute for a given node, the type of rotation to be performed is determined. There are 4 cases:

* Left rotation
* Left-right rotation
* Right rotation
* Right-left rotation

### Double Hash Table

This is a data structure where we try to calculate a specific index in an array (table) using a function to determine where the record will be stored. The simplest method of hashing is by using modulus or multiplication. In my implementation, I chose modulus in combination with the double hashing principle. The "second hash" is simply another calculation using addition/subtraction and modulus by a prime number, which aims to increase the probability of avoiding collisions. The second hash tries to increase the distribution of elements in the table to minimize collisions and create fewer data clusters.

#### Brief description of the algorithm
Input elements are taken one by one, hashed, and inserted into the table. If a collision occurs, the algorithm searches for the nearest available index.

The task also includes implementing table resizing. The table keeps track of the number of elements it contains, and if necessary, it will resize itself. The size of the table is always a prime number, which is predetermined (eliminating the need to search for a prime number), although this means the table might be disproportionately large relative to the number of elements it needs to hold, making it memory-inefficient. However, I chose to sacrifice memory for the sake of faster table functionality.

The resizing process involves taking all valid elements from the old table to create a "cleanArray," then enlarging the table to the desired size, and reinserting the original elements. These elements need to be rehashed, meaning all the indexes in the new table need to be recalculated since the table size has changed, which affects the hash calculation.

## SK
Vlastná implementácia AVLtree a Double Hash Table a ich vzájomné porovnanie cez tester.

### AVLtree
<p>Vo všeobecnosti sú stromy používané na reprezentáciu organizačnej štruktúry, systému zložiek v OS, rodokmene atď. Základom je vždy množina uzlov (typu rodič a potomkovia), kt. sú vzájomne prepojené a usporiadané.

Základom je binárny vyhľadávací strom. Ten je však v prípade AVL stromu vylepšený o kľúčovú vec a to (samo)vyvažovanie. Vyvažovanie slúži nato aby z časovej zložitosti O(n) stala zložitosť O(log n). Spôsobené je to tým, že AVL Tree vie pomocou vyvažovania a rotácii „vymieňať uzly“ medzi jednotlivými vetvami a tým zefektívniť prístup k prvkom. </p>

#### Stručný opis algoritmu
<p>Základom insert metódy je teda už spomínaný klasický princíp BVS. Kedy sa buď vytvorí uzol alebo sa pomocou rekurzie postupne vnárame v strome a vkladáme dáta. Pre každý node sa nastaví výška a vypočíta takzvaný balance faktor. Ten slúži nato aby sa určilo či je potrebné vykonať rotáciu a vyvážiť tak strom. Na základe toho a porovnávania key s value atribútom pre daný node sa potom určuje aká rotácia sa má vykonať. Poznáme 4 prípady:</p>

* Rotácia vľavo
* Rotácia vľavo a vpravo
* Rotácia vpravo
* Rotácia vpravo a vľavo

### Double Hash Table

<p>Jedná sa o dátovú štruktúru kedy sa snažíme pomocou funkcie vypočítať určitý index v poli (tabuľke) kam bude záznam uložený. Najjednoduchšia metóda hashovania je modulovanie alebo násobenie. V mojej implementácii som si vybral modulovanie v kombinácii s doublehash princípom, „druhý hash“ je iba ďalší výpočet pomocou sčítania/odčítania a modulovania prvočíslom kde je snaha o zvýšenie pravdepodobnosti aby nedošlo ku kolízii. Druhý hash je teda snaha o zväčšenie rozptylu prvkov v tabuľke aby sme museli riešiť menej kolízii a aby tak vznikali menšie klastre dát.</p>

#### Stručný opis algoritmu
<p>Vstupné elementy sú postupne brané, hashované a vkladané do tabuľky. V prípade, že nastane kolízia tak sa prístupy k hľadaniu najbližšieho voľného indexu</p>

<p>Úlohou je aj implementovať zväčšenie tabuľky. Tabuľka si preto eviduje počet prvkov, kt. obsahuje a ak je potrebné sama sa zväčší. Rozmer tabuľky je vždy nejaké prvočíslo, kt. je dopredu známe (odpadá tak nutnosť hľadania nejakého prvočísla) z čoho však vyplýva, že tabuľka môže byť neprimerane veľká počtu prvkov, kt. je potrebné obsiahnuť čo je pamäťovo neefektívne avšak bolo pre mňa výhodnejšie obetovať pamäť na úkor zrýchlenia funkčnosti tabuľky.
Samotné zväčšenie prebieha tak, že zo starej tabuľky vyberieme všetky platné prvky a urobíme z nich „cleanArray“ potom sa tabuľka zväčší na požadovaný rozmer a pôvodné prvky sa znovu vložia s tým, že je potrebné ich prehashovať, teda opätovne vypočítať všetky indexy v novej tabuľke, pretože indexy už budú rozdielne nakoľko tabuľka modifikovala svoju veľkosť a to má vplyv na výpočet hashu.</p>
