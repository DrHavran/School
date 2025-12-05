package Units;

public class Mage extends Unit{

    public Mage(String name) {
        super(name);
        basicAttack = "fireball";
    }

    @Override
    public void takeTurn(Unit unit){
        int damage = (int)(Math.random() * 101);
        System.out.println("Mage " + name + " is casting: " + basicAttack + " for " + damage + " damage");
        unit.attack(unit, damage);
        System.out.println("He hit " + unit.getName() + ", he has only " + unit.getHealth() + " health left");
    }
}
