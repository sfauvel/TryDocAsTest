package org.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.sfvl.docformatter.asciidoc.AsciidocFormatter;
import org.sfvl.doctesting.junitextension.ApprovalsExtension;
import org.sfvl.doctesting.utils.DocWriter;

@ExtendWith(DemoPageExtension.class)
@DisplayName("Operations")
public class OperationsTest {
    private static final AsciidocFormatter formatter = new AsciidocFormatter();

    @RegisterExtension
    static ApprovalsExtension doc = new ApprovalsExtension(new DocWriter());

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

