package au.com.ip.api.customerprofiles.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @ApiModelProperty(value = "type")
    @NotNull
    private AddressType type;

    @ApiModelProperty(required = true, value = "address1")
    @NotNull(message = "Address can not be null")
    private String address1;

    @ApiModelProperty(required = true, value = "address2")
    private String address2;

    @ApiModelProperty(required = true, value = "city")
    @Size(max = 20)
    private String city;

    @ApiModelProperty(required = true, value = "postCode")
    @Size(max = 5)
    private String postCode;

    @ApiModelProperty(required = true, value = "country")
    @Size(max = 10)
    private String country;

    @ApiModelProperty(required = true, value = "preferred")
    private boolean preferred;


}
