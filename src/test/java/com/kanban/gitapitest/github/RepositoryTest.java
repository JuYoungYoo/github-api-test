package com.kanban.gitapitest.github;

import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kohsuke.github.GHMyself;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.Matchers.instanceOf;

@RunWith(SpringRunner.class)
public class RepositoryTest extends AbstractGitHubApiTestBase {

    @Test
    public void test_user_repositories() throws IOException {
        GHUser u = gitHub.getUser("JuYoungYoo");

        Map<String, GHRepository> map = u.getRepositories();

        assertThat(map, IsMapContaining.hasKey("test-1"));
        assertThat(map, IsMapContaining.hasValue(instanceOf(GHRepository.class)));
    }

    @Test
    public void test_user_connect_repositories() throws IOException {
        GitHub gh = GitHub.connect();
        GHMyself user = gh.getMyself();

        Map<String, GHRepository> map = user.getRepositories();

        assertThat(map, IsMapContaining.hasKey("test-1"));
        assertThat(map, IsMapContaining.hasValue(instanceOf(GHRepository.class)));
    }
}