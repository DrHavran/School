import java.util.ArrayList;

public class Arena {
    private ArrayList<Team> teams = new ArrayList<>();

    public void addTeam(Team team){
        teams.add(team);
    }

    public void simulateOneRound(){
        for(Team team : teams){
            ArrayList<Team> enemyTeams = new ArrayList<>();
            for(Team loadingTeam : teams){
                if(loadingTeam != team){
                    enemyTeams.add(loadingTeam);
                }
            }
            for(Gladiator activeGladiator : team.getMembers()){
                Team pickedTeam = enemyTeams.get((int)(Math.random() * enemyTeams.size()));
                Gladiator pickedGladiator = pickedTeam.getMembers().get((int)(Math.random() * pickedTeam.getMembers().size()));

                //Fight
                int gladiatorOneHit = activeGladiator.rollDamage() - pickedGladiator.rollDefense();
                if(gladiatorOneHit > 0){
                    pickedGladiator.setHp(pickedGladiator.getHp() - gladiatorOneHit);
                }
                if(pickedGladiator.getHp() <= 0){
                    team.removeMember(pickedGladiator);
                }

                int gladiatorTwoHit = pickedGladiator.rollDamage() - activeGladiator.rollDefense();
                if(gladiatorTwoHit > 0){
                    activeGladiator.setHp(activeGladiator.getHp() - gladiatorTwoHit);
                }
                if(activeGladiator.getHp() <= 0){
                    pickedTeam.removeMember(activeGladiator);
                }

                team.removeDead();
                pickedTeam.removeDead();
            }
        }
        clearTeams();
    }

    public void clearTeams(){
        for(Team team : teams){
            if(team.getMembers().isEmpty()){
                teams.remove(team);
            }
        }
    }
    public void simulateRounds(){
        while(teams.size() > 1){
            simulateOneRound();
        }
    }
}
