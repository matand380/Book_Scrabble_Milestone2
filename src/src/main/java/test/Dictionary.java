package test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
    List<String> fileNames;
    CacheReplacementPolicy LRU; //for existing words
    CacheManager cacheWithLRU;
    CacheReplacementPolicy LFU; //for non-existing words
    CacheManager cacheWithLFU;
    BloomFilter filter;

    public Dictionary(String fileName1, String fileName2) {
        fileNames = new ArrayList<>();
        fileNames.add(fileName1);
        fileNames.add(fileName2);
        this.LRU = new LRU();
        this.cacheWithLRU = new CacheManager(400, LRU);
        this.LFU = new LFU();
        this.cacheWithLFU = new CacheManager(100, LFU);
        this.filter = new BloomFilter(256, "MD5", "SHA1");
        try {
            for (String fileName : fileNames) {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String line = reader.readLine();
                String[] words = new String[line.length()];
                words = line.split(" ");
                {
                    for (String w : words)
                        filter.add(w);
                }
                reader.close();
            }
        } catch (Exception e) {
            System.out.println("problem with IO");
        }
    }

    public boolean query(String word) {
        if (cacheWithLRU.query(word))
            return true;
        if (cacheWithLFU.query(word))
            return false;

        if (filter.contains(word))
            cacheWithLRU.add(word);
        else
            cacheWithLFU.add(word);

        return filter.contains(word);
    }


    public boolean challenge(String word) {
        boolean isFound;
        String fileName1 = fileNames.get(0);
        String fileName2 = fileNames.get(1);
        try {
            isFound = IOSearcher.search(word, fileName1, fileName2);
        } catch (IOException e) {
            return false;
        }
        return isFound;

    }


}
