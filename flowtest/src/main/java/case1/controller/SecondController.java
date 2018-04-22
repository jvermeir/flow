package case1.controller;

import case1.core.Second;
import case1.db.SecondRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestMapping(path = "/second")
@RestController
public class SecondController {

    @Autowired
    private SecondRepository dao;

    @RequestMapping(method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    @ResponseBody
    @ApiOperation(value = "update second")
    public String updateFirst(@ApiParam(value = "Second to store") @RequestBody Second second) {
        dao.save(second);
        return "";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "retrieve all Second for an uuid")
    public @ResponseBody Second getSecond(@PathVariable("uuid") @NotNull String uuid) {
        Second result = dao.findByUuid(uuid);
        if (result == null) {
            return null;
        }
        return result;
    }

}
