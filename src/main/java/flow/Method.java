package flow;

public class Method {
    private final String className;
    private final String methodName;
    public Method(String className, String methodName){
        this.className = className;
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String toString () {
         return className + ": " + methodName;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Method) {
            Method other = (Method) obj;
            if (other.getClassName() != null && other.getMethodName() != null) {
                return other.toString().equals(toString());
            }
        }
        return false;
    }
}
