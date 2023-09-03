package com.example.githubrepositories.model.builders;

import com.example.githubrepositories.model.dtos.GithubBranchDTO;
import com.example.githubrepositories.model.dtos.GithubRepositoryDTO;

import java.util.List;

public final class GithubRepositoryBuilder {
    private String repoName;
    private String owner;
    private List<GithubBranchDTO> branches;

    public GithubRepositoryBuilder setName(String name) {
        repoName = name;
        return this;
    }

    public GithubRepositoryBuilder setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public GithubRepositoryBuilder setBranches(List<GithubBranchDTO> branches) {
        this.branches = branches;
        return this;
    }

    public GithubRepositoryDTO build() {
        return new GithubRepositoryDTO(repoName, owner, branches);
    }
}
