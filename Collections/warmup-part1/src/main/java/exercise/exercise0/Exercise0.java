package exercise.exercise0;

import java.util.*;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Create a List (ArrayList or LinkedList), add elements to it and print all of them using ListIterator
 *             for loop and foreach loop.
 *
 */
public class Exercise0 {

    public Exercise0(){
    }

    public void iterateThroughList(){
        List<Integer> integerArrayList = new ArrayList<Integer>();
        for (int i=0;i<=100;i++){
            integerArrayList.add(i);
        }
        // TODO Exercise #0 a) Create a list (ArrayList or LinkedList) and add elements to it
        // TODO Exercise #0 a) Don't forget to specify the type of the list (Integer, String etc.)
        // TODO Exercise #0 b) Iterate through the list using ListIterator and print all its element

        // TODO Exercise #0 c) Iterate through the list using classic for loop and print all its elements
        ListIterator<Integer> it = integerArrayList.listIterator();
        while (it.hasNext()){
            Integer integer = it.next();
            System.out.println(integer + " -1");
        }

        for (Integer integer : integerArrayList)
            System.out.println(integer + " -2");

        // TODO Exercise #0 d) Iterate through the list using foreach loop and print all its elements
        for (int i = 0; i<integerArrayList.size(); i++){
            System.out.println(integerArrayList.get(i) + " -3");
        }

    }

    public static void main(String[] args) {
        Exercise0 exercise0 = new Exercise0();
        exercise0.iterateThroughList();
    }
}
