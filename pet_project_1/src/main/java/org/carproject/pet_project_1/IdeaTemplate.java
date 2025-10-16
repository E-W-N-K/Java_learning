package org.carproject.pet_project_1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class IdeaTemplate {
    @JsonProperty("идея")
    private String idea;
    @JsonProperty("описание")
    private String description;
    @JsonProperty("дата отправки")
    private String currentDate;

    IdeaTemplate(String idea, String description, String currentDate) {
        this.idea = idea;
        this.description = description;
        this.currentDate = currentDate;
    }

}
