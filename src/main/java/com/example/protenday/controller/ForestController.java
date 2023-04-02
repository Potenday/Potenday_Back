package com.example.protenday.controller;

import com.example.protenday.dto.Forest;
import com.example.protenday.dto.response.Response;
import com.example.protenday.service.ForestEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/forest")
public class ForestController {

    private final ForestEntityService forestEntityService;

    @GetMapping("/test")
    public Response<Void> test() {
        return Response.success();
    }

    @GetMapping("/{encodedId}")
    public Response<Void> searchForest(@PathVariable("encodedId") String encodedId) {

        Forest forest = forestEntityService.searchForest(encodedId);

        return Response.success();
    }
}
