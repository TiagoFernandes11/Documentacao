package uservices.domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @NotBlank(message = "O campo alarm_name é obrigatório!")
    @Column(nullable = false)
    private String alarm_name;

    @NotBlank(message = "O campo criticality é obrigatório!")
    @Column(nullable = false)
    private String criticality;

    @NotBlank(message = "O campo description é obrigatório!")
    @Column(nullable = false)
    private String description;

    @NotBlank(message = "O campo responsible é obrigatório!")
    @Column(nullable = false)
    private String responsible;

    @NotBlank(message = "O campo actuation é obrigatório!")
    @Column(nullable = false)
    private String actuation;

    @NotBlank(message = "O campo escalation é obrigatório!")
    @Column(nullable = false)
    private String escalation;

    @NotBlank(message = "O campo squad é obrigatório!")
    @Column(nullable = false)
    private String squad;


}
