package in.ashwanthkumar.gocd.client.apis;

import in.ashwanthkumar.gocd.client.GoCD;
import in.ashwanthkumar.gocd.client.TestUtils;
import in.ashwanthkumar.gocd.client.auth.UsernameAndPasswordAuthentication;
import in.ashwanthkumar.gocd.client.types.User;
import in.ashwanthkumar.gocd.client.types.UserRole;
import in.ashwanthkumar.gocd.client.types.Users;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserResourcesTest {

    @Test
    public void shouldParseForAllUsers() throws IOException {
        GoCD client = new GoCD("http://server", new UsernameAndPasswordAuthentication("foo", "bar"), TestUtils.readFile("/responses/users/get_all_users.json"));
        Users allUsers = client.users().getAllUsers();
        assertThat(allUsers.sizeOfUsers(), is(1));
        User user = allUsers.getUsers().get(0);
        assertThat(user.getLoginName(), is("jdoe"));
        assertThat(user.getAdmin(), is(false));
        assertThat(user.getEnabled(), is(true));
        assertThat(user.getCheckinAliases(), hasItems("jdoe", "johndoe"));
        assertThat(user.getRoles(), hasItems(new UserRole("gocd", "role name")));
    }

    @Test
    public void shouldParseForOneUser() throws IOException {
        GoCD client = new GoCD("http://server", new UsernameAndPasswordAuthentication("foo", "bar"), TestUtils.readFile("/responses/users/get_one_user.json"));
        User jdoe = client.users().getOneUser("jdoe");
        assertThat(jdoe.getLoginName(), is("jdoe"));
        assertThat(jdoe.getAdmin(), is(true));
        assertThat(jdoe.getEnabled(), is(true));
        assertThat(jdoe.getCheckinAliases(), is(new ArrayList<String>()));
        assertThat(jdoe.getRoles(), hasItems(new UserRole("gocd", "role name")));
    }

}