package co.inventorsoft.academy.demorest.entity;

import co.inventorsoft.academy.demorest.entity.enumeration.PlayerPosition;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @EqualsAndHashCode.Include
    @ToString.Include
    private String playerName;

    @EqualsAndHashCode.Include
    @ToString.Include
    private String nationality;
    private Long shirtNumber;
    private PlayerPosition playerPosition;

    @ManyToOne
    @JoinColumn(name = "teamCategory_id")
    private TeamCategory teamCategory;
}
