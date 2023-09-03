package com.example.githubrepositories.model.builders;

import com.example.githubrepositories.model.dtos.BranchInfoDTO;

public final class BranchInfoBuilder {
    private String name;
    private String lastCommitSha;

    public BranchInfoBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BranchInfoBuilder setLastCommitSha(String lastCommitSha) {
        this.lastCommitSha = lastCommitSha;
        return this;
    }

    public BranchInfoDTO build() {
        return new BranchInfoDTO(name, lastCommitSha);
    }
}
