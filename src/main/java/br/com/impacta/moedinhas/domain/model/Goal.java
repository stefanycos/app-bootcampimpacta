package br.com.impacta.moedinhas.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "goals")
public class Goal implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private Boolean reached;

    private String description;

    private Double cost;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
