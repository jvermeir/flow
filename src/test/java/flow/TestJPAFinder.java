package flow;

import org.junit.jupiter.api.Test;
import spoon.reflect.declaration.CtType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJPAFinder {
    @Test
    public void testAllJPAInterfacesAreFound() {
        JPAFinder jpaFinder = new JPAFinder(PathFinder.getAllTypes("./flowtest/src/main/java/case1"));
        List<CtType<?>> jpaMethods = jpaFinder.getJPAMethods();
        assertEquals(2, jpaMethods.size());
    }
}
