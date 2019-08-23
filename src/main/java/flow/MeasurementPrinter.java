package flow;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;

@AllArgsConstructor
public class MeasurementPrinter {
    private String outputFileName;
    private MonitorUtil monitorUtil;

    public void print() {
        File output = new File(outputFileName);
        monitorUtil.getMeasurements().forEach(measurement -> writeLine(output, measurement));
    }

    @SneakyThrows
    private void writeLine(File output, Measurement measurement) {
        FileUtils.write(output, measurement.toString(), Charset.defaultCharset());
    }
}
