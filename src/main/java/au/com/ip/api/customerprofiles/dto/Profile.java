package au.com.ip.api.customerprofiles.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

  /**
   * Unique Identifier representing a specific customer profile
   * @return profileId
   **/
  @ApiModelProperty(value = "Unique Identifier representing a specific customer profile")
  private String profileId;

  @ApiModelProperty(required = true, value = "First Name")
  @NotNull
  @Size(max=25)
  private String firstName;

  @ApiModelProperty(required = true, value = "Last Name")
  @NotNull
  @Size(max=25)
  private String lastName;

  @ApiModelProperty(required = true, value = "Date of Birth")
  @NotNull
  @Valid
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @Past
  private LocalDate dateOfBirth;

  @ApiModelProperty(required = true, value = "address list")
  @NotNull(message = "Address is required, multiple addresses can be accepted.")
  private List<Address> addresses;


  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
  }

}

