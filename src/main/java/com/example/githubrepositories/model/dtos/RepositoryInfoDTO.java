package com.example.githubrepositories.model.dtos;

import java.util.List;

public record RepositoryInfoDTO(String name, String owner, List<BranchInfoDTO> branches) {
}
