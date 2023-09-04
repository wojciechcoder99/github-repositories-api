package com.example.githubrepositories.services;

import com.example.githubrepositories.model.builders.GithubBranchBuilder;
import com.example.githubrepositories.model.builders.GithubCommitBuilder;
import com.example.githubrepositories.model.builders.GithubRepositoryBuilder;
import com.example.githubrepositories.model.dtos.GithubBranchDTO;
import com.example.githubrepositories.model.dtos.GithubRepositoryDTO;
import com.example.githubrepositories.model.responses.GithubRepositoryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubService {

    private static final String GITHUB_API = "https://api.github.com/";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper;

    public GithubService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<GithubRepositoryResponse> createUserRepositoriesResponse(String username) {
        return getUserRepositories(username).stream()
                .map(GithubRepositoryResponse::new)
                .collect(Collectors.toList());
    }

    private List<GithubRepositoryDTO> getUserRepositories(String username) {
        ResponseEntity<List<LinkedHashMap<String, Object>>> response = restTemplate.exchange(
                String.format(GITHUB_API + "users/" + "%s" +  "/repos", username),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return createRepositoriesInfos(response.getBody());
    }

    private List<GithubRepositoryDTO> createRepositoriesInfos(List<LinkedHashMap<String, Object>> reposMapList) {
        if (ObjectUtils.isEmpty(reposMapList)) {
            return Collections.emptyList();
        }
        return reposMapList.stream()
                .map(map -> objectMapper.convertValue(map, GithubRepositoryDTO.class))
                .filter(repo -> !repo.fork())
                .map(this::createSingleRepositoryInfo)
                .collect(Collectors.toList());
    }

    private GithubRepositoryDTO createSingleRepositoryInfo(GithubRepositoryDTO repo) {
        return new GithubRepositoryBuilder()
                .setName(repo.name())
                .setOwner(repo.owner())
                .setIsFork(repo.fork())
                .setBranches(getBranchInfo(repo.owner().login(), repo.name()))
                .build();
    }

    private List<GithubBranchDTO> getBranchInfo(String owner, String repo) {
        ResponseEntity<List<LinkedHashMap<String, Object>>> response = executeGetMethod(
                String.format(GITHUB_API + "repos/" + "%s" +  "/" + "%s" + "/branches", owner, repo));
        return createBranchesInfos(response.getBody());
    }

    private <T> ResponseEntity<List<T>> executeGetMethod(String url) {
        try {
            return exchange(url, null);
        } catch (HttpClientErrorException.Forbidden e) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "YOUR_PERSONAL_ACCESS_TOKEN");
            return exchange(url, new HttpEntity<>(headers));
        }
    }

    private <T> ResponseEntity<List<T>> exchange(String url, HttpEntity<String> entity) {
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }
        );
    }

    private List<GithubBranchDTO> createBranchesInfos(List<LinkedHashMap<String, Object>> branchesMapList) {
        if (ObjectUtils.isEmpty(branchesMapList)) {
            return Collections.emptyList();
        }
        return branchesMapList.stream()
                .map(map -> objectMapper.convertValue(map, GithubBranchDTO.class))
                .map(this::createSingleBranchInfo)
                .collect(Collectors.toList());
    }

    private GithubBranchDTO createSingleBranchInfo(GithubBranchDTO branch) {
        return new GithubBranchBuilder()
                .setName(branch.name())
                .setLastCommitSha(new GithubCommitBuilder().setSha(branch.commit().sha()).build())
                .build();
    }
}

