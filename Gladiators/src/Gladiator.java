public class Gladiator {
    private String name;
    private int minDamage;
    private int maxDamage;
    private int minDefence;
    private int maxDefence;
    private int hp;

    public Gladiator(String name, int minDamage, int maxDamage, int minDefence, int maxDefence, int hp) {
        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.minDefence = minDefence;
        this.maxDefence = maxDefence;
        this.hp = hp;
    }

    public int rollDamage(){
        return (int) ((Math.random() * (maxDamage - minDamage)) + minDamage);
    }
    public int rollDefense(){
        return (int) ((Math.random() * (maxDefence - minDefence)) + minDefence);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
