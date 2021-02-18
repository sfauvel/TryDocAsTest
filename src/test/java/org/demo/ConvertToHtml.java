package org.demo;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.SafeMode;
import org.asciidoctor.jruby.AsciiDocDirectoryWalker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.asciidoctor.Asciidoctor.Factory.create;

public class ConvertToHtml {

    private final Path docRootPath;
    private final Path htmlDocsPath;

    public ConvertToHtml(Path docRootPath, Path htmlDocsPath) {
        this.docRootPath = docRootPath;
        this.htmlDocsPath = htmlDocsPath;
    }

    public void execute() {
        final List<File> scan = new AsciiDocDirectoryWalker("src").scan();
        execute(new AsciiDocDirectoryWalker(docRootPath.toString()).scan());
    }

    public void execute(File file) {
        execute(Arrays.asList(file));
    }

    public void execute(List<File> files) {
        System.out.println("Convert to html");
        try (Asciidoctor asciidoctor = create()) {
            for (File asciidocFile : files) {
                final Path outputPath = getOutputPath(asciidocFile);
                Files.createDirectories(outputPath);
                asciidoctor.convertFile(asciidocFile,
                        OptionsBuilder.options()
                                .safe(SafeMode.UNSAFE)
                                .toDir(outputPath.toFile()));

                System.out.println("\t" + Paths.get(".").resolve(outputPath.resolve(asciidocFile.getName().replace(".adoc", ".html"))) + " was generated");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Path getOutputPath(File asciidocFile) throws IOException {
        final Path relativizeToRootPath = docRootPath.relativize(asciidocFile.toPath());
        final Path outputFile = htmlDocsPath.resolve(relativizeToRootPath);
        return outputFile.getParent();
    }

}
