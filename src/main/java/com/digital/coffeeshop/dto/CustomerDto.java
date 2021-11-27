package com.digital.coffeeshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDto {

  private String id;

  @JsonProperty(required = true)
  @NotNull
  private String customerName;

  @JsonProperty(required = true)
  @NotNull
  @Size(min = 10, max = 10)
  private String mobileNumber;

  private String address;
}
