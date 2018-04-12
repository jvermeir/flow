package case1.controller;


@org.springframework.web.bind.annotation.RequestMapping(path = "/third")
@org.springframework.web.bind.annotation.RestController
public class ThirdController {
    @org.springframework.beans.factory.annotation.Autowired
    private case1.db.ThirdRepository thirdRepository;

    @org.springframework.web.bind.annotation.RequestMapping(method = org.springframework.web.bind.annotation.RequestMethod.GET, path = "/{id}")
    @javax.ws.rs.Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    @io.swagger.annotations.ApiOperation("retrieve all third for an Id")
    public java.util.List<case1.core.Third> getThirdById(@io.swagger.annotations.ApiParam("to retrieve third of ")
    @org.springframework.web.bind.annotation.PathVariable("id")
    @javax.validation.constraints.NotNull
    java.lang.String Id) {
        return thirdRepository.findAllById(Id);
    }
}

