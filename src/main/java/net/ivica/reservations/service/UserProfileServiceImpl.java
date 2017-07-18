package net.ivica.reservations.service;

import net.ivica.reservations.api.UserProfile;
import net.ivica.reservations.api.dao.GenericDao;
import net.ivica.reservations.api.dao.UserProfileDao;
import net.ivica.reservations.api.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userProfileService")
public class UserProfileServiceImpl extends AbstractGenericService<UserProfile> implements UserProfileService {

    private UserProfileDao _userProfileDao;

    @Override
    protected GenericDao<UserProfile> getEntityDao() {
        return getUserProfileDao();
    }

    private UserProfileDao getUserProfileDao() {
        return _userProfileDao;
    }

    @Autowired
    public void setUserProfileDao(UserProfileDao userProfileDao) {
        _userProfileDao = userProfileDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserProfile userProfile = getUserProfileDao().findUserProfileByEmail(email);
        if (userProfile == null) {
            throw new UsernameNotFoundException(String.format("User with email %s not found.", email));
        }

        return userProfile;
    }

}
