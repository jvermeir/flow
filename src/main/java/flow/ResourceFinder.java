package flow;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourceFinder {
    private final String sourcePath;
    private final String[] fileExtensions = {"java"};

    public ResourceFinder(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public List<String> getResources() {
        Collection<File> javaFiles = FileUtils.listFiles(new File(sourcePath), fileExtensions, true);
        Stream<List<String>> stream =
                javaFiles.stream()
                .map(file -> getResourceMethods(file))
                .filter(list -> list.size() > 0);
        List<String> resourceMethods = stream.flatMap(List::stream).collect(Collectors.toList());
        return resourceMethods;
    }

    private List<String> getResourceMethods(File file) {
        List<String> result = new ArrayList<>();
        try {
            List<String> lines = FileUtils.readLines(file);
            List<String> annotationsAndDeclarations =
                    lines.stream()
                    .filter(line -> line.indexOf("public") + line.indexOf("@RequestMapping") > 0)
                    .collect(Collectors.toList());
            boolean seenAnnotation = false;
            for (String annoOrDecl: annotationsAndDeclarations) {
                if (annoOrDecl.indexOf("@RequestMapping")>0) {
                    seenAnnotation = true;
                } else if (annoOrDecl.indexOf("public") > 0) {
                    if (seenAnnotation) {
                        result.add(annoOrDecl);
                        seenAnnotation = false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
