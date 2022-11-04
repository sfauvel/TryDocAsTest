package org.demo;

import org.junit.jupiter.api.Test;
import org.sfvl.docformatter.Formatter;
import org.sfvl.doctesting.utils.ClassesOrder.EncapsulateDeclared;
import org.sfvl.doctesting.utils.ClassesOrder.EncapsulateDeclaredClass;
import org.sfvl.doctesting.utils.ClassesOrder.EncapsulateDeclaredMethod;
import org.sfvl.doctesting.utils.DocPath;
import org.sfvl.doctesting.utils.OnePath;
import org.sfvl.doctesting.writer.ClassDocumentation;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KTClassDocumentation extends ClassDocumentation {
    private final Function<OnePath, Path> onePathToPath;

    public KTClassDocumentation(Formatter formatter) {
        super(formatter);
        this.onePathToPath = (o) -> {
            return Paths.get(o.filename());
        };
    }

    public KTClassDocumentation(Formatter formatter, Function<OnePath, Path> onePathToPath, Predicate<Method> methodFilter, Predicate<Class> classFilter) {
        super(formatter, onePathToPath, methodFilter, classFilter);

        this.onePathToPath = onePathToPath;
    }

    public String getClassDocumentation(Class<?> clazz) {
        return this.getClassDocumentation(clazz, 1);
    }

    public String getClassDocumentation(Class<?> clazz, int depth) {
//        ClassesOrder classesOrder = new ClassesOrder();
//        Stream<EncapsulateDeclared> declaredInOrder = classesOrder.getDeclaredInOrder(clazz, this.methodFilter, this.classFilter);
        final Stream<EncapsulateDeclaredMethod> declaredInOrder = Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Test.class))
                .map(m -> new EncapsulateDeclaredMethod(m));
        return this.getClassDocumentation(clazz, depth, (List) declaredInOrder.collect(Collectors.toList()));
    }

    private String getClassDocumentation(Class<?> clazz, int depth, List<EncapsulateDeclared> encapsulatedDeclarations) {
        Function<Path, String> includeWithOffset = (path) -> {
            return this.formatter.include(path.toString(), depth).trim();
        };
        String content = (String) encapsulatedDeclarations.stream().map((encapsulateDeclared) -> {
            if (encapsulateDeclared instanceof EncapsulateDeclaredMethod) {
                Method encapsulatedMethod = (Method) encapsulateDeclared.getEncapsulatedObject();
                Path receivedPath = (new DocPath(encapsulatedMethod)).received().path();
                Path methodApprovedPath;
                if (receivedPath.toFile().exists()) {
                    methodApprovedPath = (Path) this.onePathToPath.apply((new DocPath(encapsulatedMethod)).received());
                    return (String) includeWithOffset.apply(methodApprovedPath);
                } else {
                    methodApprovedPath = (Path) this.onePathToPath.apply((new DocPath(encapsulatedMethod)).approved());
                    return (String) includeWithOffset.apply(methodApprovedPath);
                }
            } else if (encapsulateDeclared instanceof EncapsulateDeclaredClass) {
                Class<?> encapsulatedClass = (Class) encapsulateDeclared.getEncapsulatedObject();
                return this.getClassDocumentation(encapsulatedClass, depth + 1);
            } else {
                return "";
            }
        }).collect(Collectors.joining("\n\n"));
//       return this.formatter.paragraphSuite(new String[]{this.getTitle(clazz, depth), this.getDescription(clazz), content});
        return this.formatter.paragraphSuite(new String[]{this.getTitle(clazz, depth), "", content});
    }

}
