import cs1302.genlistadt.GenList;
import java.util.*;
import java.util.function.*;

/**
 * Driver class for LinkedGenList objects which tests 
 * map, reduce, filter, min, and allMatch methods.
 */
public class LinkedGenListTest {

    public static void main(String[] args) {
        GenList<String> strList = new LinkedGenList<String>();
        strList.add("Python");
        strList.add("Java");
        strList.add("Swift");
        strList.add("Ruby");

        GenList<Integer> integerList = new LinkedGenList<Integer>();
        integerList.add(10);
        integerList.add(15);
        integerList.add(20);
        integerList.add(25);
        
        LinkedGenListTest.demoMap(strList, integerList);
        LinkedGenListTest.demoReduce(strList, integerList);
        LinkedGenListTest.demoFilter(strList, integerList);
    } // main

    /**
     * Tests the {@code map} method with parameters of {@code String} and {@code Integer}. 
     * 1.1 is added to all elements and are converted to double for {@code Integer}.
     * All elements are capitalized for {@code String}.
     * 
     * @param str object of type {@code String}
     * @param integer object of type {@code Integer}
     */
    public static void demoMap(GenList<String> str, GenList<Integer> integer) {

        // Makes all elements of String upper-case
        Function<String, String> strMap = (String i) -> i.toUpperCase();
        System.out.println("Demo Method: demoMap");
        System.out.println("Type: String");
        System.out.println("Original List: " + str.makeString(", "));
        System.out.println("New Mapped List: " + str.map(strMap).makeString(", "));
        System.out.println();

        // Adds 1.1 to the Integer and converts to Double
        Function<Integer, Double> integerMap = (Integer j) -> j + 1.1;
        System.out.println("Demo Method: demoMap"); 
        System.out.println("Type: Integer");
        System.out.println("Original List: " + integer.makeString(", "));
        System.out.println("New Mapped List: " + integer.map(integerMap).makeString(", "));
        System.out.println();
    } // demoMap

    /**
     * Tests the {@code reduce} method with parameters of {@code String} and {@code Integer}.
     * All elements of {@code Integer} are multiplied by each other to reduce the list.
     * The first character of {@code String} are combined into one to reduce the list.
     * 
     * @param str object of type {@code String}
     * @param integer object of type {@code Integer}
     */
    public static void demoReduce(GenList<String> str, GenList<Integer> integer) {
        
        // Reduces list by only printing first letter of the Strings
        BinaryOperator<String> strReduce = (String a, String b) -> a.concat(b.substring(0, 1));
        System.out.println("Demo Method: demoReduce");
        System.out.println("Type: String");
        System.out.println("Original List: " + str.makeString(", "));
        System.out.println("New Reduced List: " + str.reduce("Characters: ", strReduce));
        System.out.println();

        // Reduces list by multiplying
        BinaryOperator<Integer> integerReduce = (Integer a, Integer b) -> a * b;
        System.out.println("Demo Method: demoReduce");
        System.out.println("Type: Integer");
        System.out.println("Original List: " + integer.makeString(", "));
        System.out.println("New Reduced List: " + integer.reduce(1, integerReduce));
        System.out.println();
    } // demoReduce

    /**
     * Tests the {@code filter} method with parameters of {@code String} and {@code Integer}.
     * Elements less than 15 and greater than 20 are filtered out for {@code Integer}.
     * Elements that do not contain the letter "y" or "v" are filtered out for {@code String}.
     * 
     * @param str object of type {@code String}
     * @param integer object of type {@code Integer}
     */
    public static void demoFilter(GenList<String> str, GenList<Integer> integer) {
    
        // True if String has respective letter
        Predicate<String> strFltr = (String a) -> (a.indexOf("y") != -1) || (a.indexOf("v") != -1);
        System.out.println("Demo Method: demoFilter");
        System.out.println("Type: String");
        System.out.println("Original List: " + str.makeString(", "));
        System.out.println("New Filtered List: " + str.filter(strFltr).makeString(", "));
        System.out.println();

        // True if the Integer is < 15 and > 20
        Predicate<Integer> integerFilter = (Integer a) -> (a < 15 || a > 20);
        System.out.println("Demo Method: demoFilter");
        System.out.println("Type: Integer");
        System.out.println("Original List: " + integer.makeString(", "));
        System.out.println("New Filtered List: " + integer.filter(integerFilter).makeString(", "));
        System.out.println();
    } // demoFilter
    
} // LinkedGenListTest
