import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Logic {
    private Node root;
    private final Data data;

    public Logic() {
        this.data = new Data();
        generateATree();
        printATree();
    }

    private void generateATree() {
        root = new Node();
        root.setPoints(data.getPoints());
        ArrayList<Node> queue = new ArrayList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            Node current = queue.removeFirst();

            double bestWeight = Double.MAX_VALUE;
            String bestAttribute = "";
            Check bestReq = null;

            for(String attribute : data.getAttributes()){
                if(attribute.equals(Settings.name) || attribute.equals(Settings.type)){
                    continue;
                }

                try{
                    ArrayList<Double> values = new ArrayList<>();
                    for(HashMap<String, String> point : current.getPoints()){
                        values.add(Double.parseDouble(point.get(attribute)));
                    }
                    values = values.stream().sorted().collect(Collectors.toCollection(ArrayList::new));
                    ArrayList<Double> options = new ArrayList<>();
                    for(int i = 0; i < values.size()-1; i++){
                        options.add((values.get(i) + values.get(i+1)) / 2);
                    }

                    for(Double option : options){
                        Check req = (i) -> (Double.parseDouble((String) i) < option);

                        double weight = count(current, attribute, req);
                        if(weight < bestWeight){
                            bestWeight = weight;
                            bestAttribute = attribute;
                            bestReq = req;
                        }
                    }
                }catch (NumberFormatException e){
                    HashSet<String> options = new HashSet<>();
                    for(HashMap<String, String> point : current.getPoints()){
                        options.add(point.get(attribute));
                    }

                    for(String option : options){
                        Check req = (i) -> (i.equals(option));

                        double weight = count(current, attribute, req);
                        if(weight < bestWeight){
                            bestWeight = weight;
                            bestAttribute = attribute;
                            bestReq = req;
                        }
                    }
                }
            }
            Node left = new Node();
            Node right = new Node();

            current.setLeftBranch(left);
            current.setRightBranch(right);

            for(HashMap<String, String> point : current.getPoints()){
                assert bestReq != null;
                if(bestReq.check(point.get(bestAttribute))){
                    left.addPoint(point);
                }else{
                    right.addPoint(point);
                }
            }
            current.setCheckReq(bestReq);
            current.setCheckString(bestAttribute);

            if(countGini(left.getPoints()) != 0){
                queue.add(left);
            }
            if(countGini(right.getPoints()) != 0){
                queue.add(right);
            }
        }
    }

    private double count(Node current, String attribute, Check req){
        ArrayList<HashMap<String, String>> leftBranch = new ArrayList<>();
        ArrayList<HashMap<String, String>> rightBranch = new ArrayList<>();

        for(HashMap<String, String> point : current.getPoints()){
            if(req.check(point.get(attribute))){
                leftBranch.add(point);
            }else{
                rightBranch.add(point);
            }
        }

        return countWeight(leftBranch, rightBranch);
    }
    private double countWeight(ArrayList<HashMap<String, String>> left, ArrayList<HashMap<String, String>> right){
        double total = left.size() + right.size();
        return (left.size()/total) * countGini(left) + (right.size()/total) * countGini(right);
    }
    private double countGini(ArrayList<HashMap<String, String>> list){
        String type = Settings.type;
        HashMap<String, Double> types = new HashMap<>();
        for(HashMap<String, String> point : list){
            if(types.containsKey(point.get(type))){
                types.replace(point.get(type), types.get(point.get(type)) + 1);
            }else{
                types.put(point.get(type), 1.0);
            }
        }

        double total = 0;
        for(Double number : types.values()){
            total += (Math.pow(number/list.size(), 2));
        }

        return 1 - total;
    }

    private void printATree(){
        ArrayList<Node> mainQueue = new ArrayList<>();
        ArrayList<Node> subQueue = new ArrayList<>();
        
        mainQueue.add(root);

        while(!mainQueue.isEmpty()){
            Node current = mainQueue.removeFirst();

            if(current.getLeftBranch() != null){
                subQueue.add(current.getLeftBranch());
                subQueue.add(current.getRightBranch());
                System.out.print("[ " + current.getCheckString() + " ]");
            }else{
                System.out.print("[ ");
                current.getPoints().forEach(x -> System.out.print(x.get(Settings.name) + " "));
                System.out.print("- " + current.getPoints().getFirst().get(Settings.type));
                System.out.print(" ]");
            }

            if(mainQueue.isEmpty()){
                mainQueue.addAll(subQueue);
                subQueue.clear();
                System.out.println();
            }
        }
    }
}