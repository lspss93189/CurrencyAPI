package cathaybk.demo.controller;

import cathaybk.demo.dto.request.CurrencyRqDto;
import cathaybk.demo.dto.response.CurrencyRsDto;
import cathaybk.demo.repository.CurrencyRepository;
import cathaybk.demo.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private CurrencyRepository currencyRepository;

    private CurrencyService currencyService;

    public CurrencyController(CurrencyRepository currencyRepository, CurrencyService currencyService) {
        this.currencyRepository = currencyRepository;
        this.currencyService = currencyService;
    }

    @GetMapping
    public List<CurrencyRsDto> findAll() {
        return currencyService.findAll();
    }

    @PostMapping
    public CurrencyRsDto create(@RequestBody CurrencyRqDto request) {
        return currencyService.create(request);
    }

    @PutMapping("/{id}")
    public CurrencyRsDto update(@PathVariable Long id, @RequestBody CurrencyRqDto request) {
        return currencyService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        currencyService.delete(id);
    }
}
