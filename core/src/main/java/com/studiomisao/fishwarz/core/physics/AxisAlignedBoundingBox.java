package com.studiomisao.fishwarz.core.physics;

import pythagoras.f.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: Ingemar
 * Date: 2012-02-28
 * Time: 22:41
 * To change this template use File | Settings | File Templates.
 */
public class AxisAlignedBoundingBox {

    private Vector min;  // bottom left corner
    private Vector max;  // top right corner

    public AxisAlignedBoundingBox(Vector min, Vector max) {
        this.min = min;
        this.max = max;
    }

    public AxisAlignedBoundingBox(float xMin, float yMin, float xMax, float yMax) {
        this.min = new Vector(xMin, yMin);
        this.max = new Vector(xMax, yMax);
    }

    public Vector getMin() {
        return min;
    }

    public void setMin(Vector min) {
        this.min = min;
    }

    public Vector getMax() {
        return max;
    }

    public void setMax(Vector max) {
        this.max = max;
    }

    public static boolean overlap(AxisAlignedBoundingBox aabb, BoundingSphere sphere) {

        float squaredDistance = 0;

        // Process x
        if (sphere.getPosition().x < aabb.getMin().x) {
            float diff = sphere.getPosition().x - aabb.getMin().x;
            squaredDistance += diff * diff;
        } else if (sphere.getPosition().x > aabb.getMax().x) {
            float diff = sphere.getPosition().x - aabb.getMax().x;
            squaredDistance += diff * diff;
        }

        // Process y
        if (sphere.getPosition().y < aabb.getMin().y) {
            float diff = sphere.getPosition().y - aabb.getMin().y;
            squaredDistance += diff * diff;
        }  else if (sphere.getPosition().y > aabb.getMax().y) {
            float diff = sphere.getPosition().y - aabb.getMax().y;
            squaredDistance += diff * diff;
        }

        return squaredDistance <= sphere.getRadius();
    }
}
