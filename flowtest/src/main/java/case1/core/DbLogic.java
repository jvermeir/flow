package case1.core;

import case1.core.Second;
import case1.db.SecondRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DbLogic {

    public void f() {
    }

    public long doDbStuff(SecondRepository repository) {
        Long i = Math.round(Math.random() * 10000);
        Second second = new Second("uuid" + i, i.intValue());
        repository.save(second);
        repository.findByUuid("uuid" + i);
        return repository.count();
    }
}
