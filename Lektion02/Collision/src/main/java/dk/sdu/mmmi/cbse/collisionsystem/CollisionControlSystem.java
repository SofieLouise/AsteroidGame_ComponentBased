/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.asteroidsystem.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.enemysystem.Enemy;
import dk.sdu.mmmi.cbse.playersystem.Player;
import java.util.List;

/**
 *
 * @author sofielouise
 */
public class CollisionControlSystem implements IPostEntityProcessingService {

    private int numberOfCollisions;
    private Entity player;
    private PositionPart playerPosition;
    private Circle playerCircle;
    private List<Entity> enemies;
    private List<Entity> asteroids;
    private List<Entity> players;

    @Override
    public void process(GameData gameData, World world) {
        players = world.getEntities(Player.class);
        enemies = world.getEntities(Enemy.class);
        
        world.getEntities(Asteroid.class).forEach((asteroid) -> {
            Circle asteroidCircle = createCircle(asteroid);
            enemies.forEach((enemy) -> {
                if(checkCollision(asteroidCircle, createCircle(enemy))){
                    world.removeEntity(enemy);
                }
            });
            players.forEach((player) -> {
                if(checkCollision(createCircle(player), asteroidCircle)){
                world.removeEntity(player);
            }
            });

            // TODO check for bullets (asteroid life - 1)
        });
        enemies.forEach((enemy) -> {
            //TODO check for player bullets
        });
        //TODO check if player is hit with enemy bullet
    }
    
    private Circle createCircle (Entity entity) {
        PositionPart positionPart = entity.getPart(PositionPart.class);
        return new Circle(positionPart.getX(), positionPart.getY(), entity.getRadius());
    }

    private boolean checkCollision(Circle circle1, Circle circle2) {
        float dx = circle1.getCentreX() - circle2.getCentreX();
        float dy = circle1.getCentreY() - circle2.getCentreY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < circle1.getRadius() + circle2.getRadius();
    }
}
