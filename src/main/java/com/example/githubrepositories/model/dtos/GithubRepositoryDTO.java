package com.example.githubrepositories.model.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubRepositoryDTO(
        @JsonProperty("name")
        String name,
        @JsonProperty("owner")
        GithubOwnerDTO owner,
        @JsonProperty("fork")
        boolean fork,
        List<GithubBranchDTO> branches) {
}
