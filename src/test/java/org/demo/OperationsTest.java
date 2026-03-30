package org.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import com.github.docastest.doctesting.junitextension.ApprovalsExtension;
import com.github.docastest.doctesting.junitextension.HtmlPageExtension;
import com.github.docastest.doctesting.junitextension.SimpleApprovalsExtension;

@ExtendWith(HtmlPageExtension.class)
@DisplayName("Operations")
public class OperationsTest {

    @RegisterExtension
    static ApprovalsExtension doc = new SimpleApprovalsExtension();

    /**
     * In mathematics, a square is the result of multiplying a number by itself.
     */
    @Test
    public void calculate_the_square_of_a_number() {
        int a = 5;
        int result = Operations.square(a);
        doc.write(String.format("The square of %d is equal to %d", a, result));
    }

}

