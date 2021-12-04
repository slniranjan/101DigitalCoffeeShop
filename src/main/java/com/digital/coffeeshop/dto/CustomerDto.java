package com.digital.coffeeshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerDto {

  private Long id;

  @JsonProperty(required = true)
  @NotNull
  private String customerName;

  @JsonProperty(required = true)
  @NotNull
  @Size(min = 10, max = 10)
  private String mobileNumber;

  private String address;
}
