package flow;


import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.aspectj.lang.JoinPoint;

@RequiredArgsConstructor
@ToString
public class Measurement {
    private final JoinPoint joinPoint;
    private final String phase;
    private final String type;
}
