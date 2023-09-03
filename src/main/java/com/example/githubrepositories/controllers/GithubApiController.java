package com.example.githubrepositories.controllers;

import com.example.githubrepositories.exceptions.NotAcceptableException;
import com.example.githubrepositories.model.dtos.GithubRepositoryDTO;
import com.example.githubrepositories.services.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/github")
public class GithubApiController {

    private final GithubService githubService;
    public GithubApiController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping("/repositories/{username}")
    public ResponseEntity<Object> getRepositories(@PathVariable String username,
                                                   @RequestHeader(value = "Accept") String accept) throws Exception {
            return ResponseEntity.ok(githubService.getUserRepositories(username));
    }
}

