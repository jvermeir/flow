import flow.MonitorUtil;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;

public class X {
    static MonitorUtil monitorUtil = new MonitorUtil();
    public static void main(String args[]) {
        Instant start = Instant.now();
        monitorUtil.store(null, "test", "type");
        int j=0;
        for (int i =0;i<1;i++) { j=j+i; }
        Instant end = Instant.now();
        Duration between = Duration.between(start, end);
        DecimalFormat df2 = new DecimalFormat( "#,###,###,###" );
        System.out.println(between + ":" + df2.format(between.getNano()));
        monitorUtil.store(null, "test", "type-end");
        monitorUtil.getMeasurements().forEach(measurement -> System.out.println(measurement.toString()));
    }

}
