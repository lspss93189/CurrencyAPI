package cathaybk.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoindeskRsDto {

    private CoindeskRsDtoTime time;

    private String disclaimer;

    private String chartName;

    private Map<String,CoindeskRsDtoBpi> bpi;
}
