package flow;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PathFinder {
    public final Collection<CtType<?>> allTypes;
    public final List<CtType<?>> listOfTypes;

    public static final Collection<CtType<?>> getAllTypes(String sourcePath) {
        Launcher launcher = new Launcher();
        launcher.addInputResource(sourcePath);
        launcher.getEnvironment().setAutoImports(true);
        launcher.getEnvironment().setNoClasspath(true);
        launcher.buildModel();
        CtModel model = launcher.getModel();
        return model.getAllTypes();
    }

    public PathFinder(String sourcePath) {
        allTypes = getAllTypes(sourcePath);
        listOfTypes = new ArrayList<>(allTypes);
    }

    public List<List<CtType<?>>> getPaths() {
        List<CtType<?>> jpaMethods = new JPAFinder(allTypes).getJPAMethods();
        return jpaMethods.stream().map(method -> toResult(method)).collect(Collectors.toList());
    }

    private List<CtType<?>> toResult(CtType<?> x) {
        ArrayList<CtType<?>> result = new ArrayList<>();
        result.add(x);
        return result;
    }
}
