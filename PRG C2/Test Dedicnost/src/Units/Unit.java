package Units;

public abstract class Unit {
    protected String name;
    protected int health;
    protected String basicAttack;

    public Unit(String name) {
        this.name = name;
        health = 100;
    }

    public abstract void takeTurn(Unit unit);

    protected void takeDamage(int damage) {
        health -= damage;
    }
    protected void attack(Unit unit, int damage){
        unit.takeDamage(damage);
    }

    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
}
