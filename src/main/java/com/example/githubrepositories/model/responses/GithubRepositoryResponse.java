package com.example.githubrepositories.model.responses;

import com.example.githubrepositories.model.dtos.GithubRepositoryDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class GithubRepositoryResponse {
    @JsonProperty("Repository Name")
    private final String repositoryName;
    @JsonProperty("Owner Login")
    private final String ownerLogin;
    @JsonProperty("Branches")
    private final List<GithubBranchResponse> branches;
    public GithubRepositoryResponse(GithubRepositoryDTO githubRepositoryDTO) {
        repositoryName = githubRepositoryDTO.name();
        ownerLogin = githubRepositoryDTO.owner().login();
        branches = githubRepositoryDTO.branches().stream().map(GithubBranchResponse::new).collect(Collectors.toList());
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public List<GithubBranchResponse> getBranches() {
        return branches;
    }
}

