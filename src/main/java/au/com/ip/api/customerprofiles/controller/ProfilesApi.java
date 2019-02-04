package au.com.ip.api.customerprofiles.controller;

import au.com.ip.api.customerprofiles.dto.Profile;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "profiles", description = "Profiles API")
public interface ProfilesApi {

    @ApiOperation(value = "Find Customer Profile by Id", nickname = "getProfileById",
                  notes = "Returns a single customer profile", response = Profile.class, tags = {"Profile",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful response", response = Profile.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Profile not found")})
    @GetMapping(value = "/profiles/{profile_id}")
    Profile getProfileById(@ApiParam(value = "Profile Id", required = true) @PathVariable("profile_id") String profileId);


    @ApiOperation(value = "Save Customer Profile", nickname = "saveProfile",
                  notes = "Save customer profile", response = Profile.class, tags = {"Profile",})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Profile saved successfully", response = Profile.class),
            @ApiResponse(code = 405, message = "Invalid input")})
    @PostMapping(value = "/profiles")
    Profile saveProfile(@ApiParam(value = "Profile to be saved", required = true)
                                         @Valid @RequestBody Profile profile);


    @ApiOperation(value = "Update Customer Profile by Id", nickname = "updateProfile",
                  notes = "Update Profile", response = String.class, tags = {"Profile",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profile successfully updated", response = String.class),
            @ApiResponse(code = 400, message = "Invalid Profile Id provided"),
            @ApiResponse(code = 404, message = "Profile not found"),
            @ApiResponse(code = 405, message = "Validation Exception")})
    @PutMapping(value = "/profiles/{profile_id}")
    ResponseEntity<String> updateProfile(@ApiParam(value = "Profile Id", required = true)
                                           @PathVariable("profile_id") String profileId,
                                           @ApiParam(value = "Profile with changes", required = true)
                                           @Valid @RequestBody Profile profile);


    @ApiOperation(value = "Delete Customer Profile by Id", nickname = "deleteProfile", notes = "Delete a profile", tags = {"Profile",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profile successfully deleted"),
            @ApiResponse(code = 404, message = "Profile not found")})
    @DeleteMapping(value = "/profiles/{profile_id}")
    ResponseEntity<Void> deleteProfile(@ApiParam(value = "Profile Id", required = true) @PathVariable("profile_id") String profileId);


}
