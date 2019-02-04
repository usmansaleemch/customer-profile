package au.com.ip.api.customerprofiles.service;

import au.com.ip.api.customerprofiles.dto.Profile;

/**
 * Profile Service Interface
 */
public interface ProfileService {

    Profile getProfileById(String id);

    Profile saveProfile(Profile profile);

    void updateProfile(String id, Profile profile);

    void deleteProfile(String id);

}
