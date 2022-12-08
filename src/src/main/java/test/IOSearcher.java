package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOSearcher {
    public static boolean search(String word, String fileName1, String fileName2) throws IOException {
        List<String> fileNames = new ArrayList<>();
        fileNames.add(fileName1);
        fileNames.add(fileName2);
        for (String fileName : fileNames) {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String readLine = reader.readLine();
            while (readLine != null) {
                if (readLine.contains(word)) {
                    reader.close();
                    return true;
                }
                readLine = reader.readLine();
            }
            reader.close();
        }
        return false;
    }

}
