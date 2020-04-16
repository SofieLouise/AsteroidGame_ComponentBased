package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControlSystemTest {

    PlayerControlSystem playerControlSystem;
    Player player;
    World world;
    GameData gameData;

    @BeforeEach
    public void init() {
        playerControlSystem = new PlayerControlSystem();
        player = new Player();
        world = new World();
        gameData = new GameData();
        gameData.getKeys().setKey(0,true);
        gameData.setDisplayHeight(200);
        gameData.setDisplayWidth(200);
        gameData.setDelta(1);
        float deacceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = 100;
        float y = 100;
        float radians = 3.1415f / 2;
        player.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        player.add(new PositionPart(x, y, radians));
        player.add(new LifePart(5, 2));
        world.addEntity(player);
    }

    @Test
    public void movePlayer() {
        Player player = (Player) world.getEntities(Player.class).get(0);
        PositionPart positionPart = player.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        playerControlSystem.process(gameData, world);
        positionPart = player.getPart(PositionPart.class);
        Assert.assertNotEquals(x,positionPart.getX());
        Assert.assertNotEquals(y,positionPart.getY());
    }
}