package com.golovkobalak.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Data
@NoArgsConstructor
@Scope("prototype")
public class CurrencyReportDTO {
    private String currencyName;
    private Double buyRate;
    private String buyBank;
    private Double sellRate;
    private String sellBank;

}
