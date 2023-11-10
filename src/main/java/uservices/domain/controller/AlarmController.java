package uservices.domain.controller;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import uservices.domain.Entity.Alarm;
import uservices.domain.repository.AlarmRepository;

import java.util.List;

@RestController
@RequestMapping("/api/alarms")
public class AlarmController {
    private AlarmRepository repository;

    public AlarmController(AlarmRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Alarm> find(Alarm filtro){
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING );
        Example<Alarm> example = Example.of(filtro, matcher);
        return repository.findAll(example);
    }

    @GetMapping("/{id}")
    public Alarm findAlarmByID(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Alarme não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Alarm save(@RequestBody Alarm alarm){
        return repository.save(alarm);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ) {
        repository.findById(id).map(alarm -> {
            repository.delete(alarm);
            return alarm;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Alarme não encontrado"));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Alarm alarm){
        repository.findById(alarm.getId()).map(existingClient -> {
            alarm.setId(existingClient.getId());
            repository.save(alarm);
            return existingClient;}).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"Alarme não encontrado") );
    }

}
