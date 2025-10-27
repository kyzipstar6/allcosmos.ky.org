package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class AutoBadImportCleaner {
    public static void main(String args[]) {
        String bi = "import java.lang.classfile.Label;";

        try {
            String pdir = new File("").getAbsolutePath();
            String dir = pdir + "\\src\\application\\";
            List<File> allf = null;
            Path p = new File(dir).getAbsoluteFile().toPath();
            Stream<Path> path = Files.list(new File(dir).getAbsoluteFile().toPath());
            List<Path> lfiles = path.toList();

            for (int i = 0; i < lfiles.size() - 1; i++) {
                BufferedReader r = new BufferedReader(new FileReader(lfiles.get(i).toFile()));

                List<String> lns = r.lines().toList();
                r.close();
                StringBuilder bd = new StringBuilder();
                for (String l : lns)
                    bd.append(l + " \n");
                String txt = bd.toString();
                String file = lfiles.get(i).toFile() + "";
                if (!file.contains(".class")) {
                    FileWriter wr = new FileWriter(lfiles.get(i).toFile());
                    wr.write(txt.replace(bi, ""));
                    wr.close();
                    System.out.println("Succesfully cleared" + lfiles.get(i).toFile());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
