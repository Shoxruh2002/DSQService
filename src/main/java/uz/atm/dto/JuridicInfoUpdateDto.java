package uz.atm.dto;

import lombok.*;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 12/26/22 5:35 PM
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JuridicInfoUpdateDto {

    private UpdateData data;

    private String sendType;

    private String transactionId;

    private String date;

    private Boolean success;

    private String projectType;

    @Data
    public static class UpdateData {

        private Long tin;

    }
}
