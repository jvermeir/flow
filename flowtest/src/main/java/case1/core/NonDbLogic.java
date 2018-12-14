package case1.core;

public class NonDbLogic {
    public long doNonDbStuff() {
        long square = 0;
        for (int i = 0; i < Math.round(Math.random() * 10000); i++) {
            square = i * i;
        }
        return square;
    }
}
