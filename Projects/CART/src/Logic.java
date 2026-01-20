import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Logic {
    private Node root;
    private final Data data;

    public Logic() {
        this.data = new Data();
        generateATree();
    }

    private void generateATree() {
        root = new Node();
        root.setAnimals(data.getAnimals());
        ArrayList<Node> queue = new ArrayList<>();
        queue.add(root);

        root.setCheckReq((i) -> ((Double) i < 25));

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

                }catch (NumberFormatException e){
                    HashSet<String> options = new HashSet<>();
                    for(HashMap<String, String> animal : current.getAnimals()){
                        options.add(animal.get(attribute));
                    }

                    for(String option : options){
                        Check req = (i) -> (i.equals(option));
                        ArrayList<HashMap<String, String>> leftBranch = new ArrayList<>();
                        ArrayList<HashMap<String, String>> rightBranch = new ArrayList<>();

                        for(HashMap<String, String> animal : current.getAnimals()){
                            if(req.check(animal)){
                                leftBranch.add(animal);
                            }else{
                                rightBranch.add(animal);
                            }
                        }

                        double weight = countWeight(leftBranch, rightBranch, current.getAnimals().size());
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

            for(HashMap<String, String> animals : current.getAnimals()){
                assert false;
                if(bestReq.check(animals)){
                    left.addAnimal(animals);
                }else{
                    right.addAnimal(animals);
                }
            }
            current.setCheckReq(bestReq);
            current.setCheckString(bestAttribute);
        }
    }

    private double countWeight(ArrayList<HashMap<String, String>> left, ArrayList<HashMap<String, String>> right, double total){
        return (left.size()/total) * countGini(left) + (right.size()/total) * countGini(right);
    }

    private double countGini(ArrayList<HashMap<String, String>> list){
        String type = Settings.type;
        HashMap<String, Double> types = new HashMap<>();
        for(HashMap<String, String> animal : list){
            if(types.containsKey(animal.get(type))){
                types.replace(animal.get(type), types.get(animal.get(type)) + 1);
            }else{
                types.put(animal.get(type), 1.0);
            }
        }

        double total = 0;
        for(Double number : types.values()){
            total += (Math.pow(number/list.size(), 2));
        }

        return 1 - total;
    }
}
