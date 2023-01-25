public class LambdaToRefExample {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    numbers.forEach(n -> System.out.println(n)); //lambda

    List<Integer> numbers2 = Arrays.asList(1, 2, 3);
    numbers2.forEach(System.out::println); //ref

    Function<Integer, Integer, Integer> add = (x, y) -> x + y; //lambda
    int result = add.apply(5, 3);
    System.out.println(result);

}