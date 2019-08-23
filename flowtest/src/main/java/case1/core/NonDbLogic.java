package case1.core;

public class NonDbLogic {
    public long doNonDbStuff() {
        long square = 0;
        for (int j = 0; j < Math.round(Math.random() * 1000000); j++) {
            for (int i = 0; i < Math.round(Math.random() * 1000000); i++) {
                square = i * i;
            }
        }
        return square;
    }
}
