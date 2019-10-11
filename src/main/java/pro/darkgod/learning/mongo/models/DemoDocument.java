package pro.darkgod.learning.mongo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pro.darkgod.learning.mongo.dtos.DemoDto;

@Document(collection = "users")
public class DemoDocument {

    @Id
    private String id;

    private String username;
    private String name;
    private String lastName;
    private String password;

    public static DemoDocument fromDto(DemoDto demoDto) {
        DemoDocument demoDocument = new DemoDocument();
        demoDocument.setUsername(demoDto.getUsername());
        demoDocument.setName(demoDto.getName());
        demoDocument.setLastName(demoDto.getLastName());
        demoDocument.setPassword(demoDto.getPassword());

        return demoDocument;
    }

    public String getId() {
        return id;
    }

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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

