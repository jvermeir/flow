package asm;

import org.apache.commons.io.FileUtils;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.Method;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    private String targetClass;
    private Method targetMethod;

    private AppClassVisitor cv;

    private ArrayList<Callee> callees = new ArrayList<Callee>();

    private static class Callee {
        String className;
        String methodName;
        String methodDesc;
        String source;
        int line;

        public Callee(String cName, String mName, String mDesc, String src, int ln) {
            className = cName; methodName = mName; methodDesc = mDesc; source = src; line = ln;
        }
    }

    private class AppMethodVisitor extends MethodVisitor {

        boolean callsTarget;
        int line;

        public AppMethodVisitor() { super(Opcodes.ASM6); }

        public void visitMethodInsn(
                final int opcode,
                final String owner,
                final String name,
                final String descriptor,
                final boolean isInterface) {
            System.out.println("owner: " + owner);
            if (owner.equals(targetClass)) {
                if (name.equals(targetMethod.getName())) {
//                    if (descriptor.equals(targetMethod.getDescriptor())) {
                        callsTarget = true;
//                    }
                }
            }
        }

        public void visitCode() {
            callsTarget = false;
        }

        public void visitLineNumber(int line, Label start) {
            this.line = line;
        }

        public void visitEnd() {
            if (callsTarget)
                callees.add(new Callee(cv.className, cv.methodName, cv.methodDesc,
                        cv.source, line));
        }
    }

    private class AppClassVisitor extends ClassVisitor {

        private AppMethodVisitor mv = new AppMethodVisitor();

        public String source;
        public String className;
        public String methodName;
        public String methodDesc;

        public AppClassVisitor() { super(Opcodes.ASM6); }

        public void visit(int version, int access, String name,
                          String signature, String superName, String[] interfaces) {
            className = name;
        }

        public void visitSource(String source, String debug) {
            this.source = source;
        }

        public AppMethodVisitor visitMethod(int access, String name,
                                         String desc, String signature,
                                         String[] exceptions) {
            methodName = name;
            methodDesc = desc;

            return mv;
        }
    }

    public void findCallingMethodsInfolder(String path, String targetClass, String targetMethodDeclaration) throws Exception {
        this.targetClass = targetClass;
        this.targetMethod = Method.getMethod(targetMethodDeclaration);
        this.cv = new AppClassVisitor();

        Collection<File> javaFiles = FileUtils.listFiles(new File(path), new String[]{"class"}, true);
        javaFiles.stream().map(file -> testFile(file)).collect(Collectors.toList());
    }

    public boolean testFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ClassReader reader = new ClassReader(fis);
            reader.accept(cv, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    public static void findPaths(List<List<CalleeData>> paths) throws Exception {
        List<List<CalleeData>> newPaths = new ArrayList<>();
        for (List<CalleeData> path: paths) {
            App app = new App();
            CalleeData calleeData = path.get(0);
            app.findCallingMethodsInfolder("target/test-classes/", calleeData.targetClass, calleeData.targetMethod);
            for (Callee c : app.callees) {
                ArrayList<CalleeData> newPath = new ArrayList<>(path);
                newPath.add(new CalleeData(c.className, c.methodName));
                newPaths.add(newPath);
            }
        }
        findPaths(newPaths);
    }

    public static void main( String[] args ) {
        try {
            App app = new App();
//            app.findCallingMethodsInfolder("target/test-classes/", "case1/case1.db/FirstRepository", "First findByUuid(@Param(\"uuid\") String uuid)");
            app.findCallingMethodsInfolder("target/test-classes/", "case1/Callee", "String getIt()");
//            app.findCallingMethodsInJar(args[0], args[1], args[2]);

            for (Callee c : app.callees) {
                System.out.println(c.source+":"+c.line+" "+c.className+" "+c.methodName+" "+c.methodDesc);
            }

            System.out.println("--\n"+app.callees.size()+" methods invoke "+
                    app.targetClass+" "+
                    app.targetMethod.getName()+" "+app.targetMethod.getDescriptor());
        } catch(Exception x) {
            x.printStackTrace();
        }
    }
}

class CalleeData {
    public final String targetClass;
    public final String targetMethod;
    CalleeData(String targetClass, String targetMethod) {
        this.targetClass = targetClass;
        this.targetMethod = targetMethod;
    }
}