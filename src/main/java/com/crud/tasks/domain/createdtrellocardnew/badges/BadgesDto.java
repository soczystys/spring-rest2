package com.crud.tasks.domain.createdtrellocardnew.badges;

import com.crud.tasks.domain.createdtrellocardnew.badges.nestedclassesforbadges.AttachmentsByTypeDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BadgesDto {
    @JsonProperty("votes")
    private int votes;

    @JsonProperty("attachmentsByType")
    private AttachmentsByTypeDto attachmentsByTypeDto;
}
