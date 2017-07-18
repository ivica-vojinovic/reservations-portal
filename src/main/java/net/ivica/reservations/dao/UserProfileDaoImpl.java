package net.ivica.reservations.dao;

import net.ivica.reservations.api.ParameterTuple;
import net.ivica.reservations.api.UserProfile;
import net.ivica.reservations.api.dao.UserProfileDao;
import org.springframework.stereotype.Repository;

@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractGenericDao<UserProfile> implements UserProfileDao {

    public UserProfileDaoImpl() {
        super(UserProfile.class);
    }

    @Override
    public UserProfile findUserProfileByEmail(String email) {
        return findSingleResult("user_profile_find_by_email", new ParameterTuple("email", email));
    }

}
