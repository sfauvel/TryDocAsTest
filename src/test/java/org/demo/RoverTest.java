package org.demo;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.sfvl.doctesting.junitextension.ApprovalsExtension;
import org.sfvl.doctesting.junitextension.HtmlPageExtension;
import org.sfvl.doctesting.junitextension.SimpleApprovalsExtension;

import java.util.ArrayList;
import java.util.List;


@ExtendWith(HtmlPageExtension.class)
public class RoverTest {

    @RegisterExtension
    static ApprovalsExtension doc = new SimpleApprovalsExtension();

    @Nested
    class Initialization {

    @Test
    public void should_start_at_initial_position() {
        final Rover rover = new Rover();

        doc.write("Initial " + roverToString(rover));
    }

    @Test
    public void should_start_at_a_given_initial_starting_point() {
        int intial_x = 3;
        int intial_y = 2;
        final Rover rover = new Rover(intial_x, intial_y);

        doc.write("When initialized rover with " + intial_x + ", " + intial_y
                + " then " + roverToString(rover));
    }

}

    @Test
    public void should_turn_right() {
         Rover rover = new Rover();

        doc.write("Initial " + roverToString(rover),"","");

        List<String> directions = new ArrayList<String>();

        final int nbOfOperation = 4;
        for (int i = 0; i < nbOfOperation; i++) {
            rover = rover.turnRight();
            directions.add(rover.getDirection());
        }

        doc.write("After turning right "+nbOfOperation+" times, direction is " + String.join(" then ", directions));
    }

    private String roverToString(Rover rover) {
        return "position is " + rover.getX() + ", " + rover.getY()
                + " direction is " + rover.getDirection();
    }

}
