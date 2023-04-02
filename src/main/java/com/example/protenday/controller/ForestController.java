package com.example.protenday.controller;

import com.example.protenday.dto.Forest;
import com.example.protenday.dto.response.ForestResponse;
import com.example.protenday.dto.response.Response;
import com.example.protenday.service.ForestEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/forest")
public class ForestController {

    private final ForestEntityService forestEntityService;

    @GetMapping("/{encodedId}")
    public Response<ForestResponse> searchForest(@PathVariable("encodedId") String encodedId) {

        Forest forest = forestEntityService.searchForest(encodedId);

        return Response.success(ForestResponse.fromForest(forest));
    }
}
