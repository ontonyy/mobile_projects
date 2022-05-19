package com.ontony.pingpong.helper;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BodyHelper {

    public static Body createBody(float x, float y, float width, float height, boolean isStatic, float density, World world, ContactType type) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = !isStatic ? BodyDef.BodyType.DynamicBody : BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x / Const.PPM, y / Const.PPM);
        bodyDef.fixedRotation = true;
        Body body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 / Const.PPM, height / 2 / Const.PPM);

        FixtureDef  fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.density = density;
        body.createFixture(fixDef).setUserData(type);

        shape.dispose();
        return body;
    }
}
