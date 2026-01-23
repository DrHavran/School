public class Main {
    public static void main(String[] args) {
        ArrayWrapper wrap = new ArrayWrapper();

        System.out.println(wrap.countIf((i) -> i % 2 == 0));
        System.out.println(wrap.maxBy(Math::abs));
        System.out.println(wrap.takeWhile(n -> n > 0));

    }
}