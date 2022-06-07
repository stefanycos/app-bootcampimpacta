package br.com.impacta.moedinhas.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = Category.COLLECTION_NAME)
public class Category {

    public static final String COLLECTION_NAME = "categories";

    @Id
    private String id;

    private String name;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public String getName() {
        return this.name.trim();
    }

    public Boolean getStatus() {
        return status == null ? true : status;
    }
}
