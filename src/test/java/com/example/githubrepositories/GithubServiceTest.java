package com.example.githubrepositories;

import com.example.githubrepositories.model.dtos.GithubBranchDTO;
import com.example.githubrepositories.model.dtos.GithubCommitDTO;
import com.example.githubrepositories.model.dtos.GithubOwnerDTO;
import com.example.githubrepositories.model.dtos.GithubRepositoryDTO;
import com.example.githubrepositories.model.responses.GithubRepositoryResponse;
import com.example.githubrepositories.services.GithubService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;
import static org.mockito.Mockito.when;

public class GithubServiceTest {
    @InjectMocks
    private GithubService githubService;
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void getUserRepositoriesTest() {
        // prepare
        GithubRepositoryResponse repo1 = new GithubRepositoryResponse(
                new GithubRepositoryDTO("repo1",
                        new GithubOwnerDTO("wojciechcoder99"),
                        false,
                        List.of(new GithubBranchDTO("master",
                                new GithubCommitDTO("652g3diu2u7d7")))));

        when(githubService.createUserRepositoriesResponse("wojciechcoder99"))
                .thenReturn(List.of(repo1));

        // when
        List<GithubRepositoryResponse> result = githubService.createUserRepositoriesResponse("wojciechcoder99");

        // then
        Assertions.assertThat(result)
                .isNotNull()
                .hasSize(1)
                .extracting(GithubRepositoryResponse::getRepositoryName)
                .containsExactly("repo1");
    }
}
