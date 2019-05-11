package au.com.ip.api.customerprofiles.controller;

import au.com.ip.api.customerprofiles.dto.Profile;
import au.com.ip.api.customerprofiles.service.ProfileService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@Slf4j
public class ProfilesApiController implements ProfilesApi {

    private ProfileService profileService;

    @Autowired
    public ProfilesApiController(ProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     * Delete request handler for deleting customer profile by Id
     * @param profileId unique profile id generated at time of save operation
     * @return Response message
     */
    public ResponseEntity<Void> deleteProfile(@ApiParam(value = "Profile Id",required=true) @PathVariable("profile_id") String profileId) {
        log.debug("Delete Profile " + profileId);
        profileService.deleteProfile(profileId);

        return new ResponseEntity("Profile deleted successfully", HttpStatus.OK);
    }

    /**
     * Retrieves Profile by Id
     * @param profileId Profile Id
     * @return Profile object
     */
    public Profile getProfileById(@ApiParam(value = "Profile Id",required=true) @PathVariable("profile_id") String profileId) {

        log.debug("Retrieve Profile " + profileId);
        log.info("Retrieve Profile " + profileId);
        return profileService.getProfileById(profileId);
    }

    /**
     * Save new customer profile
     * @param profile Profile containing first,last name, dob and address
     * @return Saved Profile object
     */
    public Profile saveProfile(@ApiParam(value = "Profile to be saved" ,required=true )  @Valid @RequestBody Profile profile) {

        log.info("New Profile " + profile);
        log.debug("New Profile " + profile);
        return profileService.saveProfile(profile);
    }

    /**
     * Update Profile based on id
     * @param profileId Provided unique profile id
     * @param profile Profile object containing changes
     * @return Update Profile object
     */
    public ResponseEntity<String> updateProfile(@ApiParam(value = "Profile Id",required=true) @PathVariable("profile_id") String profileId, @ApiParam(value = "Profile with changes" ,required=true )  @Valid @RequestBody Profile profile) {
        log.debug("Update Profile " + profileId);
        log.info("Update Profile " + profileId);
        profileService.updateProfile(profileId, profile);
        return new ResponseEntity("Profile updated successfully", HttpStatus.OK);
    }

}
