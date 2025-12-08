package io.github.some_example_name.Managers;

import io.github.some_example_name.Entities.Entity;
import io.github.some_example_name.Entities.Monster.SmallZombie;
import io.github.some_example_name.Entities.Player.Player;
import io.github.some_example_name.Entities.Weapons.Gun;
import io.github.some_example_name.Entities.Weapons.Weapon;

import java.util.ArrayList;

public class EntityManager {
    private static EntityManager instance;
    public static EntityManager getInstance() {
        if (instance == null) instance = new EntityManager();
        return instance;
    }

    private final ArrayList<Entity> entities;
    private final ArrayList<Entity> toAdd;
    private final ArrayList<Entity> toRemove;

    private Player player;
    private Weapon weapon;

    public EntityManager() {
        this.entities = new ArrayList<>();
        this.toAdd = new ArrayList<>();
        this.toRemove = new ArrayList<>();
    }

    public void addTest(){
        this.player = new Player();
        this.weapon = new Gun();

        entities.add(new SmallZombie());
    }

    public void update(){
        player.update();
        weapon.update();
        for(Entity entity : entities){
            entity.update();
        }

        addEntities();
        removeEntities();
    }

    private void addEntities(){
        entities.addAll(toAdd);
        toAdd.clear();
    }
    private void removeEntities(){
        for(Entity entity : toRemove){
            entities.remove(entity);
        }
        toRemove.clear();
    }

    public void addEntity(Entity entity){
        toAdd.add(entity);
    }
    public void removeEntity(Entity entity){
        toRemove.add(entity);
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }   //getters
    public Entity[] getMainEntities() {
        Entity[] mainEntities = new Entity[2];
        if(player.getRotation().equals("up")){
            mainEntities[0] = weapon;
            mainEntities[1] = player;
        }else{
            mainEntities[0] = player;
            mainEntities[1] = weapon;
        }

        return mainEntities;
    }
    public Player getPlayer() {
        return player;
    }

    public float playerX(){
        return player.getSprite().getX();
    }
    public float playerY(){
        return player.getSprite().getY();
    }
    public float playerWidth(){
        return player.getSprite().getWidth();
    }
    public float playerHeight(){
        return player.getSprite().getHeight();
    }

    public float weaponX(){
        return weapon.getSprite().getX();
    }
    public float weaponY(){
        return weapon.getSprite().getY();
    }
    public float weaponWidth(){
        return weapon.getSprite().getWidth();
    }
    public float weaponHeight(){
        return weapon.getSprite().getHeight();
    }
}
