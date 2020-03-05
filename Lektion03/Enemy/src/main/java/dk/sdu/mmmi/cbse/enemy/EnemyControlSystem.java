/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.enemy;

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
public class EnemyControlSystem implements IEntityProcessingService {

    private Random random = new Random();
    private int processCounter = 0;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);

            if (processCounter % 10 == 0) {
                movingPart.setLeft(random.nextBoolean());
                movingPart.setRight(random.nextBoolean());
            }
            movingPart.setUp(random.nextBoolean());

            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);

            updateShape(enemy);
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

        shapex[1] = (float) (x + Math.cos(radians - 4 * 3.1415f / 5) * radius);
        shapey[1] = (float) (y + Math.sin(radians - 4 * 3.1145f / 5) * radius);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * (radius / 8 * 5));
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * (radius / 8 * 5));

        shapex[3] = (float) (x + Math.cos(radians + 4 * 3.1415f / 5) * radius);
        shapey[3] = (float) (y + Math.sin(radians + 4 * 3.1415f / 5) * radius);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

}
