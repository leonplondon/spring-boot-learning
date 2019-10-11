package pro.darkgod.learning.mongo.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.darkgod.learning.mongo.dtos.DemoDto;
import pro.darkgod.learning.mongo.models.DemoDocument;
import pro.darkgod.learning.mongo.repositories.DemoNoSqlRepository;

@RestController
@RequestMapping(
    value = "/api/nosql",
    produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
    consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
)
public class DemoNoSqlController {

    @Autowired
    private DemoNoSqlRepository demoNoSqlRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public DemoDocument create(@Valid @RequestBody DemoDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        DemoDocument demoDocument = DemoDocument.fromDto(user);
        return demoNoSqlRepository.insert(demoDocument);
    }

    @GetMapping
    public List<DemoDocument> findAll() {
        return demoNoSqlRepository.findAll();
    }

    @GetMapping("/username")
    public Page<DemoDocument> findByUsernameFragment(
        @RequestParam("q") String username,
        Pageable pageable
    ) {
        return demoNoSqlRepository
            .findByUsernameFragment(username, pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(
        @PathVariable("id") String id,
        @Valid @RequestBody DemoDto demoDto
    ) {
        DemoDocument demoDocument = demoNoSqlRepository
            .findById(id)
            .orElse(null);

        if (demoDocument == null) {
            return ResponseEntity
                .notFound()
                .build();
        }

        demoDocument.setName(demoDto.getName());

        DemoDocument saved = demoNoSqlRepository.save(demoDocument);

        return ResponseEntity.ok(saved);
    }
}
