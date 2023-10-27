package uservices.domain.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String alarm_name;
    private String criticality;
    private String description;
    private String responsible;
    private String actuation;
    private String escalation;
    private String squad;

    public Alarm(Integer id, String alarm_name, String criticality, String description, String responsible, String actuation, String escalation, String squad) {
        this.id = id;
        this.alarm_name = alarm_name;
        this.criticality = criticality;
        this.description = description;
        this.responsible = responsible;
        this.actuation = actuation;
        this.escalation = escalation;
        this.squad = squad;
    }

    public Alarm() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlarm_name() {
        return alarm_name;
    }

    public void setAlarm_name(String alarm_name) {
        this.alarm_name = alarm_name;
    }

    public String getCriticality() {
        return criticality;
    }

    public void setCriticality(String criticality) {
        this.criticality = criticality;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getActuation() {
        return actuation;
    }

    public void setActuation(String actuation) {
        this.actuation = actuation;
    }

    public String getEscalation() {
        return escalation;
    }

    public void setEscalation(String escalation) {
        this.escalation = escalation;
    }

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
    }
}
