package cathaybk.demo;

import cathaybk.demo.dto.response.CoindeskRsDto;
import cathaybk.demo.dto.response.CoindeskRsDtoBpi;
import cathaybk.demo.dto.response.CoindeskRsDtoTime;
import cathaybk.demo.dto.response.CoindeskTransformedRsDto;
import cathaybk.demo.repository.CurrencyRepository;
import cathaybk.demo.service.CoindeskService;
import cathaybk.demo.entity.Currency;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CoindeskServiceTest {

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private CurrencyRepository currencyRepository;

    @Autowired
    private CoindeskService coindeskService;

    @Test
    void testTransformData() {
        // 模擬 Coindesk API 回傳
        CoindeskRsDto raw = new CoindeskRsDto();
        CoindeskRsDtoTime time = new CoindeskRsDtoTime();
        time.setUpdatedISO("2024-01-01T12:00:00Z");
        raw.setTime(time);

        CoindeskRsDtoBpi usd = new CoindeskRsDtoBpi();
        usd.setCode("USD");
        usd.setRate("67,000.1234");

        Map<String, CoindeskRsDtoBpi> bpiMap = new HashMap<>();
        bpiMap.put("USD", usd);
        raw.setBpi(bpiMap);


        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any()))
                .thenReturn(raw);

        Currency currency = new Currency();
        currency.setCurrencyCode("USD");
        currency.setCurrencyName("美元");

        Mockito.when(currencyRepository.findByCurrencyCode("USD")).thenReturn(Optional.of(currency));

        CoindeskTransformedRsDto result = coindeskService.transformData();

        // 驗證結果
        assertNotNull(result);
        assertEquals("2024/01/01 12:00:00", result.getUpdatedTime());
        assertEquals(1, result.getCurrencyInfoList().size());
        assertEquals("USD", result.getCurrencyInfoList().get(0).getCurrency());
        assertEquals("美元", result.getCurrencyInfoList().get(0).getCurrencyName());
        assertEquals("67,000.1234", result.getCurrencyInfoList().get(0).getRate());
    }
}
