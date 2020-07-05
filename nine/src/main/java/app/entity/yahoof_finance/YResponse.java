package app.entity.yahoof_finance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class YResponse {
  private YResult marketSummaryResponse;
}
