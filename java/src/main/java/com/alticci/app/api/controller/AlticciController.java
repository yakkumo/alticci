package com.alticci.app.api.controller;


import com.alticci.app.application.responses.SingleResultDto;
import com.alticci.app.application.services.alticci.IAlticciQuery;
import com.alticci.app.application.services.alticci.dtos.AlticciDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/alticci")
@RequiredArgsConstructor
@SecurityScheme(
        name = "Basic Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class AlticciController {

    private final IAlticciQuery _alticciQuery;

    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = {"alticci"})
    @GetMapping(value = {"/{value}"})
    public SingleResultDto<AlticciDto> calculateAlticci(@PathVariable(name = "value") Long value) {
        try {
            return _alticciQuery.findById(value);
        } catch (Exception ex) {
            return new SingleResultDto<>(ex);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = {"alticci"})
    @GetMapping(value = {"show-cache"})
    public ConcurrentMap<Object,Object> showCache() {
        try {
            return _alticciQuery.keys("alticci");
        } catch (Exception ex) {
            return null;
        }
    }

}
