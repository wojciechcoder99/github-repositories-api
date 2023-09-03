package com.example.githubrepositories.controllers;

import com.example.githubrepositories.model.dtos.GithubRepositoryDTO;
import com.example.githubrepositories.services.GithubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/github")
public class GithubApiController {
    private final GithubService githubService;
    public GithubApiController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/repositories/{username}")
    public ResponseEntity<List<GithubRepositoryDTO>> getRepositories(@PathVariable String username,
                                                   @RequestHeader(value = "Accept") String accept) throws Exception {
        return ResponseEntity.ok(githubService.getUserRepositories(username));
    }
}

