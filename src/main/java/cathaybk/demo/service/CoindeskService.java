package cathaybk.demo.service;

import cathaybk.demo.dto.response.CoindeskRsDto;
import cathaybk.demo.dto.response.CoindeskRsDtoBpi;
import cathaybk.demo.dto.response.CoindeskTransformedRsDto;
import cathaybk.demo.dto.response.CoindeskTransformedRsDtoCurrencyInfo;
import cathaybk.demo.entity.Currency;
import cathaybk.demo.repository.CurrencyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoindeskService {

    private RestTemplate restTemplate;

    private CurrencyRepository currencyRepository;

    public CoindeskService(RestTemplate restTemplate, CurrencyRepository currencyRepository) {
        this.restTemplate = restTemplate;
        this.currencyRepository = currencyRepository;
    }

    public CoindeskRsDto callCoindeskApi() {
        return restTemplate.getForObject("https://kengp3.github.io/blog/coindesk.json", CoindeskRsDto.class);
    }

    public CoindeskTransformedRsDto transformData() {
        CoindeskRsDto rawData = callCoindeskApi();

        //格式化時間
        DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_DATE_TIME;
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(rawData.getTime().getUpdatedISO(), inputFormatter);


        List<CoindeskTransformedRsDtoCurrencyInfo> currencyInfoList = new ArrayList<>();
        for (CoindeskRsDtoBpi bpi : rawData.getBpi().values()) {
            Currency currency = currencyRepository.findByCurrencyCode(bpi.getCode()).orElse(null);
            CoindeskTransformedRsDtoCurrencyInfo currencyInfo = new CoindeskTransformedRsDtoCurrencyInfo();
            currencyInfo.setCurrency(bpi.getCode());
            currencyInfo.setCurrencyName(currency != null ? currency.getCurrencyName() : bpi.getCode());
            currencyInfo.setRate(bpi.getRate());

            currencyInfoList.add(currencyInfo);
        }

        CoindeskTransformedRsDto response = CoindeskTransformedRsDto.builder()
                .updatedTime(dateTime.format(outputFormatter))
                .currencyInfoList(currencyInfoList)
                .build();

        return response;
    }
}
