package flow;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestResourceFinder {
    @Test
    public void test5ResourcesAreFoundInCase1() {
        ResourceFinder resourceFinder = new ResourceFinder("./src/test/resources/case1");
        List<String> resources = resourceFinder.getResources();
        assertEquals(3, resources.size());
    }
}
