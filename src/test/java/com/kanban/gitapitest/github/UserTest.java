package com.kanban.gitapitest.github;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;
import org.kohsuke.github.*;
import org.kohsuke.github.extras.OkHttpConnector;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsSame.sameInstance;

/**
 * @author Kohsuke Kawaguchi
 */
public class UserTest extends AbstractGitHubApiTestBase {

    @Test
    public void test_user_connect() throws IOException {
        GitHub gh = GitHub.connect();
        GHMyself user = gh.getMyself();

        assertThat(user.getLogin(), is("JuYoungYoo"));
    }


    private Set<GHUser> count30(PagedIterable<GHUser> l) {
        Set<GHUser> users = new HashSet<GHUser>();
        PagedIterator<GHUser> itr = l.iterator();
        for (int i = 0; i < 30 && itr.hasNext(); i++) {
            users.add(itr.next());
        }
        assertEquals(30, users.size());
        return users;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    public void listFollowsAndFollowers() throws IOException {
        GHUser u = gitHub.getUser("JuYoungYoo");
        assertNotEquals(
                count30(u.listFollowers()),
                count30(u.listFollows()));
    }
}
