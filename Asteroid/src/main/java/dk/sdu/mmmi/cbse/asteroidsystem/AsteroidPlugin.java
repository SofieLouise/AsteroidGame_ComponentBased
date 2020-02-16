/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author sofielouise
 */
public class AsteroidPlugin implements IGamePluginService {

    private Entity asteroid;
    private List<Entity> asteroids;
    private final int MAX_NUM_ASTEROIDS = 3;
    private Random random = new Random();

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        asteroids = new ArrayList<>();
        for (int i = 0; i <= random.nextInt(MAX_NUM_ASTEROIDS); i++) {
            asteroid = createAsteroid(gameData);
            System.out.println("Build asteroid: " + (i + 1) + asteroid.getID());
            asteroids.add(asteroid);
            world.addEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {

        float deacceleration = 10;
        float acceleration = 100;
        float maxSpeed = 200;
        float rotationSpeed = 0;
        float x, y;
        if (random.nextBoolean()) {
            x = gameData.getDisplayWidth();
            y = 0;
        } else {
            x = 0;
            y = gameData.getDisplayHeight();
        }
        float radians = 3.1415f / random.nextInt(10);

        Entity asteroid = new Asteroid();
        asteroid.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        asteroid.add(new PositionPart(x, y, radians));

        return asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        asteroids.forEach(world::removeEntity);
    }

}
