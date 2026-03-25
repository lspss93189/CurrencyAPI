package cathaybk.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoindeskTransformedRsDtoCurrencyInfo {
    private String currency;

    private String currencyName;

    private String rate;
}
