package uservices.domain.controller;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uservices.domain.Entity.Client;
import uservices.domain.repository.ClientRepository;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private ClientRepository repository;
    private PasswordEncoder encoder;

    public ClientController(ClientRepository repository) {
        this.repository = repository;
        this.encoder = new BCryptPasswordEncoder();
    }

    @GetMapping
    public List<Client> find(Client filtro){
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING );
        Example<Client> example = Example.of(filtro, matcher);
        return repository.findAll(example);
    }

    @GetMapping("/{id}")
    public Client findClientByid(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> registerUser(@RequestBody Client client){
        Client savedCustomer = null;
        ResponseEntity response = null;
        try{
            String hashedPassword = encoder.encode(client.getPwd());
            client.setPwd(hashedPassword);
            savedCustomer = repository.save(client);
            if(savedCustomer.getClient_id() > 0){
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("Given user details are sucessfully registered");
            }
        } catch (Exception ex){
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception ocurred due to " + ex.getMessage());
        }
        return response;
    }

    //post com criptografia
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Client save(@RequestBody Client user) {
//        String encoder = this.encoder.encode(user.getSenha());
//        user.setSenha(encoder);
//        return repository.save(user);
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository.findById(id).map(user -> {
            repository.delete(user);
            return user;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Client user) {
        repository.findById(user.getClient_id()).map(existingClient -> {
            user.setClient_id(existingClient.getClient_id());
            repository.save(user);
            return existingClient;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }

    //update com criptografia
//    @PutMapping()
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void update(@RequestBody Client user) {
//        String encoder = this.encoder.encode(user.getSenha());
//        user.setSenha(encoder);
//        repository.findById(user.getId()).map(existingClient -> {
//            user.setId(existingClient.getId());
//            repository.save(user);
//            return existingClient;
//        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
//    }

    //tentativa de metodo de validação com senha criptografada
    @PostMapping("/login")
    public ResponseEntity<Client> validarSenha(@RequestBody Client client){
        String senha = repository.findClientByEmail(client.getEmail()).getPwd();
        Boolean valid = encoder.matches(client.getPwd(), senha);
        if(!valid){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(200).build();
    }
}
