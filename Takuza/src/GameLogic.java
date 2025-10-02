import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class GameLogic {
    public int[][] board;
    private int size;

    public int loadIntList() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(Settings.filePath));
        int size = sc.nextLine().split(" ").length;
        sc.close();

        board = new int[size][size];
        this.size = size;
        return size;
    }

    public void loadBoard() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(Settings.filePath));

        for (int y = 0; y < size; y++) {
            String[] line = sc.nextLine().split(" ");
            for (int x = 0; x < line.length; x++) {
                if(!line[x].equals("-")){
                    board[x][y] = Integer.parseInt(line[x]);
                }else{
                    board[x][y] = -1;
                }
            }
        }
        sc.close();
    }

    public String getCell(int x, int y) {
        int number = board[x][y];
        if(number == -1){
            return "";
        }
        return String.valueOf(number);
    }

    public void setValue(int x, int y, int value) {
        board[x][y] = value;
    }

    public boolean checkWin(){
        HashSet<String> columns = new HashSet<>();
        HashSet<String> rows = new HashSet<>();
        for(int y = 0; y < size; y++){
            StringBuilder column = new StringBuilder();
            StringBuilder row = new StringBuilder();


            int streakX = 0;
            int streakY = 0;
            int numberX = 0;
            int numberY = 0;

            for(int x = 0; x < size; x++){

                //Streak check on the X axis
                if(board[x][y] == numberX){
                    streakX++;
                    if(streakX == 3){
                        System.out.println("streak");
                        return false;
                    }
                }else{
                    numberX = board[x][y];
                    streakX = 1;
                }

                if(board[x][y] == -1){
                    System.out.println("empty number");
                    return false;
                }
                column.append(board[x][y]);

                //Streak check on the Y axis
                if(board[y][x] == numberY){
                    streakY++;
                    if(streakY == 3){
                        System.out.println("streak");
                        return false;
                    }
                }else{
                    numberY = board[y][x];
                    streakY = 1;
                }
                row.append(board[y][x]);
            }


            //checks if there is the same amount of 0's and 1's
            if(column.toString().replaceAll("1", "").length()!=column.length()/2){
                System.out.println("1/0");
                return false;
            }
            if(row.toString().replaceAll("1", "").length()!=row.length()/2){
                System.out.println("1/0");
                return false;
            }

            //checks if there isn't any duplicate rows/columns
            if(columns.contains(column.toString())){
                System.out.println("duplicate column");
                return false;
            }else{
                columns.add(column.toString());
            }
            if(rows.contains(row.toString())){
                System.out.println("duplicate row");
                return false;
            }else{
                rows.add(row.toString());
            }
        }

        return true;
    }
}
