package uz.atm.dto.differentiation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import uz.atm.enums.DsqOrganizationStatus;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 11/2/22 2:05 PM
 **/
@AllArgsConstructor
@NoArgsConstructor
public class FinancialStatusMacroEconomyDto {

    public Long inn;

    public Integer year;

    public Integer quarter;

    public FinancialReport likvidlikDarajasi;//Жорий ликвидлик даражаси
    public FinancialReport tezLikvidlikDarajasi;// Тез ликвидлик даражаси
    public FinancialReport kunlikKreditorQarzlarAylanmasi;//Кунлик кредитор қарзлар айланмаси
    public FinancialReport kunlikDebitorQarzlarAylanmasi;//Кунлик дебитор қарзлар айланмаси
    public FinancialReport qarzningKapitalgaNisbati;//Қарз (мажбуриятлар)нинг капиталга нисбати
    public FinancialReport qarzningAktivlargaNisbati;//Қарз (мажбуриятлар)нинг активларга нисбати
    public FinancialReport majburyatlarningEBITgaNisbati;//Мажбуриятларнинг EBITDAга нисбати
    public FinancialReport EBITningQarzTolovlarigaNisbati;//EBITDAнинг қарз тўловларига нисбати
    public FinancialReport foizXarajatlariningBoglanishi;//Фоиз харажатларининг қопланиши
    public FinancialReport sofFoydaMarjasi;//Соф фойда маржаси
    public FinancialReport operatsionFoydaMarjasi;//Операцион фойда маржаси
    public FinancialReport aylanmaMablaglarRentabelligi;//Айланма маблағларнинг рентабеллиги
    public FinancialReport aktivlarningRentabelligi;//Активларнинг рентабеллиги
    public FinancialReport kapitalningRentabelligi;//Капиталнинг рентабеллиги
    public FinancialReport xarajatlarningQoplanishi;//Харажатларнинг қопланиши
    public FinancialReport tashqiQarzningMajgaNisbati;//Ташқи қарзнинг мажбуриятларга нисбати*
    public FinancialReport xorijiyValyutadagiTashQarzJamiQarzNisbati;//Хорижий валютадаги ташқи қарзнинг жами қарзга нисбати

    public FinancialReport total;

    public FinancialStatusMacroEconomyDto(Long inn, Integer year, Integer quarter) {
        this.inn = inn;
        this.year = year;
        this.quarter = quarter;
    }

    public FinancialStatusMacroEconomyDto(Long inn, Integer year, Integer quarter, Boolean isHighRisk) {

        this.inn = inn;
        this.year = year;
        this.quarter = quarter;
        if (isHighRisk) {
            this.likvidlikDarajasi = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.tezLikvidlikDarajasi = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.kunlikKreditorQarzlarAylanmasi = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.kunlikDebitorQarzlarAylanmasi = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.qarzningKapitalgaNisbati = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.qarzningAktivlargaNisbati = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.majburyatlarningEBITgaNisbati = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.EBITningQarzTolovlarigaNisbati = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.foizXarajatlariningBoglanishi = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.sofFoydaMarjasi = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.operatsionFoydaMarjasi = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.aylanmaMablaglarRentabelligi = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.aktivlarningRentabelligi = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.kapitalningRentabelligi = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.xarajatlarningQoplanishi = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.tashqiQarzningMajgaNisbati = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.xorijiyValyutadagiTashQarzJamiQarzNisbati = new FinancialReport(DsqOrganizationStatus.HIGH);
            this.total = new FinancialReport(DsqOrganizationStatus.HIGH);
        }
    }
}
