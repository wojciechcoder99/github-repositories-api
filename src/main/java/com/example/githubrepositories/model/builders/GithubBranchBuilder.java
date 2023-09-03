package com.example.githubrepositories.model.builders;

import com.example.githubrepositories.model.dtos.GithubBranchDTO;
import com.example.githubrepositories.model.dtos.GithubCommitDTO;

public final class GithubBranchBuilder {
    private String name;
    private GithubCommitDTO lastCommitSha;

    public GithubBranchBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public GithubBranchBuilder setLastCommitSha(GithubCommitDTO lastCommitSha) {
        this.lastCommitSha = lastCommitSha;
        return this;
    }

    public GithubBranchDTO build() {
        return new GithubBranchDTO(name, lastCommitSha);
    }
}
