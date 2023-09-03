package com.example.githubrepositories.model.builders;

import com.example.githubrepositories.model.dtos.BranchInfoDTO;
import com.example.githubrepositories.model.dtos.RepositoryInfoDTO;

import java.util.List;

public final class RepositoryInfoBuilder {
    private String repoName;
    private String owner;
    private List<BranchInfoDTO> branches;

    public RepositoryInfoBuilder setName(String name) {
        repoName = name;
        return this;
    }

    public RepositoryInfoBuilder setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public RepositoryInfoBuilder setBranches(List<BranchInfoDTO> branches) {
        this.branches = branches;
        return this;
    }

    public RepositoryInfoDTO build() {
        return new RepositoryInfoDTO(repoName, owner, branches);
    }
}
