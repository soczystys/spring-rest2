package com.crud.tasks.domain.createdtrellocardnew.badges.nestedclassesforbadges.nestedclassesforattachmentsbytype;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloDto {
    @JsonProperty("board")
    private int board;

    @JsonProperty("card")
    private int card;
}
