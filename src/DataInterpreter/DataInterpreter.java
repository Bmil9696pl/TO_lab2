package DataInterpreter;

import DataDecoder.DataDecoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataInterpreter{
    static public void interpret(String[] dataPack){
        DataDecoder dataDecoder = new DataDecoder();
        String message = "";
        Pattern pattern;
        Matcher matcher;
        String newString;
        Boolean grupa7wwWW = false;
        for (String data :
                dataPack) {
            System.out.println(data);
            String[] attributes = dataDecoder.prepareAttributes(data);
            for(int x = 0; x < attributes.length; x++) {
                switch (x) {
                    //attributes0
                    case 0 -> {
                        newString = "Numer dnia miesiaca: ";
                        pattern = Pattern.compile("^.{0}(.{2})");
                        matcher = pattern.matcher(attributes[x]);
                        while (matcher.find()) {
                            message = matcher.group(1);
                        }
                        newString += message;
                        System.out.println(newString);

                        newString = "Godzina odczytu: ";
                        pattern = Pattern.compile("^.{2}(.{2})");
                        matcher = pattern.matcher(attributes[x]);
                        while (matcher.find()) {
                            message = matcher.group(1);
                        }
                        newString += message;
                        System.out.println(newString);

                        newString = "Wskaźnik wiatru: ";
                        pattern = Pattern.compile("^.{4}(.{1})");
                        matcher = pattern.matcher(attributes[x]);
                        while (matcher.find()) {
                            message = matcher.group(1);
                        }
                        switch (message) {
                            case "0" -> newString += "prędkość wiatru oszacowano w m/s";
                            case "1" -> newString += "prędkość wiatru zmierzono anemometrem w m/s";
                            case "3" -> newString += "prędkość wiatru oszacowano w węzłach";
                            case "4" -> newString += "prędkość wiatru zmierzono anemometrem w węzłach";
                        }
                        System.out.println(newString);
                    }
                    //attributes1
                    case 1 -> {
                        newString = "Numer blokowy kraju: ";
                        newString += attributes[x].substring(0, 2);
                        System.out.println(newString);
                        newString = "Indywidualny numer stacji: ";
                        newString += attributes[x].substring(2, 5);
                        System.out.println(newString);
                    }
                    //attributes2
                    case 2 -> {
                        newString = "Wskaźnik grupy opadowej: ";
                        message = attributes[x].substring(0, 1);
                        switch (message) {
                            case "0" -> {
                                newString += "grupa opadowa w sekcji 1 i 3";
                            }
                            case "1" -> {
                                newString += "grupa opadowa tylko w sekcji 1";
                            }
                            case "2" -> newString += "grupa opadowa tylko w sekcji 3";
                            case "3" -> newString += "grupa opadowa pominięta (opady nie wystąpiły)";
                            case "4" -> newString += "grupa opadowa pominięta (nie wykonywano pomiarów opadu) - w kluczu SHIP jednocześnie brak grupy 6RRRtR";
                        }
                        System.out.println(newString);
                        newString = "Typ stacji: ";
                        message = attributes[x].substring(1, 2);
                        switch (message) {
                            case "1" -> {
                                newString += "stacja nieautomatyczna, grupa 7wwW1W2 włączona";
                                grupa7wwWW = true;
                            }
                            case "2" -> newString += "stacja nieautomatyczna, grupa 7wwW1W2 opuszczona (brak zjawisk)";
                            case "3" -> newString += "stacja nieautomatyczna, grupa 7wwW1W2 opuszczona (brak danych)";
                            case "4" -> {
                                newString += "stacja automatyczna, grupa 7wwW1W2 włączona kodowana za pomocą WMO Code Table 4677 i 4561";
                                grupa7wwWW = true;
                            }
                            case "5" -> newString += "stacja automatyczna, grupa 7wwW1W2 opuszczona (brak zjawisk)";
                            case "6" -> newString += "stacja automatyczna, grupa 7wwW1W2 opuszczona (brak danych)";
                            case "7" -> {
                                newString += "stacja automatyczna, grupa 7wwW1W2 włączona kodowana za pomocą WMO Code Table 4680 i 4531";
                                grupa7wwWW = true;
                            }
                        }
                        System.out.println(newString);
                        newString = "Wysokość względna podstawy najniższych chmur: ";
                        message = attributes[x].substring(2, 3);
                        switch (message) {
                            case "0" -> newString += "0 do 50 m";
                            case "1" -> newString += "50 do 100 m";
                            case "2" -> newString += "100 do 200 m";
                            case "3" -> newString += "200 do 300 m";
                            case "4" -> newString += "300 do 600 m";
                            case "5" -> newString += "600 do 1000 m";
                            case "6" -> newString += "1000 do 1500 m";
                            case "7" -> newString += "1500 do 2000 m";
                            case "8" -> newString += "2000 do 2500 m";
                            case "9" -> newString += "powyżej 2500 m lub brak chmur";
                            case "/" -> newString += "nieznana";
                        }
                        System.out.println(newString);
                        newString = "Widzialność w kierunku poziomym: ";
                        message = attributes[x].substring(3, 5);
                        switch (message) {
                            case "//" -> newString += "nieznana";
                            case "00" -> newString += "<0,1";
                            case "01" -> newString += "0,1";
                            case "02" -> newString += "0,2";
                            case "03" -> newString += "0,3";
                            case "04" -> newString += "0,4";
                            case "05" -> newString += "0,5";
                            case "06" -> newString += "0,6";
                            case "07" -> newString += "0,7";
                            case "08" -> newString += "0,8";
                            case "09" -> newString += "0,9";
                            case "10" -> newString += "1,0";
                            case "11" -> newString += "1,1";
                            case "12" -> newString += "1,2";
                            case "13" -> newString += "1,3";
                            case "14" -> newString += "1,4";
                            case "15" -> newString += "1,5";
                            case "16" -> newString += "1,6";
                            case "17" -> newString += "1,7";
                            case "18" -> newString += "1,8";
                            case "19" -> newString += "1,9";
                            case "20" -> newString += "2,0";
                            case "21" -> newString += "2,1";
                            case "22" -> newString += "2,2";
                            case "23" -> newString += "2,3";
                            case "24" -> newString += "2,4";
                            case "25" -> newString += "2,5";
                            case "26" -> newString += "2,6";
                            case "27" -> newString += "2,7";
                            case "28" -> newString += "2,8";
                            case "29" -> newString += "2,9";
                            case "30" -> newString += "3,0";
                            case "31" -> newString += "3,1";
                            case "32" -> newString += "3,2";
                            case "33" -> newString += "3,3";
                            case "34" -> newString += "3,4";
                            case "35" -> newString += "3,5";
                            case "36" -> newString += "3,6";
                            case "37" -> newString += "3,7";
                            case "38" -> newString += "3,8";
                            case "39" -> newString += "3,9";
                            case "40" -> newString += "4,0";
                            case "41" -> newString += "4,1";
                            case "42" -> newString += "4,2";
                            case "43" -> newString += "4,3";
                            case "44" -> newString += "4,4";
                            case "45" -> newString += "4,5";
                            case "46" -> newString += "4,6";
                            case "47" -> newString += "4,7";
                            case "48" -> newString += "4,8";
                            case "49" -> newString += "4,9";
                            case "50" -> newString += "5,0";
                            case "56" -> newString += "6";
                            case "57" -> newString += "7";
                            case "58" -> newString += "8";
                            case "59" -> newString += "9";
                            case "60" -> newString += "10";
                            case "61" -> newString += "11";
                            case "62" -> newString += "12";
                            case "63" -> newString += "13";
                            case "64" -> newString += "14";
                            case "65" -> newString += "15";
                            case "66" -> newString += "16";
                            case "67" -> newString += "17";
                            case "68" -> newString += "18";
                            case "69" -> newString += "19";
                            case "70" -> newString += "20";
                            case "71" -> newString += "21";
                            case "72" -> newString += "22";
                            case "73" -> newString += "23";
                            case "74" -> newString += "24";
                            case "75" -> newString += "25";
                            case "76" -> newString += "26";
                            case "77" -> newString += "27";
                            case "78" -> newString += "28";
                            case "79" -> newString += "29";
                            case "80" -> newString += "30";
                            case "81" -> newString += "35";
                            case "82" -> newString += "40";
                            case "83" -> newString += "45";
                            case "84" -> newString += "50";
                            case "85" -> newString += "55";
                            case "86" -> newString += "60";
                            case "87" -> newString += "65";
                            case "88" -> newString += "70";
                            case "89" -> newString += ">70";
                        }
                        System.out.println(newString);
                    }
                    //attributes3
                    case 3 -> {
                        newString = "Wielkość zachmurzenia ogólnego i dane o wietrze: ";
                        message = attributes[x].substring(0, 1);
                        switch (message) {
                            case "0" -> newString += "niebo bezchmurne";
                            case "1" -> newString += "1/8 lub mniej";
                            case "2" -> newString += "2/8";
                            case "3" -> newString += "3/8";
                            case "4" -> newString += "4/8";
                            case "5" -> newString += "5/8";
                            case "6" -> newString += "6/8";
                            case "7" -> newString += "7/8";
                            case "8" -> newString += "8/8";
                            case "9" -> newString += "niebo niewidoczne (zasłonięte mgłą lub innymi zjawiskami meteorologicznymi)";
                            case "/" -> newString += "chmury niewidoczne z powodów innych niż mgła czy inne zjawiska meteorologiczne lub nie prowadzi się obserwacji ";
                        }
                        System.out.println(newString);
                        newString = "Średni (z 10 minut) kierunek wiatru rzeczywistego: ";
                        message = attributes[x].substring(1, 3);
                        switch (message) {
                            case "99" -> newString += "wiatr zmienny";
                            case "00" -> newString += "cisza";
                            case "01" -> newString += "005-014°  N";
                            case "02" -> newString += "015-024°  NNE";
                            case "03" -> newString += "025-034°  NNE";
                            case "04" -> newString += "035-044°  NE";
                            case "05" -> newString += "045-054°  NE";
                            case "06" -> newString += "055-064°  ENE";
                            case "07" -> newString += "065-074°  ENE";
                            case "08" -> newString += "075-084°  ENE";
                            case "09" -> newString += "085-094°  E";
                            case "10" -> newString += "095-104°  E";
                            case "11" -> newString += "105-114°  ESE";
                            case "12" -> newString += "115-124°  ESE";
                            case "13" -> newString += "125-134°  SE";
                            case "14" -> newString += "135-144°  SE";
                            case "15" -> newString += "145-154°  SSE";
                            case "16" -> newString += "155-164°  SSE";
                            case "17" -> newString += "165-174°  SSE";
                            case "18" -> newString += "175-184°  S";
                            case "19" -> newString += "185-194°  S";
                            case "20" -> newString += "195-204°  SSW";
                            case "21" -> newString += "205-214°  SSW";
                            case "22" -> newString += "215-224°  SW";
                            case "23" -> newString += "225-234°  SW";
                            case "24" -> newString += "235-244°  WSW";
                            case "25" -> newString += "245-254°  WSW";
                            case "26" -> newString += "255-264°  WSW";
                            case "27" -> newString += "265-274°  W";
                            case "28" -> newString += "275-284°  W";
                            case "29" -> newString += "285- 294°  WNW";
                            case "30" -> newString += "295-304°  WNW";
                            case "31" -> newString += "305-314°  NW";
                            case "32" -> newString += "315-324°  NW";
                            case "33" -> newString += "325-334°  NNW";
                            case "34" -> newString += "335-344°  NNW";
                            case "35" -> newString += "345-354°  N";
                            case "36" -> newString += "355-004°  N";
                        }
                        System.out.println(newString);
                        newString = "Prędkość wiatru rzeczywistego wyrażona w węzłach: ";
                        message = attributes[x].substring(3, 5);
                        if ("99".equals(message)) {
                            x += 1;
                            newString += attributes[x].substring(2, 5);
                        } else {
                            newString += message;
                        }
                        System.out.println(newString);
                    }
                    default -> {
                        switch (attributes[x].charAt(0)) {
                            //attributes4
                            case '1' -> {
                                newString = "Temperatura powietrza: ";
                                if (attributes[x].charAt(1) == '1')
                                    newString += "-";
                                newString += attributes[x].substring(2, 4) + "," + attributes[x].substring(4, 5);
                                System.out.println(newString);
                            }
                            //attributes5
                            case '2' -> {
                                newString = "Temperatura punktu rosy: ";
                                if (attributes[x].charAt(1) == '1')
                                    newString += "-";
                                newString += attributes[x].substring(2, 4) + "," + attributes[x].substring(4, 5);
                                System.out.println(newString);
                            }
                            //attributes6
                            case '3' -> {
                                newString = "Ciśnienie atmosferyczne na poziomie stacji: ";
                                message = attributes[x].substring(3, 5);
                                newString += attributes[x].substring(1, 4) + "," + attributes[x].substring(4, 5);
                                System.out.println(newString);
                            }
                            //attributes7
                            case '4' -> {
                                newString = "Ciśnienie atmosferyczne na poziomie morza: ";
                                message = attributes[x].substring(3, 5);
                                newString += attributes[x].substring(1, 4) + "," + attributes[x].substring(4, 5);
                                System.out.println(newString);
                                System.out.println("Jeżeli ciśnienie na stacji wynosi 1000,0 hPa lub więcej pomija się cyfrę tysięcy");
                            }
                            //attributes8
                            case '5' -> {
                                newString = "Tendencja ciśnienia atmosferycznego: ";
                                message = attributes[x].substring(1, 2);
                                switch (message) {
                                    case "0" -> newString += "wzrost, potem spadek; ciśnienie jest wyższe lub takie samo jak przed trzema godzinami";
                                    case "1" -> newString += "wzrost potem stan stały; lub wzrost, potem wzrost wolniejszy; ciśnienie jest wyższe niż przed trzema godzinami ";
                                    case "2" -> newString += "wzrost równomierny lub nierównomierny; ciśnienie jest wyższe niż przed trzema godzinami";
                                    case "3" -> newString += "spadek, potem wzrost; lub stan stały, potem wzrost; lub wzrost, potem wzrost słabszy; ciśnienie jest wyższe niż przed trzema godzinami";
                                    case "4" -> newString += "stan stały; ciśnienie jest takie samo jak przed trzema godzinami";
                                    case "5" -> newString += "spadek, potem wzrost; ciśnienie jest takie samo lub niższe niż przed trzema godzinami";
                                    case "6" -> newString += "spadek, potem stan stały; lub spadek, potem spadek wolniejszy; ciśnienie jest niższe niż przed trzema godzinami";
                                    case "7" -> newString += "spadek równomierny lub nierównomierny; ciśnienie jest niższe niż przed trzema godzinami";
                                    case "8" -> newString += "wzrost, potem spadek; lub stan stały, potem spadek; lub spadek, potem spadek szybszy; ciśnienie jest niższe niż przed trzema godzinami";
                                }
                                System.out.println(newString);
                                System.out.println("Bezwzględna wartość tendencji ciśnienia atmosferycznego za okres trzech godzin poprzedzających obserwację: " + attributes[x].substring(2, 4) + "," + attributes[x].substring(4, 5));
                            }
                            case '6' -> {
                                newString = "Suma opadu: ";
                                message = attributes[x].substring(1, 4);
                                switch (message) {
                                    case "989" -> newString += "989 mm lub więcej";
                                    case "990" -> newString += "ślad opadu";
                                    case "991" -> newString += "0,1 mm";
                                    case "992" -> newString += "0,2 mm";
                                    case "993" -> newString += "0,3 mm";
                                    case "994" -> newString += "0,4 mm";
                                    case "995" -> newString += "0,5 mm";
                                    case "996" -> newString += "0,6 mm";
                                    case "997" -> newString += "0,7 mm";
                                    case "998" -> newString += "0,8 mm";
                                    case "999" -> newString += "0,9 mm";
                                    case "///" -> newString += "opadu nie zmierzono";
                                    default -> newString += message + " mm";
                                }
                                message = attributes[x].substring(4, 5);
                                switch (message) {
                                    case "1" -> newString += "całkowite opady w ciągu 6 godzin poprzedzających obserwację";
                                    case "2" -> newString += "całkowite opady w ciągu 12 godzin poprzedzających obserwację";
                                    case "3" -> newString += "całkowite opady w ciągu 18 godzin poprzedzających obserwację";
                                    case "4" -> newString += "całkowite opady w ciągu 24 godzin poprzedzających obserwację";
                                    case "5" -> newString += "całkowite opady w ciągu 1 godzin poprzedzających obserwację";
                                    case "6" -> newString += "całkowite opady w ciągu 2 godzin poprzedzających obserwację";
                                    case "7" -> newString += "całkowite opady w ciągu 3 godzin poprzedzających obserwację";
                                    case "8" -> newString += "całkowite opady w ciągu 9 godzin poprzedzających obserwację";
                                    case "9" -> newString += "całkowite opady w ciągu 15 godzin poprzedzających obserwację";

                                }
                                System.out.println(newString);
                            }
                            case '7' -> {
                                if (grupa7wwWW) {
                                    newString = "Stan pogody bieżącej: ";
                                    message = attributes[x].substring(1, 3);
                                    switch (message) {
                                        case "00" -> newString += "rozwój chmur nie był obserwowany lub nie był możliwy do obserwowania (podczas ostatniej godziny)";
                                        case "01" -> newString += "chmury na ogół zanikają lub stają się cieńsze (podczas ostatniej godziny)";
                                        case "02" -> newString += "stan nieba na ogól bez zmian (podczas ostatniej godziny)";
                                        case "03" -> newString += "chmury w stadium tworzenia się lub rozwoju (podczas ostatniej godziny)";
                                        case "04" -> newString += "widzialność zmniejszona przez dymy (z pożarów lasów, stepów, dymy pochodzenia fabrycznego lub pyły wulkaniczne)";
                                        case "05" -> newString += "zmętnienie (zmniejszenie widzialności spowodowane głównie przez litometeory)";
                                        case "06" -> newString += "pył unoszony w powietrzu na znacznych przestrzeniach (nie wzniesiony przez wiatr w czasie obserwacji w miejscu dokonywania obserwacji ani w jego pobliżu)";
                                        case "07" -> newString += "pył lub piasek w powietrzu wznoszony przez wiatr w czasie obserwacji w miejscu dokonywania obserwacji, bez wyraźnych wirów pyłowych lub piaskowych lub wichury pyłowej czy piaskowej";
                                        case "08" -> newString += "silnie rozwinięte wiry pyłowe lub piaskowe w miejscu obserwacji lub w pobliżu, w czasie obserwacji lub w ciągu ostatniej godziny, lecz nie wichura pyłowa lub piaskowa";
                                        case "09" -> newString += "wichura pyłowa lub piaskowa w zasięgu widzenia w czasie obserwacji lub w miejscu wykonywania obserwacji w ciągu ostatniej godziny";
                                        case "10" -> newString += "zamglenie (widzialność zmniejszają tylko kropelki wody lub kryształki lodu, widzialność wynosi 1000 m lub więcej)";
                                        case "11" -> newString += "niska mgła lub mgła lodowa w płatach (w miejscu obserwacji do 2 m wysokości na lądzie, do 10 m wysokości na morzu, widzialność mniejsza niż 1000 m)";
                                        case "12" -> newString += "mniej lub bardziej ciągła warstwa niskiej mgły lub mgły lodowej (w miejscu obserwacji do 2 m wysokości na lądzie, do 10 m wysokości na morzu, widzialność mniejsza niż 1000 m)";
                                        case "13" -> newString += "błyskawica widoczna, lecz nie słychać grzmotu";
                                        case "14" -> newString += "opad w polu widzenia nie sięgający gruntu lub powierzchni morza";
                                        case "15" -> newString += "opad w polu widzenia sięgający gruntu lub powierzchni morza, w większej odległości od miejsca obserwacji (ponad 5 km)";
                                        case "16" -> newString += "opad w polu widzenia sięgający gruntu lub powierzchni morza, w pobliżu lecz nie w samym miejscu obserwacji";
                                        case "17" -> newString += "burza bez opadu w czasie obserwacji (ostatni grzmot słyszany mniej niż w ciągu ostatnich 15 minut przed obserwacją)";
                                        case "18" -> newString += "nawałnica w polu widzenia lub w miejscu obserwacji w czasie obserwacji lub w ciągu ostatniej godziny (gdy prędkość wiatru nagle wzrasta o co najmniej 8 m/s przy jednoczesnym osiągnięciu co najmniej 11 m/s (o 3 przedziały skali Beauforta) i utrzymywaniu się tej prędkości przez co najmniej 1 minutę)";
                                        case "19" -> newString += "trąba (wodna lub tornado) w polu widzenia lub miejscu obserwacji w ciągu ostatniej godziny lub w czasie obserwacji";
                                        case "20" -> newString += "mżawka (nie marznąca) lub śnieg ziarnisty (bez cech opadu przelotnego)";
                                        case "21" -> newString += "deszcz (nie marznący, bez cech opadu przelotnego)";
                                        case "22" -> newString += "śnieg (bez cech opadu przelotnego)";
                                        case "23" -> newString += "deszcz ze śniegiem lub ziarna lodowe (deszcz lodowy, bez cech opadu przelotnego)";
                                        case "24" -> newString += "mżawka marznąca lub deszcz marznący (bez cech opadu przelotnego)";
                                        case "25" -> newString += "przelotny deszcz";
                                        case "26" -> newString += "przelotny śnieg lub przelotny deszcz ze śniegiem";
                                        case "27" -> newString += "przelotny grad lub deszcz z gradem lub krupy śnieżne lub krupy lodowe";
                                        case "28" -> newString += "mgła lub mgła lodowa (widzialność poniżej 1000 m)";
                                        case "29" -> newString += "burza bez opadu lub z opadem (jeżeli ostatni grzmot słyszany wcześniej niż 15 minut przed obserwacją)";
                                        case "30" -> newString += "słaba lub umiarkowana wichura pyłowa lub piaskowa, słabnąca w ciągu ostatniej godziny";
                                        case "31" -> newString += "słaba lub umiarkowana wichura pyłowa lub piaskowa, bez dostrzegalnych zmian w ciągu ostatniej godziny";
                                        case "32" -> newString += "słaba lub umiarkowana wichura pyłowa lub piaskowa, zaczęła się lub wzmogła w ciągu ostatniej godziny";
                                        case "33" -> newString += "silna wichura pyłowa lub piaskowa, słabnąca w ciągu ostatniej godziny";
                                        case "34" -> newString += "silna wichura pyłowa lub piaskowa, bez zmian w ciągu ostatniej godziny";
                                        case "35" -> newString += "silna wichura pyłowa lub piaskowa, zaczęła się lub wzmogła w ciągu ostatniej godziny";
                                        case "36" -> newString += "niska zamieć śnieżna, słaba lub umiarkowana (na ogół poniżej poziomu oczu obserwatora, bez opadów śniegu)";
                                        case "37" -> newString += "niska zamieć śnieżna, silna (na ogół poniżej poziomu oczu obserwatora, bez opadów śniegu, gwałtowne narastanie zasp)";
                                        case "38" -> newString += "wysoka zamieć śnieżna, słaba lub umiarkowana (na ogół powyżej poziomu oczu obserwatora, bez opadów śniegu)";
                                        case "39" -> newString += "wysoka zamieć śnieżna, silna (na ogół powyżej poziomu oczu obserwatora, bez opadów śniegu, gwałtowne narastanie zasp)";
                                        case "40" -> newString += "mgła (mgła lodowa) sięgająca powyżej poziomu oczu obserwatora, w pewnej odległości od miejsca obserwacji w czasie obserwacji, w ciągu ostatniej godziny mgły w miejscu obserwacji nie było (w miejscu obserwacji widzialność większa od 1000 m)";
                                        case "41" -> newString += "mgła (mgła lodowa) w płatach";
                                        case "42" -> newString += "mgła (mgła lodowa), niebo widoczne, stała się rzadsza w ciągu ostatniej godziny";
                                        case "43" -> newString += "mgła (mgła lodowa), niebo niewidoczne, staje się rzadsza, stała się rzadsza w ciągu ostatniej godziny";
                                        case "44" -> newString += "mgła (mgła lodowa), niebo widoczne, bez dostrzegalnych zmian w ciągu ostatniej godziny";
                                        case "45" -> newString += "mgła (mgła lodowa), niebo niewidoczne, bez dostrzegalnych zmian w ciągu ostatniej godziny";
                                        case "46" -> newString += "mgła (mgła lodowa), niebo widoczne, zaczęła się tworzyć lub gęstnieje w ciągu ostatniej godziny";
                                        case "47" -> newString += "mgła (mgła lodowa), niebo niewidoczne, zaczęła się tworzyć lub gęstnieje w ciągu ostatniej godziny";
                                        case "48" -> newString += "mgła osadzająca szadź, niebo widoczne (głównym czynnikiem osłabiającym widzialność są kropelki wody)";
                                        case "49" -> newString += "mgła osadzająca szadź, niebo niewidoczne (głównym czynnikiem osłabiającym widzialność są kropelki wody)";
                                        case "50" -> newString += "mżawka nie marznąca, z przerwami, słaba w czasie obserwacji";
                                        case "51" -> newString += "mżawka nie marznąca, ciągła, słaba w czasie obserwacji";
                                        case "52" -> newString += "mżawka nie marznąca, z przerwami, umiarkowana w czasie obserwacji";
                                        case "53" -> newString += "mżawka nie marznąca, ciągła, umiarkowana w czasie obserwacji";
                                        case "54" -> newString += "mżawka nie marznąca, z przerwami, silna w czasie obserwacji";
                                        case "55" -> newString += "mżawka nie marznąca, ciągła, silna w czasie obserwacji";
                                        case "56" -> newString += "mżawka marznąca, słaba";
                                        case "57" -> newString += "mżawka marznąca, umiarkowana lub silna";
                                        case "58" -> newString += "mżawka z deszczem, słaba";
                                        case "59" -> newString += "mżawka z deszczem, umiarkowana lub silna";
                                        case "60" -> newString += "deszcz nie marznący, z przerwami, słaby w czasie obserwacji";
                                        case "61" -> newString += "deszcz nie marznący, ciągły, słaby w czasie obserwacji";
                                        case "62" -> newString += "deszcz nie marznący, z przerwami, umiarkowany w czasie obserwacji";
                                        case "63" -> newString += "deszcz nie marznący, ciągły, umiarkowany w czasie obserwacji";
                                        case "64" -> newString += "deszcz nie marznący, z przerwami, silny w czasie obserwacji";
                                        case "65" -> newString += "deszcz nie marznący, ciągły, silny w czasie obserwacji";
                                        case "66" -> newString += "deszcz marznący, słaby";
                                        case "67" -> newString += "deszcz marznący, umiarkowany lub silny";
                                        case "68" -> newString += "deszcz lub mżawka ze śniegiem, słaby";
                                        case "69" -> newString += "deszcz lub mżawka ze śniegiem, umiarkowany lub silny";
                                        case "70" -> newString += "śnieg z przerwami, słaby w czasie obserwacji";
                                        case "71" -> newString += "śnieg ciągły, słaby w czasie obserwacji";
                                        case "72" -> newString += "śnieg z przerwami, umiarkowany w czasie obserwacji";
                                        case "73" -> newString += "śnieg ciągły, umiarkowany w czasie obserwacji";
                                        case "74" -> newString += "śnieg z przerwami, silny w czasie obserwacji";
                                        case "75" -> newString += "śnieg ciągły, silny w czasie obserwacji";
                                        case "76" -> newString += "pył diamentowy (słupki lodowe), z mgłą lub bez mgły";
                                        case "77" -> newString += "śnieg ziarnisty, z mgłą lub bez mgły";
                                        case "78" -> newString += "oddzielne gwiazdki śniegu, z mgłą lub bez mgły";
                                        case "79" -> newString += "ziarna lodowe (deszcz lodowy)";
                                        case "80" -> newString += "przelotny deszcz, słaby";
                                        case "81" -> newString += "przelotny deszcz, umiarkowany lub silny";
                                        case "82" -> newString += "przelotny deszcz, gwałtowny";
                                        case "83" -> newString += "przelotny deszcz ze śniegiem, słaby";
                                        case "84" -> newString += "przelotny deszcz ze śniegiem, umiarkowany lub silny";
                                        case "85" -> newString += "przelotny śnieg, słaby";
                                        case "86" -> newString += "przelotny śnieg, umiarkowany lub silny";
                                        case "87" -> newString += "przelotne krupy lodowe lub śnieżne, same lub z deszczem, względnie z deszczem i śniegiem, słabe";
                                        case "88" -> newString += "przelotne krupy lodowe lub śnieżne, same lub z deszczem, względnie z deszczem i śniegiem, umiarkowane lub silne";
                                        case "89" -> newString += "przelotny grad sam lub z deszczem, względnie deszczem i śniegiem, lecz bez grzmotu, słaby";
                                        case "90" -> newString += "przelotny grad sam lub z deszczem, względnie deszczem i śniegiem, lecz bez grzmotu, umiarkowany lub silny";
                                        case "91" -> newString += "słaby deszcz w czasie obserwacji, burza w ciągu ostatniej godziny, lecz nie w czasie obserwacji";
                                        case "92" -> newString += "umiarkowany lub silny deszcz w czasie obserwacji, burza w ciągu ostatniej godziny, lecz nie w czasie obserwacji";
                                        case "93" -> newString += "słaby śnieg lub deszcz ze śniegiem w czasie obserwacji, burza w ciągu ostatniej godziny, lecz nie w czasie obserwacji";
                                        case "94" -> newString += "umiarkowany lub silny śnieg lub deszcz ze śniegiem w czasie obserwacji, burza w ciągu ostatniej godziny, lecz nie w czasie obserwacji";
                                        case "95" -> newString += "słaba lub umiarkowana burza w czasie obserwacji, bez gradu, lecz deszczem lub śniegiem względnie z deszczem i śniegiem";
                                        case "96" -> newString += "słaba lub umiarkowana burza z gradem w czasie obserwacji";
                                        case "97" -> newString += "silna burza w czasie obserwacji, bez gradu, lecz deszczem lub śniegiem względnie z deszczem i śniegiem";
                                        case "98" -> newString += "burza połączona z wichurą pyłową lub piaskową w czasie obserwacji";
                                        case "99" -> newString += "silna burza z gradem w czasie obserwacji";
                                    }
                                    System.out.println(newString);

                                    newString = "Stan pogody ubiegłej: ";
                                    if (attributes[x].charAt(3) == attributes[x].charAt(4)) {
                                        switch (attributes[x].charAt(3)) {
                                            case '0' -> newString += "chmury pokrywały połowę lub mniej niż połowę nieba w ciągu branego pod uwagę okresu";
                                            case '1' -> newString += "chmury pokrywały ponad połowę nieba przez część okresu i mniej niż połowę przez pozostałą część okresu";
                                            case '2' -> newString += "chmury pokrywały ponad połowę nieba w ciągu branego pod uwagę okresu";
                                            case '3' -> newString += "wichura pyłowa, wichura piaskowa lub zamieć śnieżna wysoka";
                                            case '4' -> newString += "mgła, mgła lodowa lub gęste zmętnienie (widzialność poniżej 1000 m)";
                                            case '5' -> newString += "mżawka";
                                            case '6' -> newString += "deszcz ciągły";
                                            case '7' -> newString += "śnieg lub śnieg z deszczem lub ziarna lodowe";
                                            case '8' -> newString += "opady przelotne";
                                            case '9' -> newString += "burza z opadem lub bez opadu";
                                            case '/' -> newString += "brak danych";
                                        }
                                    } else {
                                        switch (attributes[x].charAt(3)) {
                                            case '0' -> newString += "chmury pokrywały połowę lub mniej niż połowę nieba w ciągu branego pod uwagę okresu";
                                            case '1' -> newString += "chmury pokrywały ponad połowę nieba przez część okresu i mniej niż połowę przez pozostałą część okresu";
                                            case '2' -> newString += "chmury pokrywały ponad połowę nieba w ciągu branego pod uwagę okresu";
                                            case '3' -> newString += "wichura pyłowa, wichura piaskowa lub zamieć śnieżna wysoka";
                                            case '4' -> newString += "mgła, mgła lodowa lub gęste zmętnienie (widzialność poniżej 1000 m)";
                                            case '5' -> newString += "mżawka";
                                            case '6' -> newString += "deszcz ciągły";
                                            case '7' -> newString += "śnieg lub śnieg z deszczem lub ziarna lodowe";
                                            case '8' -> newString += "opady przelotne";
                                            case '9' -> newString += "burza z opadem lub bez opadu";
                                            case '/' -> newString += "brak danych";
                                        }
                                        newString += "; ";
                                        switch (attributes[x].charAt(4)) {
                                            case '0' -> newString += "chmury pokrywały połowę lub mniej niż połowę nieba w ciągu branego pod uwagę okresu";
                                            case '1' -> newString += "chmury pokrywały ponad połowę nieba przez część okresu i mniej niż połowę przez pozostałą część okresu";
                                            case '2' -> newString += "chmury pokrywały ponad połowę nieba w ciągu branego pod uwagę okresu";
                                            case '3' -> newString += "wichura pyłowa, wichura piaskowa lub zamieć śnieżna wysoka";
                                            case '4' -> newString += "mgła, mgła lodowa lub gęste zmętnienie (widzialność poniżej 1000 m)";
                                            case '5' -> newString += "mżawka";
                                            case '6' -> newString += "deszcz ciągły";
                                            case '7' -> newString += "śnieg lub śnieg z deszczem lub ziarna lodowe";
                                            case '8' -> newString += "opady przelotne";
                                            case '9' -> newString += "burza z opadem lub bez opadu";
                                            case '/' -> newString += "brak danych";
                                        }
                                        System.out.println(newString);
                                    }
                                } else {
                                    newString = "Stan pogody bieżącej: ";
                                    message = attributes[x].substring(1, 3);
                                    switch (message) {
                                        case "00" -> newString += "nie występują żadne znaczące zjawiska pogodowe";
                                        case "01" -> newString += "chmury na ogół zanikają lub stają się cieńsze (podczas ostatniej godziny)";
                                        case "02" -> newString += "stan nieba na ogól bez zmian (podczas ostatniej godziny)";
                                        case "03" -> newString += "chmury w stadium tworzenia się lub rozwoju (podczas ostatniej godziny)";
                                        case "04" -> newString += "dym, zmętnienie lub pył w powietrzu, widzialność równa lub większa od 1 km";
                                        case "05" -> newString += "dym, zmętnienie lub pył w powietrzu, widzialność mniejsza niż 1 km";
                                        case "10" -> newString += "zamglenie";
                                        case "11" -> newString += "pył diamentowy";
                                        case "12" -> newString += "błyskawica odległa";
                                        case "18" -> newString += "nawałnica";
                                        case "20" -> newString += "mgła, w miejscu obserwacji w ciągu ostatniej godziny, lecz nie w czasie dokonywania obserwacji";
                                        case "21" -> newString += "OPAD, w miejscu obserwacji w ciągu ostatniej godziny, lecz nie w czasie dokonywania obserwacji";
                                        case "22" -> newString += "mżawka lub śnieg ziarnisty, w miejscu obserwacji w ciągu ostatniej godziny, lecz nie w czasie dokonywania obserwacji";
                                        case "23" -> newString += "deszcz (niemarznący), w miejscu obserwacji w ciągu ostatniej godziny, lecz nie w czasie dokonywania obserwacji";
                                        case "24" -> newString += "śnieg, w miejscu obserwacji w ciągu ostatniej godziny, lecz nie w czasie dokonywania obserwacji";
                                        case "25" -> newString += "marznący deszcz lub mżawka, w miejscu obserwacji w ciągu ostatniej godziny, lecz nie w czasie dokonywania obserwacji";
                                        case "26" -> newString += "burza (z lub bez opadu), w miejscu obserwacji w ciągu ostatniej godziny, lecz nie w czasie dokonywania obserwacji";
                                        case "27" -> newString += "NISKA ZAMIEĆ ŚNIEŻNA LUB WICHURA PIASKOWA";
                                        case "28" -> newString += "niska zamieć śnieżna lub wichura piaskowa, widzialność 1 km lub więcej";
                                        case "29" -> newString += "niska zamieć śnieżna lub wichura piaskowa, widzialność poniżej 1 km";
                                        case "30" -> newString += "MGŁA";
                                        case "31" -> newString += "mgła lub mgła lodowa w płatach";
                                        case "32" -> newString += "mgła lub mgła lodowa, rzedniejąca w ciągu ostatniej godziny";
                                        case "33" -> newString += "mgła lub mgła lodowa, bez dostrzegalnych zmian w ciągu ostatniej godziny";
                                        case "34" -> newString += "mgła lub mgła lodowa, gęstniejąca w ciągu ostatniej godziny";
                                        case "35" -> newString += "mgła osadzająca szadź";
                                        case "40" -> newString += "OPAD";
                                        case "41" -> newString += "opad, słaby lub umiarkowany";
                                        case "42" -> newString += "opad, silny";
                                        case "43" -> newString += "opad ciekły, słaby lub umiarkowany";
                                        case "44" -> newString += "opad ciekły, silny";
                                        case "45" -> newString += "opad w postaci stałej, słaby lub umiarkowany";
                                        case "46" -> newString += "opad w postaci stałej, silny";
                                        case "47" -> newString += "opad marznący, słaby lub umiarkowany";
                                        case "48" -> newString += "opad marznący, silny";
                                        case "50" -> newString += "MŻAWKA";
                                        case "51" -> newString += "mżawka nie marznąca, słaba";
                                        case "52" -> newString += "mżawka nie marznąca, umiarkowana";
                                        case "53" -> newString += "mżawka nie marznąca, silna";
                                        case "54" -> newString += "mżawka marznąca, słaba";
                                        case "55" -> newString += "mżawka marznąca, umiarkowana";
                                        case "56" -> newString += "mżawka marznąca, silna";
                                        case "57" -> newString += "mżawka z deszczem, słaba";
                                        case "58" -> newString += "mżawka z deszczem, umiarkowana lub silna";
                                        case "60" -> newString += "DESZCZ";
                                        case "61" -> newString += "deszcz nie marznący, słaby";
                                        case "62" -> newString += "deszcz nie marznący, umiarkowany";
                                        case "63" -> newString += "deszcz nie marznący, silny";
                                        case "64" -> newString += "deszcz marznący, słaby";
                                        case "65" -> newString += "deszcz marznący, umiarkowany";
                                        case "66" -> newString += "deszcz marznący, silny";
                                        case "67" -> newString += "deszcz (lub mżawka) ze śniegiem, słaby";
                                        case "68" -> newString += "deszcz (lub mżawka) ze śniegiem, umiarkowany lub silny";
                                        case "70" -> newString += "ŚNIEG";
                                        case "71" -> newString += "śnieg, słaby";
                                        case "72" -> newString += "śnieg, umiarkowany";
                                        case "73" -> newString += "śnieg silny";
                                        case "74" -> newString += "ziarna lodowe, słabe";
                                        case "75" -> newString += "ziarna lodowe, umiarkowane";
                                        case "76" -> newString += "ziarna lodowe, silne";
                                        case "77" -> newString += "śnieg ziarnisty";
                                        case "78" -> newString += "kryształki lodowe";
                                        case "80" -> newString += "OPADY PRZELOTNE LUB OPADY Z PRZERWAMI";
                                        case "81" -> newString += "przelotny deszcz lub deszcz z przerwami, słaby";
                                        case "82" -> newString += "przelotny deszcz lub deszcz z przerwami, umiarkowany";
                                        case "83" -> newString += "przelotny deszcz lub deszcz z przerwami, silny";
                                        case "84" -> newString += "przelotny deszcz lub deszcz z przerwami, gwałtowny";
                                        case "85" -> newString += "przelotny śnieg lub śnieg z przerwami, słaby";
                                        case "86" -> newString += "przelotny śnieg lub śnieg z przerwami, umiarkowany";
                                        case "87" -> newString += "przelotny śnieg lub śnieg z przerwami, silny";
                                        case "89" -> newString += "grad";
                                        case "90" -> newString += "BURZA";
                                        case "91" -> newString += "burza słaba lub umiarkowana, bez opadu";
                                        case "92" -> newString += "burza słaba lub umiarkowana, z przelotnym deszczem lub śniegiem";
                                        case "93" -> newString += "burza słaba lub umiarkowana, z gradem";
                                        case "94" -> newString += "burza silna, bez opadu";
                                        case "95" -> newString += "burza silna, z przelotnym deszczem i/lub śniegiem";
                                        case "96" -> newString += "burza silna, z gradem";
                                        case "99" -> newString += "trąba (tornado)";
                                    }
                                    System.out.println(newString);

                                    newString = "Stan pogody ubiegłej: ";
                                    if (attributes[x].charAt(3) == attributes[x].charAt(4)) {
                                        switch (attributes[x].charAt(3)) {
                                            case '0' -> newString += "bez istotnych zjawisk atmosferycznych";
                                            case '1' -> newString += "OGRANICZONA WIDZIALNOŚĆ";
                                            case '2' -> newString += "zamieć lub wichura piaskowa, ograniczona widzialność";
                                            case '3' -> newString += "MGŁA";
                                            case '4' -> newString += "OPAD";
                                            case '5' -> newString += "mżawka";
                                            case '6' -> newString += "deszcz";
                                            case '7' -> newString += "śnieg lub krupa śnieżna albo lodowa";
                                            case '8' -> newString += "opad przelotny lub opad z przerwami";
                                            case '9' -> newString += "burza";
                                            case '/' -> newString += "brak danych";
                                        }
                                    } else {
                                        switch (attributes[x].charAt(3)) {
                                            case '0' -> newString += "bez istotnych zjawisk atmosferycznych";
                                            case '1' -> newString += "OGRANICZONA WIDZIALNOŚĆ";
                                            case '2' -> newString += "zamieć lub wichura piaskowa, ograniczona widzialność";
                                            case '3' -> newString += "MGŁA";
                                            case '4' -> newString += "OPAD";
                                            case '5' -> newString += "mżawka";
                                            case '6' -> newString += "deszcz";
                                            case '7' -> newString += "śnieg lub krupa śnieżna albo lodowa";
                                            case '8' -> newString += "opad przelotny lub opad z przerwami";
                                            case '9' -> newString += "burza";
                                            case '/' -> newString += "brak danych";
                                        }
                                        newString += "; ";
                                        switch (attributes[x].charAt(4)) {
                                            case '0' -> newString += "bez istotnych zjawisk atmosferycznych";
                                            case '1' -> newString += "OGRANICZONA WIDZIALNOŚĆ";
                                            case '2' -> newString += "zamieć lub wichura piaskowa, ograniczona widzialność";
                                            case '3' -> newString += "MGŁA";
                                            case '4' -> newString += "OPAD";
                                            case '5' -> newString += "mżawka";
                                            case '6' -> newString += "deszcz";
                                            case '7' -> newString += "śnieg lub krupa śnieżna albo lodowa";
                                            case '8' -> newString += "opad przelotny lub opad z przerwami";
                                            case '9' -> newString += "burza";
                                            case '/' -> newString += "brak danych";
                                        }
                                        System.out.println(newString);
                                    }
                                }
                            }
                            case '8' -> {
                                System.out.println("Wielkość zachmurzenia: " + attributes[x].charAt(1));
                                newString = "Chmury Stratocumulus, Stratus, Cumulus i Cumulonimbus: ";
                                switch (attributes[x].charAt(2)){
                                    case '0' -> newString += "brak chmur";
                                    case '1' -> newString += "Cumulus humilis lub Cumulus fractus, lecz nie złej pogody, lub obie chmury razem";
                                    case '2' -> newString += "Cumulus mediocris lub congestus występujący sam lub z Cu hum lub Cu fra bądź też ze Stratocumulus; szystkie chmury mają podstawy na tym samym poziomie";
                                    case '3' -> newString += "Cumulonimbus calvus (sam lub z Cu, Sc lub St) ";
                                    case '4' -> newString += "Stratocumulus cumulogenitus";
                                    case '5' -> newString += "Stratocumulus (lecz nie cumulogenitus)";
                                    case '6' -> newString += "Stratus nebulosus lub Stratus fractus (lecz nie złej pogody) lub obie chmury razem";
                                    case '7' -> newString += "Stratus fractus lub Cumulus fractus (złej pogody) lub obie chmury razem (pannus), zwykle pod Altostratus lub Nimbostratus";
                                    case '8' -> newString += "Cumulus i Stratocumulus (lecz nie Stratocumulus cumulogenitus) o podstawach na różnych poziomach";
                                    case '9' -> newString += "Cumulonimbus capillatus (często z kowadłem) występujący sam lub z Cumulonimbus calvus, Cumulus, Stratocumulus, Stratus lub pannus";
                                    case '/' -> newString += "chmury CL są niewidoczne z powodu ciemności, mgły, wysokiej zamieci pyłowej lub piaskowej lub też innych podobnych zjawisk";
                                }
                                System.out.println(newString);
                                newString = "Chmury Altocumulus, Altostratus i Nimbostratus: ";
                                switch (attributes[x].charAt(3)){
                                    case '0' -> newString += "brak chmur";
                                    case '1' -> newString += "Altostratus translucidus";
                                    case '2' -> newString += "Altostratus opacus lub Nimbostratus";
                                    case '3' -> newString += "Altocumulus translucidus na jednym poziomie";
                                    case '4' -> newString += "ławice Altocumulus translucidus, często soczewkowate, ciągle zmieniające się i występujące na jednym lub kilku poziomach";
                                    case '5' -> newString += "Altocumulus translucidus w pasmach, albo jedna lub więcej warstw Altocumulus translucidus lub opacus, stopniowo zaciągająca niebo; te chmury Ac na ogół w całości grubieją";
                                    case '6' -> newString += "Altocumulus cumulogenitus (lub cumulonimbogenitus)";
                                    case '7' -> newString += "Altocumulus translucidus lub opacus w dwóch lub więcej warstwach, lub Altocumulus opacus w pojedynczej warstwie, nie zaciągający nieba lub Altocumulus z Altostratus lub Nimbostratus";
                                    case '8' -> newString += "Altocumulus castellanus lub floccus";
                                    case '9' -> newString += "Altocumulus na niebie o wyglądzie chaotycznym, na ogół na kilku poziomach";
                                    case '/' -> newString += "chmury CM są niewidoczne wskutek ciemności, mgły, wysokiej zamieci pyłowej lub piaskowej, albo z powodu ciągłej warstwy chmur niższych";
                                }
                                System.out.println(newString);
                                newString = "Chmury Cirrus, Cirrocumulus i Cirrostratus: ";
                                switch (attributes[x].charAt(4)){
                                    case '0' -> newString += "brak chmur";
                                    case '1' -> newString += "Cirrus fibratus lub Cirrus uncinus (nie zaciągający nieba) ";
                                    case '2' -> newString += "Cirrus spissatus w ławicach lub w postaci poplątanych wiązek, albo Cirrus castellanus lub floccus";
                                    case '3' -> newString += "Cirrus spissatus cumulonimbogenitus";
                                    case '4' -> newString += "Cirrus uncinus lub Cirrus fibratus lub obie te chmury razem (stopniowo zaciągający niebo)";
                                    case '5' -> newString += "Cirrus (w pasmach) i Cirrostratus lub sam Cirrostratus stopniowo zaciągający niebo, ale nie wyżej niż 45° nad horyzontem";
                                    case '6' -> newString += "Cirrus (w pasmach) i Cirrostratus lub sam Cirrostratus stopniowo zaciągający niebo, powyżej niż 45° nad horyzontem, lecz nie pokrywa całkowicie nieba";
                                    case '7' -> newString += "Cirrostratus pokrywający całkowicie niebo";
                                    case '8' -> newString += "Cirrostratus nie zaciągający i nie pokrywający nieba";
                                    case '9' -> newString += "Cirrocumulus sam lub przeważający wśród chmur wysokich";
                                    case '/' -> newString += "chmury CH są niewidoczne wskutek ciemności, mgły, wysokiej zamieci pyłowej lub piaskowej albo ciągłej warstwy chmur niższych) ";
                                }
                                System.out.println(newString);
                            }
                            case '9' -> {
                                System.out.println("Aktualny czas obserwacji: " + attributes[x].substring(1, 3) + ":" + attributes[x].substring(3, 5));
                            }
                        }
                    }
                }
            }
            System.out.println("////////////////////////////////////////////");
        }
    }

}
