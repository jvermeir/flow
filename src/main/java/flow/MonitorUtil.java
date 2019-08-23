package flow;

import lombok.Getter;
import org.aspectj.lang.JoinPoint;

import java.util.ArrayList;
import java.util.List;

public class MonitorUtil {
    @Getter
    private List<Measurement> measurements = new ArrayList<>();

    public void store(JoinPoint joinPoint, String phase, String type) {
        measurements.add(new Measurement(joinPoint, phase, type));
    }
}
