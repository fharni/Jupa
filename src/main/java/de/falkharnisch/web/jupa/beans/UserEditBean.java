package de.falkharnisch.web.jupa.beans;

import de.falkharnisch.web.jupa.database.Membership;
import de.falkharnisch.web.jupa.database.User;
import de.falkharnisch.web.jupa.services.UserService;
import de.falkharnisch.web.jupa.util.RandomString;
import de.falkharnisch.web.jupa.util.Util;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Managed bean for manipulating other user data.
 */
@ManagedBean
@SessionScoped
public class UserEditBean {

    private static final int CLUB_PREFIX_END_INDEX = 7;
    private static final int USERID_WIFHTOUT_CHECKSUM_LENGTH = 12;
    private RandomString randomString = new RandomString();

    @Inject
    private Logger logger;

    @Inject
    private UserService userService;

    private User selectedUser;

    private User user;

    @PostConstruct
    private void initUser() {
        String username = Util.getUserName();
        user = userService.getUserByUsername(username);
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }


    public boolean isShowCreateMember() {
        return selectedUser != null;
    }

    public void newMember() {
        this.selectedUser = new User();
    }

    public void abort() {
        selectedUser = null;
    }

    public void saveMember() {
        if (selectedUser.getId() == null) {
            String userId = getNextUserId();
            selectedUser.setUsername(userId);

            String password = randomString.nextString();
            selectedUser.setPassword(generateHashedPassword(password));

            userService.persist(selectedUser);

            Membership membership = new Membership(selectedUser, user.getClub(), new Date());
            userService.persistOther(membership);

            mailPasswordToUser(password);
        } else {
            userService.merge(selectedUser);
        }
        abort();
    }

    private String getNextUserId() {
        String maxId = userService.getMaxIdForClub(user.getClub());
        // cut last check number
        String prefix = maxId.substring(0, CLUB_PREFIX_END_INDEX);
        Integer nextId = Integer.parseInt(maxId.substring(CLUB_PREFIX_END_INDEX, USERID_WIFHTOUT_CHECKSUM_LENGTH)) + 1;

        DecimalFormat idFormat = new DecimalFormat("00000");
        String idWithoutCheckNumber = prefix + idFormat.format(nextId);

        return Util.calculateEan13(idWithoutCheckNumber);
    }

    private String generateHashedPassword(String password) {
        logger.info("New Password for user {} is now {}", selectedUser.getUsername(), password);
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private void mailPasswordToUser(String password) {
        // TODO Mail versenden
    }
}
