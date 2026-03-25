package cathaybk.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoindeskRsDtoBpi {
    private String code;

    private String symbol;

    private String rate;

    private String description;

    private double rate_float;
}
