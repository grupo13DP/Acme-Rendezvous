package domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Join extends DomainEntity {

    // Constructors -----------------------------------------------------------

    public Join() {
        super();
    }


    // Attributes -------------------------------------------------------------
    private Date moment;

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

    private User user;
    private Rendezvous rendezvous;

    @Valid
    @ManyToOne(optional = false)
    public User getUser() {
        return user;
    }

    @Valid
    @ManyToOne(optional = false)
    public Rendezvous getRendezvous() {
        return rendezvous;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRendezvous(Rendezvous rendezvous) {
        this.rendezvous = rendezvous;
    }
}
