package pgdp.stream;

import java.util.ArrayList;

/**
 * test
 */
public class test {

    public static void main(String[] args) {
        ArrayList<Integer> a= (ArrayList<Integer>) Stream.of(1, 2, 3, 4, 5, 6).map(i -> i / (i - 1)).distinct().onErrorFilter().toCollection(ArrayList::new);

    }
}