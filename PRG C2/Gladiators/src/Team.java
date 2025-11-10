import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Gladiator> members = new ArrayList<>();
    private ArrayList<Gladiator> deadGladiators = new ArrayList<>();

    public void addMember(Gladiator gladiator){
        members.add(gladiator);
    }

    public ArrayList<Gladiator> getMembers() {
        return members;
    }

    public void removeMember(Gladiator gladiator){
        deadGladiators.add(gladiator);
    }

    public void removeDead(){
        for(Gladiator gladiator : deadGladiators){
            members.remove(gladiator);
        }
        deadGladiators.clear();
    }
}
