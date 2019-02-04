package au.com.ip.api.customerprofiles;

import au.com.ip.api.customerprofiles.controller.ProfilesApiController;
import au.com.ip.api.customerprofiles.dto.Address;
import au.com.ip.api.customerprofiles.dto.AddressType;
import au.com.ip.api.customerprofiles.dto.Profile;
import au.com.ip.api.customerprofiles.service.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProfilesApiController.class)

public class ProfileControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProfileService profileService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void givenProfile_whenPostProfile_thenReturnJsonArray()
            throws Exception {
        Address sampleAddress = Address.builder()
                    .address1("549 Queen St.")
                    .type(AddressType.OFFICE)
                    .build();
        List<Address> addressList = Arrays.asList(sampleAddress);
        Profile testProfile = Profile.builder()
                        .firstName("John")
                        .lastName("Doe")
                        .dateOfBirth(LocalDate.parse("1981-02-01"))
                        .addresses(addressList)
                        .build();

        given(profileService.saveProfile(testProfile)).willReturn(testProfile);

        mockMvc.perform(
                post("/profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testProfile)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("John")));

        verify(profileService, times(1)).saveProfile(testProfile);
        verifyNoMoreInteractions(profileService);
    }

    @Test
    public void givenId_whenGetProfile_thenReturnJsonArray() throws Exception {

        Profile profile = Profile.builder()
                .profileId("1")
                .firstName("John")
                .lastName("Doe")
                .dateOfBirth(LocalDate.parse("1981-02-01"))
                .build();

        when(profileService.getProfileById("1")).thenReturn(profile);

        mockMvc.perform(get("/profiles/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.profileId", is("1")))
                .andExpect(jsonPath("$.firstName", is("John")));
        verify(profileService, times(1)).getProfileById("1");
        verifyNoMoreInteractions(profileService);
    }


}
