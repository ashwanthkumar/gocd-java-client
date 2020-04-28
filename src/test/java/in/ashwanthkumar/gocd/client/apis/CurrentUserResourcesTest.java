package in.ashwanthkumar.gocd.client.apis;

import in.ashwanthkumar.gocd.client.GoCD;
import in.ashwanthkumar.gocd.client.TestGoCD;
import in.ashwanthkumar.gocd.client.TestUtils;
import in.ashwanthkumar.gocd.client.auth.UsernameAndPasswordAuthentication;
import in.ashwanthkumar.gocd.client.types.current_user.CurrentUser;
import in.ashwanthkumar.gocd.client.types.current_user.UpdateCurrentUser;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CurrentUserResourcesTest {
    @Test
    public void shouldParseCurrentUser() throws IOException {
        GoCD client = TestGoCD.from("http://server", new UsernameAndPasswordAuthentication("foo", "bar"), TestUtils.readFile("/responses/current_user/current_user.json"));
        CurrentUser currentUser = client.currentUser().get();
        assertThat(currentUser.getLoginName(), is("jdoe"));
        assertThat(currentUser.getDisplayName(), is("John Doe"));
        assertThat(currentUser.getEnabled(), is(true));
        assertThat(currentUser.getCheckinAliases(), is(new ArrayList<String>()));
        assertThat(currentUser.getEmail(), is(nullValue()));
        assertThat(currentUser.getEmailMe(), is(false));
    }

    @Test
    public void shouldParseUpdateCurrentUser() throws IOException {
        GoCD client = TestGoCD.from("http://server", new UsernameAndPasswordAuthentication("foo", "bar"), TestUtils.readFile("/responses/current_user/update_current_user.json"));
        UpdateCurrentUser userAttributesToUpdate = new UpdateCurrentUser("jdoe@example.com", true, List.of("jdoe", "johndoe"));
        CurrentUser updatedCurrentUser = client.currentUser().update(userAttributesToUpdate);
        assertThat(updatedCurrentUser.getLoginName(), is("jdoe"));
        assertThat(updatedCurrentUser.getDisplayName(), is("jdoe"));
        assertThat(updatedCurrentUser.getEnabled(), is(false));
        assertThat(updatedCurrentUser.getCheckinAliases(), is(List.of("jdoe", "johndoe")));
        assertThat(updatedCurrentUser.getEmail(), is("jdoe@example.com"));
        assertThat(updatedCurrentUser.getEmailMe(), is(true));
    }
}