package Units;

public class Archer extends Unit{

    public Archer(String name) {
        super(name);
        basicAttack = "arrow";
    }

    @Override
    public void takeTurn(Unit unit){
        int damage = (int)(Math.random() * 101);
        System.out.println("Archer " + name + " is shooting: " + basicAttack + " for " + damage + " damage");
        unit.attack(unit, damage);
        System.out.println("He hit " + unit.getName() + ", he has only " + unit.getHealth() + " health left");
    }
}
