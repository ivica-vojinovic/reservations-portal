package net.ivica.reservations.api.service;

import net.ivica.reservations.api.UserProfile;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserProfileService extends GenericService<UserProfile>, UserDetailsService {
}
