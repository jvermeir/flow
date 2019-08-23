package case1.core;

import case1.db.SecondRepository;
import org.springframework.context.annotation.Bean;

public class SomeLogic {
    SecondRepository repository;
    public SomeLogic(SecondRepository repository) {
        this.repository = repository;
    }

    public long doNoneDbStuff() {
        return new NonDbLogic().doNonDbStuff();
    }

    public long doDbStuff() {
        return new DbLogic().doDbStuff(repository);
    }
}
