package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
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
public class Rendezvous extends DomainEntity{

    // Constructors -----------------------------------------------------------

    public Rendezvous() {
        super();
    }


    // Attributes -------------------------------------------------------------
    private String name;
    private String description;
    private Date moment;
    private String picture;
    private GPSCoordinates	location;
    private  Boolean finalMode;
    private Boolean deleted;
    private  Boolean forAdults;

    @SafeHtml
    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Valid
    @URL
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Valid
    public GPSCoordinates getLocation() {
        return location;
    }

    public void setLocation(GPSCoordinates location) {
        this.location = location;
    }

    public Boolean getFinalMode() {
        return finalMode;
    }

    public void setFinalMode(Boolean finalMode) {
        this.finalMode = finalMode;
    }


    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }


    public Boolean getForAdults() {
        return forAdults;
    }

    public void setForAdults(Boolean forAdults) {
        this.forAdults = forAdults;
    }

    // Relationships ----------------------------------------------------------
    private User creator;
    private Collection<Rendezvous> associated;
    private Collection<Announcement> announcements;
    private Collection<Join> joined;
    private Collection<Comment> comments;
    private Collection<Question> questions;
    private Rendezvous rendezvous;

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
    @Valid
    @NotNull
    @OneToMany(mappedBy = "rendezvous")
    public Collection<Rendezvous> getAssociated() {
        return associated;
    }

    public void setAssociated(Collection<Rendezvous> associated) {
        this.associated = associated;
    }

    @Valid
    @NotNull
    @OneToMany(mappedBy = "rendezvous")
    public Collection<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(Collection<Announcement> announcements) {
        this.announcements = announcements;
    }


    @Valid
    @NotNull
    @OneToMany(mappedBy = "rendezvous")
    public Collection<Join> getJoined() {
        return joined;
    }

    public void setJoined(Collection<Join> joined) {
        this.joined = joined;
    }

    @Valid
    @NotNull
    @OneToMany(mappedBy = "rendezvous")
    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    @Valid
    @NotNull
    @OneToMany(mappedBy = "rendezvous")
    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }

    @Valid
    @ManyToOne(optional = false)
    public Rendezvous getRendezvous() {
        return rendezvous;
    }

    public void setRendezvous(Rendezvous rendezvous) {
        this.rendezvous = rendezvous;
    }
}