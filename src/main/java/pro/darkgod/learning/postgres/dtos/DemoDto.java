package pro.darkgod.learning.postgres.dtos;

import java.time.OffsetDateTime;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;
import javax.validation.constraints.Size;

public class DemoDto {

    @NotNull
    @Size(min = 5, max = 20)
    @Pattern(regexp = "^[a-z]+$", flags = Flag.CASE_INSENSITIVE)
    private String name;

    @NotNull
    @Temporal(TemporalType.DATE)
    private OffsetDateTime date;

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
