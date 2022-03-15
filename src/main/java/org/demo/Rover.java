package org.demo;

public class Rover {
    private final int intial_x;
    private final int intial_y;
    private String direction;

    public Rover(int intial_x, int intial_y) {
        this(intial_x, intial_y, "N");
    }

    public Rover() {
        this(0, 0);
    }

    public Rover(int intial_x, int intial_y, String direction) {
        this.intial_x = intial_x;
        this.intial_y = intial_y;
        this.direction = direction;
    }

    public int getX() {
        return intial_x;
    }

    public int getY() {
        return intial_y;
    }

    public String getDirection() {
        return direction;
    }

    public Rover turnRight() {
        if(this.direction == "N") {
            return new Rover(intial_x, intial_y, "E");
        } else if(this.direction == "E") {
            return new Rover(intial_x, intial_y, "S");
        } else if(this.direction == "S") {
            return new Rover(intial_x, intial_y, "W");
        } else if(this.direction == "W") {
            return new Rover(intial_x, intial_y, "N");
        }
        throw new RuntimeException("Unknown direction");
    }
}
