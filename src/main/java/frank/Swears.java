package frank;

import commands.list.hlfrench.VerbData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Swears {

    Set<String> swear;
    public Swears(String s) throws IOException {
        swear = new HashSet<>();
        Read(s);
    }

    private void Read(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));

        String line;
        while ((line = br.readLine()) != null)  {
            swear.add(line);

        }
    }

}
