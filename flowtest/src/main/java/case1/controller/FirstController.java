package case1.controller;

import case1.core.First;
import case1.db.FirstRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestMapping(path = "/first")
@RestController
public class FirstController {

    @Autowired
    private FirstRepository dao;

    @RequestMapping(method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    @ResponseBody
    @ApiOperation(value = "update first")
    public String updateFirst(@ApiParam(value = "First to store") @RequestBody First first) {
        dao.save(first);
        return "";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "retrieve all First for an uuid")
    public @ResponseBody First getFirst( @PathVariable("uuid") @NotNull String uuid) {
        First result = dao.findByUuid(uuid);
        if (result == null) {
            return null;
        }
        return result;
    }

}
