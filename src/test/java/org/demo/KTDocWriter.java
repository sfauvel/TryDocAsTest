package org.demo;

import org.sfvl.codeextraction.CodeExtractor;
import org.sfvl.docformatter.Formatter;
import org.sfvl.doctesting.utils.ClassToDocument;
import org.sfvl.doctesting.utils.NoTitle;
import org.sfvl.doctesting.writer.DocWriter;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * This object is used to store the text that need to be written to the final document.
 */

public class KTDocWriter<F extends Formatter> extends DocWriter<Formatter> {

    public KTDocWriter() {
        super();
    }

    public KTDocWriter(F formatter) {
        super(formatter);
    }

    public String formatOutput(String title, Class<?> classFile, Method testMethod) {
        return String.join("",
                defineDocPath(testMethod.getDeclaringClass()),
                "\n\n",
                formatAdocTitle(title, testMethod),
                //CodeExtractor.getComment(classFile, testMethod).map(comment -> comment + "\n\n").orElse(""),
                read());
    }

    private String formatAdocTitle(String title, Method testMethod) {
        boolean isTitle = testMethod.getAnnotation(NoTitle.class) == null;

        return isTitle
                ? getFormatter().paragraph(
                getFormatter().blockId(titleId(testMethod)),
                getFormatter().title(1, formatTitle(title, testMethod)).trim()
        ) : "";
    }

    public String formatOutput(Class<?> clazz) {
        final KTClassDocumentation classDocumentation = new KTClassDocumentation(getFormatter()) {
            protected Optional<String> relatedClassDescription(Class<?> fromClass) {
                return Optional.ofNullable(fromClass.getAnnotation(ClassToDocument.class))
                        .map(ClassToDocument::clazz)
                        .map(CodeExtractor::getComment);
            }

            @Override
            public String getTitle(Class<?> clazz, int depth) {
                return String.join("\n",
                        formatter.blockId(titleId(clazz)),
                        super.getTitle(clazz, depth));
            }

        };


        return String.join("\n",
                defineDocPath(clazz),
                "",
                classDocumentation.getClassDocumentation(clazz)
        );
    }
}
