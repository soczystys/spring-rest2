package com.crud.tasks.domain.createdtrellocardnew.badges.nestedclassesforbadges;

import com.crud.tasks.domain.createdtrellocardnew.badges.nestedclassesforbadges.nestedclassesforattachmentsbytype.TrelloDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentsByTypeDto {
    @JsonProperty("trello")
    private TrelloDto trelloDto;
}
