package net.ivica.reservations.api.dao;

import net.ivica.reservations.api.UserProfile;

public interface UserProfileDao extends GenericDao<UserProfile> {

    UserProfile findUserProfileByEmail(String email);

}
