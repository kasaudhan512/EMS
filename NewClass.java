
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NewClass {
    
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/home/shubham/Desktop/myMentor.txt"))));
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("/home/shubham/Desktop/outputtxt")));
            try {
                // for performance compile once and use repeatedly (instead of line.matches("four.*"))
                Pattern pattern = Pattern.compile("14.*"); // brackets only needed if using regex replace below ...
                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.matches()) {
                        line = line + "|12"; // your data
                        // line = matcher.replaceAll("$16,"); // or with regex-replace ... "$1" is the first "()" in the pattern above
                    }
                    writer.append(line);
                    writer.newLine();
                    // writer.flush(); // to see everything that is written immediately in the file [it's buffered otherwise]
                }
            } finally {
                writer.close();
                reader.close();
            }
        } finally {
            reader.close();
        }
    }
 
}

