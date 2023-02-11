package uz.atm.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static uz.atm.utils.AppUtils.randomInteger;

/**
 * Author: Bekpulatov Shoxruh
 * Date: 10/10/22
 * Time: 15:51
 */
@AllArgsConstructor
@NoArgsConstructor
public class FinancialReportFormDto {

    @Min(value = 1900, message = "Year must be from 1900 to 2055")
    @Max(value = 2025, message = "Year must be from 1900 to 2055")
    public Integer year;

    @Min(value = 1, message = "Quarter must from 1 to 4")
    public Integer quarter;

    @Min(100000000)
    @Max(900000000)
    public Long tin;

    public Integer request_id;

    public FinancialReportFormDto(Integer year, Integer quarter, Long tin) {
        this.year = year;
        this.quarter = quarter;
        this.tin = tin;
        this.request_id = randomInteger();
    }
}
