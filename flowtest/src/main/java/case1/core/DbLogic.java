package case1.core;

import case1.db.SecondRepository;

public class DbLogic {

    public long doDbStuff(SecondRepository repository) {
        for (int i = 0; i< Math.round(Math.random()*10000); i++) {
            repository.save(new Second());
            repository.findByUuid("uuid"+ i);
        }
        return repository.count();
    }
}
