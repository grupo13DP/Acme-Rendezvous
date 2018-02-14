package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Collection;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Announcement extends DomainEntity {

    // Constructors -----------------------------------------------------------

    public Announcement() {
        super();
    }


    // Attributes -------------------------------------------------------------

    private String title;
    private String description;
    private Date moment;

    @NotBlank
    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    @NotNull
    @Past
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getMoment() {

        return moment;
    }

    public void setMoment(Date moment) {

        this.moment = moment;
    }

    // Relationships ----------------------------------------------------------

    private RendezVous rendezVous;

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    public RendezVous getRendezVous(){

        return rendezVous;
    }

    public void setRendezVous(RendezVous rendezVous) {

        this.rendezVous = rendezVous;
    }

}
