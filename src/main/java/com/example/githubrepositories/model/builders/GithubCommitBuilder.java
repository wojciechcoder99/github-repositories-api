package com.example.githubrepositories.model.builders;

import com.example.githubrepositories.model.dtos.GithubCommitDTO;

public final class GithubCommitBuilder {
    private String sha;

    public GithubCommitBuilder setSha(String sha) {
        this.sha = sha;
        return this;
    }

    public GithubCommitDTO build() {
        return new GithubCommitDTO(sha);
    }
}
