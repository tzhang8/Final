import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class AdviceGen {

    private final String[] country = {"USA", "UK", "China", "India", "Australia", "Canada", "Mexico", "Japan", "Korea", "Thailand", "Russia"};

    public AdviceGen() {}

    public HashMap<String, String> generate_advice() throws FileNotFoundException, URISyntaxException {

        HashMap<String, String> advices = new HashMap<>();

        URL resource = getClass().getClassLoader().getResource("advices.txt");
        File file = new File(resource.toURI());
        Scanner scanner = new Scanner(file);

        int count = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            advices.put(country[count++], line);
        }
        scanner.close();
        return advices;
    }

}
