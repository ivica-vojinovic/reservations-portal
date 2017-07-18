package net.ivica.reservations.service;

import net.ivica.reservations.AbstractTests;
import net.ivica.reservations.api.UserProfile;
import net.ivica.reservations.api.service.UserProfileService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserProfileServiceTests extends AbstractTests {

    private UserProfileService _userProfileService;

    @Test
    public void findAll() throws Exception {
        List<UserProfile> userProfiles = getUserProfileService().findAll();

        Assert.assertTrue(userProfiles.stream().anyMatch(userProfile -> userProfile.getEmail().equals("admin@emisia.net")));
    }

    @Test
    public void findAndDelete() {
//        UserProfile productByName = getUserProfileService().
//
//        Assert.assertNotNull(productByName);
//        Assert.assertEquals("Delete product", productByName.getProductName());
//
//        getProductService().delete(productByName);
//
//        productByName = getProductService().findProductByName("Delete product");
//
//        Assert.assertNull(productByName);
    }

    public UserProfileService getUserProfileService() {
        return _userProfileService;
    }

    @Autowired
    public void setUserProfileService(UserProfileService userProfileService) {
        _userProfileService = userProfileService;
    }

    @Test
    public void save() {
        UserProfile userProfile = new UserProfile();
        String email = "ivojinovic@emisia.net";
        userProfile.setEmail(email);
        String firstName = "Ivica";
        userProfile.setFirstName(firstName);
        String lastName = "Vojinovic";
        userProfile.setLastName(lastName);
        String password = "password";
        userProfile.setPassword(password);
        String phoneNumber = "+3814646537567";
        userProfile.setPhoneNumber(phoneNumber);

        getUserProfileService().save(userProfile);

        UserProfile savedUserProfile = getUserProfileService().findById(userProfile.getUserProfileId());

        Assert.assertEquals(email, savedUserProfile.getEmail());
        Assert.assertEquals(firstName, savedUserProfile.getFirstName());
        Assert.assertEquals(lastName, savedUserProfile.getLastName());
        Assert.assertEquals(password, savedUserProfile.getPassword());
        Assert.assertEquals(phoneNumber, savedUserProfile.getPhoneNumber());
    }

}
