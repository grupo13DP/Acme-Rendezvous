package domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Collection;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

    // Constructors -----------------------------------------------------------

    public User() {
        super();
    }


    // Attributes -------------------------------------------------------------

    private Date birthday;

    @NotNull
    @Past
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    // Relationships ----------------------------------------------------------

    private Collection<Comment> comments;
    private Collection<Join> joins;
    private Collection<Rendezvous> rendezvouss;

    @Valid
    @OneToMany(mappedBy = "user")
    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    @Valid
    @OneToMany(mappedBy = "attendant")
    public Collection<Join> getJoins() {
        return joins;
    }

    public void setJoins(Collection<Join> joins) {
        this.joins = joins;
    }

    @Valid
    @OneToMany(mappedBy = "creator")
    public Collection<Rendezvous> getRendezvouss() {
        return rendezvouss;
    }

    public void setRendezvouss(Collection<Rendezvous> rendezvouss) {
        this.rendezvouss = rendezvouss;
    }
}
