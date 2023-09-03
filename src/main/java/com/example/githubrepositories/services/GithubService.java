package com.example.githubrepositories.services;

import com.example.githubrepositories.model.builders.GithubBranchBuilder;
import com.example.githubrepositories.model.builders.GithubCommitBuilder;
import com.example.githubrepositories.model.builders.GithubRepositoryBuilder;
import com.example.githubrepositories.model.dtos.GithubBranchDTO;
import com.example.githubrepositories.model.dtos.GithubRepositoryDTO;
import com.example.githubrepositories.model.web.GithubBranchWeb;
import com.example.githubrepositories.model.web.GithubRepoWeb;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubService {

    private static final String GITHUB_API = "https://api.github.com/";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<GithubRepositoryDTO> getUserRepositories(String username) {
        ResponseEntity<List<GithubRepoWeb>> response = executeGetMethod(
                String.format(GITHUB_API + "users/" + "%s" +  "/repos", username));
        return createRepositoriesInfos(response.getBody());
    }

    private List<GithubRepositoryDTO> createRepositoriesInfos(List<GithubRepoWeb> body) {
        if (ObjectUtils.isEmpty(body)) {
            return Collections.emptyList();
        }
        return body.stream()
                .filter(repo -> !repo.isFork())
                .map(this::createSingleRepositoryInfo)
                .collect(Collectors.toList());
    }

    private GithubRepositoryDTO createSingleRepositoryInfo(GithubRepoWeb repo) {
        return new GithubRepositoryBuilder()
                .setName(repo.name())
                .setOwner(repo.owner().login())
                .setBranches(getBranchInfo(repo.owner().login(), repo.name()))
                .build();
    }

    private List<GithubBranchDTO> getBranchInfo(String owner, String repo) {
        ResponseEntity<List<GithubBranchWeb>> response = executeGetMethod(
                String.format(GITHUB_API + "repos/" + "%s" +  "/" + "%s" + "/branches", owner, repo));
        return createBranchesInfos(response.getBody());
    }

    private <T> ResponseEntity<List<T>> executeGetMethod(String url) {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
    }

    private List<GithubBranchDTO> createBranchesInfos(List<GithubBranchWeb> body) {
        if (ObjectUtils.isEmpty(body)) {
            return Collections.emptyList();
        }
        return body.stream()
                .map(this::createSingleBranchInfo)
                .collect(Collectors.toList());
    }

    private GithubBranchDTO createSingleBranchInfo(GithubBranchWeb branch) {
        return new GithubBranchBuilder()
                .setName(branch.name())
                .setLastCommitSha(new GithubCommitBuilder().setSha(branch.commit().sha()).build())
                .build();
    }
}

