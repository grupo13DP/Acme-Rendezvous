package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {


    // Constructors -----------------------------------------------------------

    public Comment() {
        super();
    }


    // Attributes -------------------------------------------------------------

    private Date moment;
    private String text;
    private String picture;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    @NotBlank
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @URL
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }



    // Relationships ----------------------------------------------------------

    private Rendezvous rendezvous;
    private User user;
    private Collection<Comment> replies;
    private Comment comment;



    @Valid
    @NotNull
    @ManyToOne(optional = false)
    public Rendezvous getRendezvous() {
        return rendezvous;
    }

    public void setRendezvous(Rendezvous rendezvous) {
        this.rendezvous = rendezvous;
    }

    @Valid
    @NotNull
    @ManyToOne(optional = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "comment")
    public Collection<Comment> getReplies() {
        return replies;
    }

    public void setReplies(Collection<Comment> replies) {
        this.replies = replies;
    }

    @Valid
    @ManyToOne(optional  =false)
    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }



}
