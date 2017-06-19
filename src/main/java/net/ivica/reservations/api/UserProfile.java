package net.ivica.reservations.api;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

@Entity
@BatchSize(size = 100)
@Table(name = "user_profile")
public class UserProfile implements Identifiable {

    private Long _userProfileId;

    // TODO validacija input polja da je user uneo email kao username;
    private String _email;

    private String _password;

    private String _firstName;

    private String _lastName;

    private String _phoneNumber;

    @Column(name = "email", nullable = false, length = 25, unique = true)
    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    @Column(name = "first_name", nullable = false, length = 50)
    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(String firstName) {
        _firstName = firstName;
    }

    @Transient
    @Override
    public Long getIdentifier() {
        return _userProfileId;
    }

    @Column(name = "last_name", nullable = false, length = 70)
    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String lastName) {
        _lastName = lastName;
    }

    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    @Column(name = "phone_number", length = 25)
    public String getPhoneNumber() {
        return _phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        _phoneNumber = phoneNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    public Long getUserProfileId() {
        return _userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        _userProfileId = userProfileId;
    }

}
