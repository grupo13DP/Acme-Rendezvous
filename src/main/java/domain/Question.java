package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Access(AccessType.PROPERTY)
public class Question extends DomainEntity {


    // Constructors -----------------------------------------------------------

    public Question() {
        super();
    }


    // Attributes -------------------------------------------------------------
    private String text;

    @SafeHtml
    @NotBlank
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
// Relationships ----------------------------------------------------------

    private Collection<Answer> answers;
    private RendezVous rendezVous;

    @NotNull
    @Valid
    @OneToMany(cascade = CascadeType.ALL)
    public Collection<Answer> getAnswers() {
        return answers;
    }

    @Valid
    @ManyToOne(optional = false)
    public RendezVous getRendezVous() {
        return rendezVous;
    }

    public void setAnswers(Collection<Answer> answers) {
        this.answers = answers;
    }

    public void setRendezVous(RendezVous rendezVous) {
        this.rendezVous = rendezVous;
    }
}
