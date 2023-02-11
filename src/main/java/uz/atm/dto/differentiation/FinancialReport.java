package uz.atm.dto.differentiation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import uz.atm.enums.DsqOrganizationStatus;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 11/2/22 2:14 PM
 **/
@AllArgsConstructor
@NoArgsConstructor
public class FinancialReport {

    public DsqOrganizationStatus status;

    public Double amount;

    public FinancialReport(DsqOrganizationStatus status) {
        this.status = status;
    }
}
