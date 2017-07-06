package com.java_8_training.problems.lambdas;

import java.util.Arrays;
import java.util.List;

public class SortAndFilterList {

    public static void main(String[] args) {
        sortAndFilterList();
    }

    private static void sortAndFilterList() {
        List<String> animals = Arrays.asList("lion", "bear", "dear", "Dog", "Cat", "mouse", "cougar", "elephant", "giraffe", "lemur", "Bison", "chimpanzee", "hyena", "cheetah");
        System.out.println(animals);
        // TODO use lambda and functional interfaces / method references to sort by
        // 1. length
        animals.sort((a1,a2) -> String.valueOf(a2.length()).compareTo(String.valueOf(a1)));
        System.out.println(animals);
        // 2. reverse length
        animals.sort((a1,a2) -> String.valueOf(a1.length()).compareTo(String.valueOf(a2)));
        System.out.println(animals);
        // 3. alphabetically
        animals.sort(String::compareTo);
        System.out.println(animals);
        // 4. put the strings that contain 'e' first in the list. The other ones last.
        animals.stream().filter(animal -> animal.contains("e")).forEach(animal -> System.out.print(animal+' '));
        System.out.println();
        // 5. filter only the strings that have the first letter capitalized

    }
}
