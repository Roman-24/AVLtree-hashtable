package sk.stu.fiit;

public class Person implements Comparable {

    public String fullName;
    public int birthYear;

    public Person(String fullName, int birthYear) {
        this.fullName = fullName;
        this.birthYear = birthYear;
    }

    public Person() {
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
