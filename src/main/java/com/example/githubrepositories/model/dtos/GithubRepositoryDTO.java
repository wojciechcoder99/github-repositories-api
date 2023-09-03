package com.example.githubrepositories.model.dtos;

import java.util.List;

public record GithubRepositoryDTO(String name, String owner, List<GithubBranchDTO> branches) {
}
