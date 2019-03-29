package com.kanban.gitapitest.github;

import org.junit.Test;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class PullRequestDetailTest extends AbstractGitHubApiTestBase {

    private static int PULL_REQUEST_ID = 1;

    private GHRepository getRepository() throws IOException {
//        return gitHub.getRepository("dev-stream/test");
        return gitHub.getRepository("next-step/java-lotto");
    }

    @Test
    public void pullRequest_info_by_id_1() throws IOException {
        GHPullRequest pullRequest = getRepository().getPullRequest(PULL_REQUEST_ID);

        assertThat(pullRequest.getId(), is(228589205L));
        assertThat(pullRequest.getState(), is(GHIssueState.CLOSED));
    }

    @Test
    public void reviews_by_pullRequestID_1() throws IOException {
        // https://api.github.com/repos/next-step/java-lotto/pulls/1/reviews
        GHPullRequest pullRequest = getRepository().getPullRequest(PULL_REQUEST_ID);

        List<GHPullRequestReview> reviews = pullRequest.listReviews().asList();


        assertThat(reviews.size(), is(1));
        assertThat(reviews.get(0).getId(), is(171884051L));
    }

    @Test
    public void comments_by_pullRequestID_1() throws IOException {
//        https://api.github.com/repos/next-step/java-lotto/pulls/1/comments
//        firstComment.getBody();      // content
//        firstComment.getUser();      // viewer info

        GHPullRequest pullRequest = getRepository().getPullRequest(PULL_REQUEST_ID);

        List<GHPullRequestReviewComment> comments = pullRequest.listReviewComments().asList();
        GHPullRequestReviewComment firstComment = comments.get(0);
        System.out.println(firstComment.getHtmlUrl());

        System.out.println(firstComment.getParent().getChangedFiles());


        assertThat(comments.size(), is(5));                // reivew list
        assertThat(firstComment.getId(), is(231015485));   // 첫번째 리뷰 info
    }

    @Test
    public void commits_detail_by_pullRequestID_1() throws IOException {
        // https://api.github.com/repos/next-step/java-lotto/pulls/1/commitsO

        GHPullRequest pullRequest = getRepository().getPullRequest(PULL_REQUEST_ID);
        List<GHPullRequestCommitDetail> ghPullRequestCommitDetails = pullRequest.listCommits().asList();

        assertThat(ghPullRequestCommitDetails.get(0).getCommit().getMessage(), is("feat: 로또 1단계 구현"));
        assertThat(ghPullRequestCommitDetails.size(), is(8));
    }

    @Test
    public void files_by_pullRequestID_1() throws IOException {
        // https://api.github.com/repos/next-step/java-lotto/pulls/1/files

        GHPullRequest pullRequest = getRepository().getPullRequest(PULL_REQUEST_ID);
        List<GHPullRequestFileDetail> ghPullRequestFileDetails = pullRequest.listFiles().asList();   // blob, raw, content

        assertThat(ghPullRequestFileDetails.size(), is(15));
        assertThat(ghPullRequestFileDetails.get(0).getStatus(), is("added"));
        assertThat(ghPullRequestFileDetails.get(0).getFilename(), is("src/main/java/lotto/LottoConsole.java"));

    }
}
