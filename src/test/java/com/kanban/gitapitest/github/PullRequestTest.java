package com.kanban.gitapitest.github;

import org.junit.Test;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author Kohsuke Kawaguchi
 */

public class PullRequestTest extends AbstractGitHubApiTestBase {

    private GHRepository getRepository() throws IOException {
//        return gitHub.getRepository("dev-stream/test");
        return gitHub.getRepository("next-step/java-lotto");
    }

    @Test
    public void repository_pull_request_ALL() throws Exception {
        List<GHPullRequest> pullRequests = getRepository().getPullRequests(GHIssueState.ALL);

//        assertThat(pullRequests.size(), is(2));
        assertThat(pullRequests.size(), is(157));
    }

    @Test
    public void repository_pull_request_when_CLOSE() throws IOException {
        List<GHPullRequest> pullRequests = getRepository().getPullRequests(GHIssueState.CLOSED);
        GHPullRequest request = pullRequests.get(0);

        assertThat(request.getState(), is(GHIssueState.CLOSED));
//        assertThat(request.getId(), is(264578356));
        assertThat(request.getId(), is(264578356L));
    }

    @Test
    public void repository_pull_request_when_OPEN() throws IOException {
        List<GHPullRequest> pullRequests = getRepository().getPullRequests(GHIssueState.OPEN);
        GHPullRequest request = pullRequests.get(0);

        assertThat(request.getState(), is(GHIssueState.OPEN));
        assertThat(request.getId(), is(265374595L));
    }

    @Test
    public void repository_pull_request_connect_user() {

    }

}
