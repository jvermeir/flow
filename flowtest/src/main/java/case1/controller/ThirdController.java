package case1.controller;

import case1.core.Second;
import case1.core.SomeLogic;
import case1.db.SecondRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@RequestMapping(path = "/third")
@RestController
public class ThirdController {

    @Autowired
    SecondRepository repository;

    @RequestMapping(method = RequestMethod.POST, path = "nonDb")
    @Consumes(MediaType.APPLICATION_JSON)
    @ResponseBody
    @ApiOperation(value = "Non DB stuff")
    public String nonDb() {
        SomeLogic someLogic = new SomeLogic(repository);
        return someLogic.doNoneDbStuff() + "";
    }

    @RequestMapping(method = RequestMethod.POST, path = "db")
    @Consumes(MediaType.APPLICATION_JSON)
    @ResponseBody
    @ApiOperation(value = "DB stuff")
    public long db() {
        SomeLogic someLogic = new SomeLogic(repository);
        return someLogic.doDbStuff();
    }

}
