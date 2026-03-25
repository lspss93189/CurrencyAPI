package cathaybk.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoindeskRsDtoTime {
    private String updated;

    private String updatedISO;

    private String updateduk;
}
