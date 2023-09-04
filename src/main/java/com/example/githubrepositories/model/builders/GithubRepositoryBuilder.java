package com.example.githubrepositories.model.builders;

import com.example.githubrepositories.model.dtos.GithubBranchDTO;
import com.example.githubrepositories.model.dtos.GithubOwnerDTO;
import com.example.githubrepositories.model.dtos.GithubRepositoryDTO;

import java.util.List;

public final class GithubRepositoryBuilder {
    private String repoName;
    private GithubOwnerDTO owner;
    private boolean isFork;
    private List<GithubBranchDTO> branches;

    public GithubRepositoryBuilder setName(String name) {
        repoName = name;
        return this;
    }

    public GithubRepositoryBuilder setOwner(GithubOwnerDTO owner) {
        this.owner = owner;
        return this;
    }

    public GithubRepositoryBuilder setBranches(List<GithubBranchDTO> branches) {
        this.branches = branches;
        return this;
    }

    public GithubRepositoryBuilder setIsFork(boolean isFork) {
        this.isFork = isFork;
        return this;
    }

    public GithubRepositoryDTO build() {
        return new GithubRepositoryDTO(repoName, owner, isFork, branches);
    }
}
