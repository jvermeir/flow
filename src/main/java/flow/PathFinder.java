package flow;

import spoon.reflect.declaration.CtType;

import java.util.List;
import java.util.stream.Collectors;

public class PathFinder {
    private final String sourcePath;

    public PathFinder(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public List<CtType<?>> getPaths() {
        return new JPAFinder(sourcePath).getJPAMethods();
    }

    private Path findPathFromQuery(Method method) {
        return new Path(method);
    }
}
