import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] tape = {0, 0, 0, 0, 0, -2, -2};

        Robot robot = new Robot("palindrome 5");

        while(!robot.checkEnd()){
            int index = robot.getIndex();
            tape[index] = robot.checkField(tape[index]);
        }

        System.out.println(Arrays.toString(tape));
        System.out.println("index: " + robot.getIndex());
        System.out.println("status: "+ robot.getStatus());
    }
}