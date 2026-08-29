import java.io.*;

public class ReadFileContents {

    public static void main(String[] args) {
        String fileName = "a1.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String b;
            while ((b = br.readLine()) != null) {
                System.out.println(b);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}