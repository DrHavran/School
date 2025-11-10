import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    static int sizeOfFrame = 500;
    static int size = 10;
    static Stack<double[]> points = new Stack<>();
    static JFrame frame = new JFrame();
    static JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));

            g2.drawLine(sizeOfFrame/2, 0, sizeOfFrame/2, sizeOfFrame);       //draws the lines
            g2.drawLine(0, sizeOfFrame/2, sizeOfFrame, sizeOfFrame/2);
            g2.fillOval(sizeOfFrame/2-3, sizeOfFrame/2-3, 6, 6);

            g2.setStroke(new BasicStroke(1));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
            for(int i = 1; i < size+1; i++){
                int gapSize = sizeOfFrame/(size*2);
                g2.drawLine(0, sizeOfFrame/2-i*gapSize, sizeOfFrame, sizeOfFrame/2-i*gapSize);
                g2.drawLine(0, sizeOfFrame/2+i*gapSize, sizeOfFrame, sizeOfFrame/2+i*gapSize);
                g2.drawLine(sizeOfFrame/2-i*gapSize, 0, sizeOfFrame/2-i*gapSize, sizeOfFrame);
                g2.drawLine(sizeOfFrame/2+i*gapSize, 0, sizeOfFrame/2+i*gapSize, sizeOfFrame);
            }
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            for (int i = points.size() - 1; i >= 0; i--) {
                double[] selectedPoint = points.get(i);
                int gapSize = sizeOfFrame / (size * 2);
                int screenX = (int)((double) sizeOfFrame / 2 + selectedPoint[0] * gapSize);
                int screenY = (int)((double) sizeOfFrame / 2 - selectedPoint[1] * gapSize);
                g2.fillOval(screenX - 2, screenY - 2, 4, 4);
            }
        }
    };

    public static void main(String[] args) {
        askAttributes();
        setUpJFrame();
    }

    public static void createFunction(String function, double accuracy){
        double progress = 0;
        for(double i = -size; i<=size; i=i+accuracy){
            double x = i;
            double y = countFunction(function, x);
            double[] point = new double[2];
            point[0] = x;
            point[1] = y;
            if(progress!=((i + size) / (2 * size)) * 100){
                progress = ((i + size) / (2 * size)) * 100;
                System.out.printf("Progress: %.2f%%\n", ((i + size) / (2 * size)) * 100);
            }
            if(x>sizeOfFrame||y>sizeOfFrame||Double.isNaN(y)){
                continue;
            }
            points.add(point);
            // System.out.println(Arrays.toString(point)); //prints the exact points
        }
        frame.repaint();
        System.out.println("Done");
    }

    public static double countFunction(String function, double x){
        String operation = "+";
        double total = 0;

        String RightSide = function.split("=")[1].trim();
        RightSide = RightSide.replaceAll("x", String.valueOf(x));
        RightSide = RightSide.replaceAll("e", String.valueOf(Math.E));
        RightSide = RightSide.replaceAll("pi", String.valueOf(Math.PI));

        String[] RightSideParts = RightSide.split(" ");

        for(String Part : RightSideParts){
            if(Part.equals("+")){
                operation = "+";
            }else if(Part.equals("-")){
                operation = "-";
            }else if(Part.equals("*")){
                operation = "*";
            }else if(Part.equals("/")){
                operation = "/";
            }else if(isNumeric(Part)){
                total = add(operation, Double.parseDouble(Part), total);
            }else if(Part.contains("^")){
                total = add(operation, Math.pow(Double.parseDouble(Part.split("\\^")[0]), Double.parseDouble(Part.split("\\^")[1])), total);
            }else if(Part.contains("log")){
                double number = Double.parseDouble(Part.split("\\(")[1].split("\\)")[0]);
                if(Part.split("log")[1].split("\\(")[0].isEmpty()){
                    total = add(operation, Math.log10(number), total);
                }else{
                    double base = Double.parseDouble(Part.split("log")[1].split("\\(")[0]);
                    total = add(operation, Math.log(number) / Math.log(base), total);
                }
            }else if(Part.contains("abs")){
                double abs = Double.parseDouble(Part.split("\\(")[1].split("\\)")[0]);
                total = add(operation, Math.abs(abs), total);
            }else if(Part.contains("asin")){
                double asin = Double.parseDouble(Part.split("\\(")[1].split("\\)")[0]);
                total = add(operation, Math.asin(asin), total);
            }else if(Part.contains("acos")){
                double acos = Double.parseDouble(Part.split("\\(")[1].split("\\)")[0]);
                total = add(operation, Math.acos(acos), total);
            }else if(Part.contains("atan")){
                double atan = Double.parseDouble(Part.split("\\(")[1].split("\\)")[0]);
                total = add(operation, Math.atan(atan), total);
            }else if(Part.contains("sinh")){
                double sinh = Double.parseDouble(Part.split("\\(")[1].split("\\)")[0]);
                total = add(operation, Math.sinh(sinh), total);
            }else if(Part.contains("cosh")){
                double cosh = Double.parseDouble(Part.split("\\(")[1].split("\\)")[0]);
                total = add(operation, Math.cosh(cosh), total);
            }else if(Part.contains("tanh")){
                double tanh = Double.parseDouble(Part.split("\\(")[1].split("\\)")[0]);
                total = add(operation, Math.tanh(tanh), total);
            }else if(Part.contains("sin")){
                double sin = Double.parseDouble(Part.split("\\(")[1].split("\\)")[0]);
                total = add(operation, Math.sin(sin), total);
            }else if(Part.contains("cos")){
                double cos = Double.parseDouble(Part.split("\\(")[1].split("\\)")[0]);
                total = add(operation, Math.cos(cos), total);
            }else if(Part.contains("tan")){
                double tan = Double.parseDouble(Part.split("\\(")[1].split("\\)")[0]);
                total = add(operation, Math.tan(tan), total);
            }
        }
        return total;
    }
    public static double add(String operation, double number, double total){
        switch(operation){
            case "+":
                total += number;
                break;
            case "-":
                total -= number;
                break;
            case "*":
                total *= number;
                break;
            case "/":
                total /= number;
                break;
        }
        return total;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void askAttributes(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Function? ");
        String function = sc.nextLine();
        System.out.println("Accuracy? ");
        double accuracy = sc.nextDouble();
        System.out.println("Size? ");
        size = sc.nextInt();
        frame.setTitle(function);
        createFunction(function, accuracy);
    }

    public static void setUpJFrame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        panel.setPreferredSize(new Dimension(sizeOfFrame, sizeOfFrame));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}