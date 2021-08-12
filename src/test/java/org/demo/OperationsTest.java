package org.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.sfvl.doctesting.junitextension.ApprovalsExtension;
import org.sfvl.doctesting.utils.DocPath;
import org.sfvl.doctesting.utils.DocWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class OperationsTest {

    @RegisterExtension
    static ApprovalsExtension doc = new ApprovalsExtension(new DocWriter());

    @AfterAll
    static public void writeFileToConvertToHtml() throws IOException {
        final DocPath docPath = new DocPath(OperationsTest.class);

        String content = String.join("\n",
                doc.getDocWriter().defineDocPath(Paths.get(".")),
                ":nofooter:",
                "include::" + docPath.approved().fullname() + "[]");

        try (FileWriter fileWriter = new FileWriter(docPath.page().path().toFile())) {
            fileWriter.write(content);
        }
    }

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

