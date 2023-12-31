package com.example.githubrepositories.model.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubCommitDTO(
        @JsonProperty("sha")
        String sha) {
}
