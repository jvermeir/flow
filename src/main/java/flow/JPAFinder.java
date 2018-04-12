package flow;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtType;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class JPAFinder {
    private final Collection<CtType<?>> allTypes;

    public JPAFinder(String sourcePath) {
        Launcher launcher = new Launcher();
        launcher.addInputResource(sourcePath);
        launcher.getEnvironment().setAutoImports(true);
        launcher.getEnvironment().setNoClasspath(true);
        launcher.buildModel();
        CtModel model = launcher.getModel();
        allTypes = model.getAllTypes();
    }

    public List<CtType<?>> getJPAMethods() {
        return allTypes.stream().filter(type -> implementsJpa(type)).collect(Collectors.toList());
    }

    private boolean implementsJpa(CtType<?> type) {
        return type.getSuperInterfaces().stream().filter(i -> i.getSimpleName().equals("JpaRepository")).count()>0;
    }

}
