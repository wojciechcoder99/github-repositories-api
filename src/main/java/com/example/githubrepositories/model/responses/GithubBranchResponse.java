package com.example.githubrepositories.model.responses;

import com.example.githubrepositories.model.dtos.GithubBranchDTO;
import com.example.githubrepositories.model.dtos.GithubCommitDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public class GithubBranchResponse {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Last commit sha")
    private String lastCommitSha;

    public GithubBranchResponse(GithubBranchDTO githubBranchDTO) {
        this.name = githubBranchDTO.name();
        this.lastCommitSha = Optional.of(githubBranchDTO)
                .map((GithubBranchDTO::commit))
                .map(GithubCommitDTO::sha)
                .orElse(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastCommitSha() {
        return lastCommitSha;
    }

    public void setLastCommitSha(String lastCommitSha) {
        this.lastCommitSha = lastCommitSha;
    }
}
