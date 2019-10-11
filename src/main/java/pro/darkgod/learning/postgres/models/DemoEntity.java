package pro.darkgod.learning.postgres.models;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import pro.darkgod.learning.postgres.dtos.DemoDto;

@Entity
@Table(
    name = "demo_entities",
    indexes = {
        @Index(unique = true, columnList = "name"),
        @Index(columnList = "date")
    }
)
public class DemoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private OffsetDateTime date;

    public static DemoEntity fromDto(DemoDto demoDto) {
        DemoEntity result = new DemoEntity();

        result.setDate(demoDto.getDate());
        result.setName(demoDto.getName());

        return result;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }
}
