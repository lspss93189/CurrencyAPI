package cathaybk.demo.service;

import cathaybk.demo.dto.request.CurrencyRqDto;
import cathaybk.demo.dto.response.CurrencyRsDto;
import cathaybk.demo.repository.CurrencyRepository;
import cathaybk.demo.entity.Currency;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    private CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<CurrencyRsDto> findAll() {
        return currencyRepository.findAll().stream()
                .map(results -> CurrencyRsDto.builder()
                        .id(results.getId())
                        .currencyCode(results.getCurrencyCode())
                        .currencyName(results.getCurrencyName())
                        .build()).collect(Collectors.toList());
    }

    public CurrencyRsDto create(CurrencyRqDto request) {
        Currency currency = new Currency();
        currency.setCurrencyCode(request.getCurrencyCode());
        currency.setCurrencyName(request.getCurrencyName());
        Currency saved = currencyRepository.save(currency);

        CurrencyRsDto response = CurrencyRsDto.builder()
                .id(saved.getId())
                .currencyCode(saved.getCurrencyCode())
                .currencyName(saved.getCurrencyName())
                .build();
        return response;
    }

    public CurrencyRsDto update(Long id, CurrencyRqDto request) {
        Currency currency = currencyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("找不到 id=" + id + " 的幣別"));
        currency.setCurrencyCode(request.getCurrencyCode());
        currency.setCurrencyName(request.getCurrencyName());
        Currency saved = currencyRepository.save(currency);

        CurrencyRsDto response = CurrencyRsDto.builder()
                .id(saved.getId())
                .currencyCode(saved.getCurrencyCode())
                .currencyName(saved.getCurrencyName())
                .build();
        return response;
    }

    public void delete(Long id) {
        currencyRepository.deleteById(id);
    }
}
