package fr.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.sfvl.doctesting.DocWriter;
import org.sfvl.doctesting.junitextension.ApprovalsExtension;

public class OperationsTest {

    DocWriter doc = new DocWriter();
    @RegisterExtension
    ApprovalsExtension extension = new ApprovalsExtension(doc);

    /**
     * In mathematics, a square is the result of multiplying a number by itself.
     */
    @Test
    public void calculate_the_square_of_a_number() {
        int a = 5;
        int result = Operations.square(a);
        doc.write(String.format("Square of %d equals %d", a, result));
    }
}

