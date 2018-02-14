package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Answer extends DomainEntity {

    // Constructors -----------------------------------------------------------

    public Answer() {
        super();
    }

    // Attributes -------------------------------------------------------------

    private String answer;

    @SafeHtml
    @NotBlank
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // Relationships ----------------------------------------------------------


}
