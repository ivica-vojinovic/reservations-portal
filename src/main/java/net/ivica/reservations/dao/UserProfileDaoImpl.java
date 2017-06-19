package net.ivica.reservations.dao;

import net.ivica.reservations.api.UserProfile;
import net.ivica.reservations.api.dao.UserProfileDao;
import org.springframework.stereotype.Repository;

@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractGenericDao<UserProfile> implements UserProfileDao {

    public UserProfileDaoImpl() {
        super(UserProfile.class);
    }

}
