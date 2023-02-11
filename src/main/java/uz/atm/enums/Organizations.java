package uz.atm.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 10/19/22 12:33 PM
 **/
@AllArgsConstructor
@Getter
public enum Organizations {


    ORG_NAVOIY_KON_METALLURGIYA_KOMBINATI("    “Navoiy kon-metallurgiya kombinati” AJ", 308425864L),
    ORG_OLMALIQ_KON_METALLURGIYA_KOMBINATI("“Olmaliq kon-metallurgiya kombinati” AJ", 202328794L),
    ORG_OZBEKISTON_METALLURGIYA_KOMBINATI("“O‘zbekiston metallurgiya kombinati” AJ", 200460222L),
    ORG_OZAERONAVIGATSIYA_MARKAZI("“O‘zaeronavigatsiya markazi” DUK", 201052000L),
    ORG_FARGONA_AZOT("“Farg‘onaazot” AJ", 200202240L),
    ORG_ISSIQLIK_ENERGIYA_STANSIYALARI("“Issiqlik elektr stansiyalari” AJ", 306349304L),
    ORG_OZBEK_KOMIR("“O‘zbekko‘mir” AJ", 200899410L),
    ORG_OZBEKISTON_TEMIR_YOLLAR("“O‘zbekiston temir yo‘llari” AJ", 201051951L),
    ORG_OZBEKISTON_AIRWAYS("“Uzbekistan airways” AJ", 306628114L),
    ORG_OZBEKISTON_POCHTASI("“O‘zbekiston pochtasi” AJ", 200833833L),
    ORG_OZBEKGIDROENERIYA("“Uzbekgidroenergo” AJ", 304952767L),
    ORG_OZBEKNEFTGAZ("“O‘zbekneftgaz” aj", 200837914L),
    ORG_ANGREN_ISSIQLIK_ELEKTR_STANSIYASI("“Angren issiqlik elektr stansiyasi” AJ", 200595949L),
    ORG_TOSHKENT_METROPOLITENI("“Toshkent metropoliteni” UK", 200898934L),
    ORG_OZBEKTELEKOM("“O‘zbektelekom” AJ", 203366731L),
    ORG_OZSUVTAMINOTI("“O‘zsuvta’minot” AJ", 307224696L),
    ORG_OZBEKISTON_AIRPORTS("“Uzbekistan airports” AJ", 306646884L),
    ORG_OZKIMYOSANOAT("“O‘zkimyosanoat” AJ", 203621367L),
    ORG_HUDUDGAZTAMINOTI("“Hududgazta’minot” AJ", 306605769L),
    ORG_TOSHSHAHARTRANSXIZMATI("“Toshshahartransxizmat” AJ", 302762364L),
    ORG_OZAVTOSANOATI("“O‘zavtosanoati” AJ", 201053918L),
    ORG_HUDUD_ELEKTR_TARMOQLARI("“Hududiy elektr tarmoqlari” AJ", 306350099L),
    ORG_MAXSUSTRANS_ISHLAB_CHIQARISH_BOSHQARMASI("“Maxsustrans ishlab chiqarish boshqarmasi” DUK", 200903001L),
    ORG_OZDONMAHSULOT("“O‘zdonmahsulot” AJ", 201051421L),
    ORG_OZBEKISTON_MILLIY_ELEKTR_TARMOQLARI("“O‘zbekiston milliy elektr tarmoqlari” AJ", 306347741L),
    ORG_YANGI_ANGREN_ISSIQLIK_ELEKTR_STANSIYALARI("“Yangi angren issiqlik elektr stansiyasi” AJ", 200595838L),
    ORG_OZTRANSGAZ("“O‘ztransgaz” AJ", 200626188L);

    private final String name;
    private final Long tin;


    public static String findByTin(Long tin) {

        return Arrays.stream(Organizations.values()).filter(value -> value.getTin().equals(tin)).findFirst().map(Organizations::getName).orElse("UnKnown");
    }
}
