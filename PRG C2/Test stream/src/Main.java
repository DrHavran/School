import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/diamonds.csv"));
        sc.nextLine();

        ArrayList<Diamond> diamonds = new ArrayList<>();

        while(sc.hasNextLine()) {
               diamonds.add(new Diamond(sc.nextLine()));
        }

        // 1st
        System.out.println(diamonds.stream().map(Diamond::getCut).filter(p -> Objects.equals(p, "Fair")).count());

        ArrayList<Diamond> premium = diamonds.stream().filter(p -> Objects.equals(p.getCut(), "Premium")).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println(premium.stream().map(Diamond::getPrice).mapToDouble(p -> Double.parseDouble(String.valueOf(p))).sum() / premium.size());

        // 2nd

        diamonds.stream().filter(p -> p.getX() == p.getY()).forEach(p -> System.out.println(p.getCut() + "|" + p.getColor() + "|" + p.getPrice()));

        diamonds.stream().map(Diamond::getCut).distinct().forEach(System.out::println);
        // 3rd
        Double bestFair = diamonds.stream().filter(p -> Objects.equals(p.getCut(), "Fair")).map(Diamond::getPrice).max(Double::compareTo).get()/100;
        diamonds.stream().filter(p -> Objects.equals(p.getCut(), "Fair")).map(Diamond::getPrice).sorted(Comparator.reverseOrder()).forEach(
                p -> System.out.println(p/bestFair + "%")
        );
    }
}