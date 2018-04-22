package flow;

import org.junit.jupiter.api.Test;
import spoon.reflect.declaration.CtType;
import spoon.support.reflect.declaration.CtClassImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPathFinder {

    @Test
    public void dummyPlaceholderTest() {
        assertEquals(1,1);
    }

    public void test3PathQueriesAreFoundInCase1() {
        PathFinder pathFinder = new PathFinder("./flowtest/src/main/java/case1");
        List<List<CtType<?>>> paths = pathFinder.getPaths();
        // TODO: find out if this might lead to useful results...
//        CtTypeMember ctTypeMember = pathFinder.listOfTypes.get(2).getTypeMembers().get(2);
//        CtMethod<?> method = ctTypeMember.getDeclaringType().getMethods().iterator().next();
//        CtStatement statement = method.getBody().getStatement(0);
//        List<CtElement> elements = statement.
        //.getBody().getStatements().get(0).getDefaultExpression().getExecutable().getSimpleName();
//        assertEquals(3, paths.size());
    }

    private List<CtType<?>> constructPath() {
        List<CtType<?>> path = new ArrayList<>();
        CtType x = new CtClassImpl();
        x.setSimpleName("FirstController");
        path.add(x);
        return path;
    }

    /*
    a method in a class uses a method on an instance of a class until a database method is reached.
    a database method is a CtMethodImpl in the typeMembers list of a jparepo class. ctmethodImpl's have a simpleName attribute.

    allTypes.get(2).getTypeMembers().get(2).getBody().getStatements().get(0).getDefaultExpression().getExecutable().getSimpleName() -> findByUuid
     */
}
