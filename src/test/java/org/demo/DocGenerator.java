package org.demo;

import org.sfvl.doctesting.MainDocumentation;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Generates full documentation and converts it to html.
 */
public class DocGenerator extends MainDocumentation {

    private static final Path HTML_DOCS_PATH = Paths.get("docs");
    private static final String DOC_FILENAME = "index";
    private ConvertToHtml convertToHtml = new ConvertToHtml(getDocRootPath(), HTML_DOCS_PATH);

    public void execute() throws IOException {
        System.out.println("Generate root documentation");
        generate("", DOC_FILENAME);

        final Path docFilename = getDocRootPath().resolve(DOC_FILENAME + ".adoc");
        System.out.println("\t" + Paths.get(".").resolve(Paths.get("").toAbsolutePath().relativize(docFilename)) + " was generated");
        convertToHtml.execute(docFilename.toFile());
    }

    public static void main(String... args) throws IOException {
        final DocGenerator generator = new DocGenerator();
        generator.execute();
    }

}

