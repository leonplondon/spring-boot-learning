package pro.darkgod.learning.mongo.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.lang.NonNull;

public class DemoDto {

    @NotNull
    @Size(min = 4, max = 10)
    private String username;

    @NotNull
    @Size(min = 3)
    private String name;

    @NonNull
    @Size(min = 3)
    private String lastName;

    @NotNull
    @Size(min = 6, max = 10)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
