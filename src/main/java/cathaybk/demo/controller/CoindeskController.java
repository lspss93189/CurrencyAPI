package cathaybk.demo.controller;

import cathaybk.demo.dto.response.CoindeskRsDto;
import cathaybk.demo.dto.response.CoindeskTransformedRsDto;
import cathaybk.demo.service.CoindeskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coindesk")
public class CoindeskController {

    private CoindeskService coindeskService;

    //建構子
    public CoindeskController(CoindeskService coindeskService) {
        this.coindeskService = coindeskService;
    }

    @GetMapping("/raw")
    public CoindeskRsDto getRawData(){
        return coindeskService.callCoindeskApi();
    }

    @PostMapping("/transformed")
    public CoindeskTransformedRsDto getTransformedData(){
        return coindeskService.transformData();
    }
}
