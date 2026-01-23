import java.util.ArrayList;

public class ArrayWrapper {
    private final int[] numbers;

    public ArrayWrapper() {
        numbers = new int[]{1, 3, 5, 6, 7, 2, -10};
    }

    public int countIf(Count count){
        int amount = 0;

        for(Integer numb : numbers){
            if(count.countIf(numb)){
                amount++;
            }
        }

        return amount;
    }

    public int maxBy(Max max){
        int maxNumb = Integer.MIN_VALUE;

        for(Integer numb : numbers){
            if(max.max(numb) > maxNumb){
                maxNumb = numb;
            }
        }

        return maxNumb;
    }

    public ArrayList<Integer> takeWhile(Take take){
        ArrayList<Integer> newList = new ArrayList<>();

        for(Integer numb : numbers){
            if(take.check(numb)){
                newList.add(numb);
            }else{
                return newList;
            }
        }

        return null;
    }
}
