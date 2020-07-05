package ten.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ten.app.entity.Artifact;
import ten.app.entity.Tag;
import ten.app.service.MavenParseService1;
import ten.app.service.MavenParseService2;
import ten.app.service.MavenParseService3;

import java.util.Collection;

@RestController
@RequestMapping("/maven")
public class MavenController {

    private final MavenParseService1 service;

    private final MavenParseService2 service2;

    private final MavenParseService3 service3;


    public MavenController(MavenParseService1 service, MavenParseService2 service2, MavenParseService3 service3) {
        this.service = service;
        this.service2 = service2;
        this.service3 = service3;
    }

    @GetMapping("artifacts")
    public Collection<Artifact> handleArtifacts(){
        return service.artifacts();
    }

    @GetMapping("tags")
    public Collection<Tag> handleags(){
        return service3.tags();
    }
}
