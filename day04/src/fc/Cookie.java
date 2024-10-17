package fc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cookie {

    private List<String> cookies = new ArrayList<>();
    
    // Construtor to initialize the Cookie class with textfile
    public Cookie(String filePath) throws IOException,FileNotFoundException {
        FileReader file = new FileReader(filePath);
        BufferedReader br = new BufferedReader(file);
        String line;

        while ((line =br.readLine()) != null) {
            cookies.add(line);
        }
        br.close();
    }
    
    public String getCookie() {
        if (cookies.isEmpty()) {
            return "No cookies available";
        } else {
            Random rand = new Random();
            return cookies.get(rand.nextInt(cookies.size()));
        }
    }

}
