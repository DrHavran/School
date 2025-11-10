public class Main {
    public static void main(String[] args) {
        Gladiator glad1 = new Gladiator("Karel", 2, 5,2,8,30);
        Gladiator glad2 = new Gladiator("David", 2, 5,2,8,30);

        Team lion = new Team();
        Team raven = new Team();

        lion.addMember(glad1);
        raven.addMember(glad2);

        Arena arena = new Arena();

        arena.addTeam(lion);
        arena.addTeam(raven);

        arena.simulateOneRound();
    }
}