package com.kanban.gitapitest.github;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.RateLimitHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Kohsuke Kawaguchi
 */
public abstract class AbstractGitHubApiTestBase extends Assert {

    protected GitHub gitHub;

    @Before
    public void setUp() throws Exception {
        File f = new File(System.getProperty("user.home"), ".github");
        if (f.exists()) {
            Properties props = new Properties();
            FileInputStream in = null;
            try {
                in = new FileInputStream(f);
                props.load(in);
            } finally {
                IOUtils.closeQuietly(in);
            }
            // use the non-standard credential preferentially, so that developers of this library do not have
            // to clutter their event stream.
            gitHub = GitHubBuilder.fromProperties(props).withRateLimitHandler(RateLimitHandler.FAIL).build();
        } else {
            gitHub = GitHubBuilder.fromCredentials().withRateLimitHandler(RateLimitHandler.FAIL).build();
        }
    }

    protected GHUser getUser() {
        try {
            return gitHub.getMyself();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    protected void kohsuke() {
        String login = getUser().getLogin();
        Assume.assumeTrue(login.equals("kohsuke") || login.equals("kohsuke2"));
    }

//    protected static final RandomNameGenerator rnd = new RandomNameGenerator();
}
