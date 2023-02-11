package uz.atm.dto.differentiation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import uz.atm.enums.DsqOrganizationStatus;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 11/4/22 5:04 PM
 **/
@AllArgsConstructor
@NoArgsConstructor
public class YearFinancialStatusDto {

    public Long inn;

    public String orgName;

    public Integer year;

    public Integer quarter;

    public FinancialReport likvidlikDarajasi;//Жорий ликвидлик даражаси
    public FinancialReport kunlikKreditorQarzlarAylanmasi;//Кунлик кредитор қарзлар айланмаси
    public FinancialReport xarajatlarningQoplanishi;//Харажатларнинг қопланиши
    public FinancialReport total;

    public YearFinancialStatusDto(Long inn, Integer year, Integer quarter, FinancialReport likvidlikDarajasi, FinancialReport kunlikKreditorQarzlarAylanmasi, FinancialReport xarajatlarningQoplanishi, FinancialReport total) {
        this.inn = inn;
        this.year = year;
        this.quarter = quarter;
        this.likvidlikDarajasi = likvidlikDarajasi;
        this.kunlikKreditorQarzlarAylanmasi = kunlikKreditorQarzlarAylanmasi;
        this.xarajatlarningQoplanishi = xarajatlarningQoplanishi;
        this.total = total;
    }

    public YearFinancialStatusDto(Long tin, Integer year, Integer quarter, boolean isHighRisk) {
        this.inn = tin;
        this.year = year;
        this.quarter = quarter;
        if (isHighRisk) {
            this.likvidlikDarajasi = new FinancialReport(DsqOrganizationStatus.VERY_HIGH);
            this.kunlikKreditorQarzlarAylanmasi = new FinancialReport(DsqOrganizationStatus.VERY_HIGH);
            this.xarajatlarningQoplanishi = new FinancialReport(DsqOrganizationStatus.VERY_HIGH);
            this.total = new FinancialReport(DsqOrganizationStatus.VERY_HIGH);
        }
    }
}
