package DataAcquisition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class DataAcquisition {
    static public String[] acquireData(String begin, String end) throws IOException {
        String s_link = "https://www.ogimet.com/cgi-bin/getsynop?begin="+ begin +"&end="+ end + "&state=Pol";
        System.out.println(s_link);
        URL link = new URL(s_link);
        InputStream inputStream = link.openStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String inputLine;
        String output="";

        while ((inputLine = bufferedReader.readLine()) != null)
            output+=inputLine;

        bufferedReader.close();
        return output.split("==");
    }
}
