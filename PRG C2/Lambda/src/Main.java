import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] words = {"cari", "elephant", "dog", "airplane"};
        Sort sort = ((s1, s2) -> Integer.compare(s1.length(), s2.length()));

        sortBy(words, sort);
        System.out.println(Arrays.toString(words));
    }

    private static void sortBy(String[] words, Sort sort) {
        for(int i = 0; i < words.length-1; i++) {
            if(sort.sort(words[i], words[i+1]) == 1){
                String temp = words[i];

                words[i] = words[i+1];
                words[i+1] = temp;
                if(i > 1){
                    i -= 2;
                }else{
                    i = -1;
                }
            }
        }
    }
}