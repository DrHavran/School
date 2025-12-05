package Units;

public class Warrior extends Unit{

    public Warrior(String name) {
        super(name);
        basicAttack = "sword";
    }

    @Override
    public void takeTurn(Unit unit){
        int damage = (int)(Math.random() * 101);
        System.out.println("Warrior " + name + " is fighting: " + basicAttack + " for " + damage + " damage");
        unit.attack(unit, damage);
        System.out.println("He hit " + unit.getName() + ", he has only " + unit.getHealth() + " health left");
    }
}
