package net.ivica.reservations.service;

import net.ivica.reservations.api.UserProfile;
import net.ivica.reservations.api.dao.GenericDao;
import net.ivica.reservations.api.dao.UserProfileDao;
import net.ivica.reservations.api.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userProfileService")
public class UserProfileServiceImpl extends AbstractGenericService<UserProfile> implements UserProfileService {

    @Autowired
    private UserProfileDao _userProfileDao;

    @Override
    protected GenericDao<UserProfile> getEntityDao() {
        return getUserProfileDao();
    }

    public UserProfileDao getUserProfileDao() {
        return _userProfileDao;
    }
}
