package uservices.domain.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Length;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O campo E-Mail é obrigatório!")
    @Column(length = 150, nullable = false)
    @Email(message = "Insira um e-mail válido")
    private String email;

    @NotBlank(message = "O campo Senha é obrigatório")
    @Column(length = 100, nullable = false)
    private String pwd;

    @NotBlank(message = "O campo Role é obrigatório")
    @Column(length = 45, nullable = false)
    private String role;


}
