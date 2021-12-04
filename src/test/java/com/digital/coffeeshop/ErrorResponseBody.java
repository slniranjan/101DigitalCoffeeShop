package com.digital.coffeeshop;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponseBody {

  private String timestamp;
  private Integer status;
  private List<String> error;
  private String type;
  private String path;
  private String message;

}
