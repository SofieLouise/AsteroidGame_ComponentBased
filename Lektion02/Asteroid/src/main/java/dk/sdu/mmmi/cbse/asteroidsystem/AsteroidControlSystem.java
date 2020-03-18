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
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.Random;

/**
 *
 * @author sofielouise
 */
public class AsteroidControlSystem implements IEntityProcessingService {

    private final Random random = new Random();
    private int processCounter = 0;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            movingPart.setRotationSpeed(0);

            movingPart.setUp(true);

            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);

            updateShape(asteroid);
            processCounter++;
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();
        float radius = 8;
        entity.setRadius(radius);

        shapex[0] = (float) (x + Math.cos(radians) * radius);
        shapey[0] = (float) (y + Math.sin(radians) * radius);

        shapex[1] = (float) (x + Math.cos(radians - 3.1415f / 2) * radius);
        shapey[1] = (float) (y + Math.sin(radians - 3.1145f / 2) * 8);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * radius);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * radius);

        shapex[3] = (float) (x + Math.cos(radians + 3.1415f / 2) * radius);
        shapey[3] = (float) (y + Math.sin(radians + 3.1415f / 2) * radius);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
}
