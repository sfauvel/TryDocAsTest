package fr.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.sfvl.doctesting.DocWriter;
import org.sfvl.doctesting.junitextension.ApprovalsExtension;

public class DocTest {

    DocWriter doc = new DocWriter();
    @RegisterExtension
    ApprovalsExtension extension = new ApprovalsExtension(doc);

    @Test
    public void check_doc_test_works() {
        int a = 5;
        int result = a * a;
        doc.write(String.format("Le carré de %d est %d", a, result));
    }
}
