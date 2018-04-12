package flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Path {
    private List<Method> path = new ArrayList<>();
    public Path(Method start) {
        path.add(start);
    }

    public List<Method> getPath() {
        return path;
    }

    public void append(Method next) {
        path.add(next);
    }

    @Override
    public String toString() {
        return path.stream()
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining("-", "{", "}"));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj !=null && obj instanceof Path) {
            Path other = (Path) obj;
            return other.toString().equals(this.toString());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return path.hashCode();
    }
}
