package org.demo;

import org.sfvl.docformatter.AsciidocFormatter;
import org.sfvl.docformatter.Formatter;
import org.sfvl.doctesting.utils.ClassFinder;
import org.sfvl.doctesting.writer.Classes;
import org.sfvl.doctesting.writer.Document;
import org.sfvl.doctesting.writer.Options;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Generates full documentation and converts it to html.
 */
public class DocGenerator {

    private static final Path HTML_DOCS_PATH = Paths.get("docs");
    private static final String DOC_FILENAME = "index.adoc";
    private final Formatter formatter = new AsciidocFormatter();
    private final Path docRootPath = Paths.get("src", "test", "docs");
    private ConvertToHtml convertToHtml = new ConvertToHtml(docRootPath, HTML_DOCS_PATH);

    public void execute() throws IOException {
        System.out.println("Generate root documentation");
        new Document(this.build()).saveAs(Paths.get("").resolve(DOC_FILENAME));

        final Path docFilename = docRootPath.resolve(DOC_FILENAME);
        System.out.println("\t" + docFilename + " was generated");
        convertToHtml.execute(docFilename.toFile());
    }

    public String build() {
        return this.formatter.paragraphSuite(
                new Options(this.formatter).withCode(),
                formatter.title(1, "Documentation"),
                new Classes(this.formatter).includeClasses(
                        Paths.get(""),
                        new ClassFinder().testClasses(this.getClass().getPackage())
                )
        );

    }

    public static void main(String... args) throws IOException {
        new DocGenerator().execute();
    }

}

