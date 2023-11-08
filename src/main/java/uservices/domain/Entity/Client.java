package uservices.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer client_id;

    @NotBlank(message = "O campo E-Mail é obrigatório!")
    @Column(length = 150, nullable = false, unique = true)
    @Email(message = "Insira um e-mail válido")
    private String email;

    @NotBlank(message = "O campo Senha é obrigatório")
    @Column(length = 100, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;

    @NotBlank(message = "O campo Role é obrigatório")
    @Column(length = 45, nullable = false)
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy="client",fetch=FetchType.EAGER)
    private Set<Authority> authorities;


}
