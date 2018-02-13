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
public class RendezVous extends DomainEntity{

    // Constructors -----------------------------------------------------------

    public RendezVous() {
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

    @Valid
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Valid
    public Boolean getForAdults() {
        return forAdults;
    }

    public void setForAdults(Boolean forAdults) {
        this.forAdults = forAdults;
    }

    // Relationships ----------------------------------------------------------
    private User creator;
    private Collection<RendezVous> associated;
    private Collection<Announcement> announcements;
    private Collection<Join> joined;
    private Collection<Comment> comments;
    private Collection<Question> questions;

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
    @OneToMany(mappedBy = "rendezVous")
    public Collection<RendezVous> getAssociated() {
        return associated;
    }

    public void setAssociated(Collection<RendezVous> associated) {
        this.associated = associated;
    }

    @Valid
    @NotNull
    @OneToMany(mappedBy = "rendezVous")
    public Collection<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(Collection<Announcement> announcements) {
        this.announcements = announcements;
    }


    @Valid
    @NotNull
    @OneToMany(mappedBy = "rendezVous")
    public Collection<Join> getJoined() {
        return joined;
    }

    public void setJoined(Collection<Join> joined) {
        this.joined = joined;
    }

    @Valid
    @NotNull
    @OneToMany(mappedBy = "rendezVous")
    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    @Valid
    @NotNull
    @OneToMany(mappedBy = "rendezVous")
    public Collection<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Collection<Question> questions) {
        this.questions = questions;
    }
}
