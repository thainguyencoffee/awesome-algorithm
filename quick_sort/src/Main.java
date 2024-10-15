import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    static List<Integer> quicksort(List<Integer> list) {
        if (list.size() < 2) return list;

        int pivot = list.get(0);
        List<Integer> less = list.stream().filter(l -> l < pivot).toList();
        List<Integer> greater = list.stream().filter(l -> l > pivot).toList();

        return Stream.concat(
                        Stream.concat(quicksort(less).stream(), Stream.of(pivot)),
                        quicksort(greater).stream())
                .toList();
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(33, 10, 55, 71, 29, 60, 1);

        System.out.println(quicksort(numbers));
    }
}