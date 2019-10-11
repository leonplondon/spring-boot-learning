package pro.darkgod.learning.postgres.controllers;

import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.darkgod.learning.postgres.dtos.DemoDto;
import pro.darkgod.learning.postgres.models.DemoEntity;
import pro.darkgod.learning.postgres.repositories.DemoSqlRepository;

@RestController
@RequestMapping(
    value = "/api/sql",
    produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
    consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
)
@Validated
public class DemoSqlController {

    @Autowired
    private DemoSqlRepository demoSqlRepository;

    @PostMapping
    public DemoEntity create(@Valid @RequestBody DemoDto demoDto) {
        DemoEntity demoEntity = DemoEntity.fromDto(demoDto);
        return demoSqlRepository.save(demoEntity);
    }

    @GetMapping
    public List<DemoEntity> findAll() {
        return demoSqlRepository.findAll();
    }

    @GetMapping("/{id}")
    public DemoEntity findOne(@PathVariable("id") Long id) {
        return demoSqlRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(
        @PathVariable("id") @NotBlank(message = "Please send a valid entity id") Long id,
        @Valid @RequestBody DemoDto demoDto
    ) {
        DemoEntity demoEntity = demoSqlRepository.findById(id).orElse(null);

        if (demoEntity == null) {
            return ResponseEntity.notFound().build();
        }

        demoDto.setName(demoDto.getName());
        demoDto.setDate(demoDto.getDate());

        DemoEntity saved = demoSqlRepository.save(demoEntity);

        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public boolean delete(
        @PathVariable("id") @NotBlank(message = "Please send a valid entity id") Long id) {
        try {
            demoSqlRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> count(
        @RequestParam("date") @NotBlank(message = "Come on!!!") String offsetDateTime) {
        Integer countEntitiesAfterDate = demoSqlRepository
            .countEntitiesAfterDate(OffsetDateTime.parse(offsetDateTime));
        return ResponseEntity.ok(countEntitiesAfterDate);
    }
}