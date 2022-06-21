package br.com.impacta.moedinhas.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "categories")
public class Category implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

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
