import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(44);
        list.add(552);
        list.add(635);
        list.add(999);
        System.out.println(list.toString());
        System.out.println(evenCount(list)+ " четных числа");
        findMinMax(
                list.stream().parallel(),
                Integer::compareTo,
                (integer, integer2) -> System.out.println(integer +" "+ integer2)
        );
    }
    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer
    ){
        List<? extends T> list = stream.collect(Collectors.toList());
        Optional<? extends T> min = list.stream().min(order);
        Optional<? extends T> max = list.stream().max(order);

        if (!list.isEmpty())
            minMaxConsumer.accept(min.get(),max.get());
        else {
            minMaxConsumer.accept(null,null);
        }
    }
    public static int evenCount(List<Integer> list){
        return  (int)list.stream()
                .filter(i-> i%2==0)
                .count();
    }
}