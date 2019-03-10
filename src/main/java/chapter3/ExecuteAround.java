package chapter3;

import java.io.*;

public class ExecuteAround {

    public static void main(String... args) throws IOException {

        ExecuteAround ar = new ExecuteAround();

        // method we want to refactor to make more flexible
        String result = ar.processFileLimited();
        System.out.println(result);

        System.out.println("---");

        String oneLine = ar.processFile((BufferedReader b) -> b.readLine());
        System.out.println(oneLine);

        String twoLines = ar.processFile((BufferedReader b) -> b.readLine() + b.readLine());
        System.out.println(twoLines);

    }

    public String processFileLimited() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("chapter3/data.txt");
        try (BufferedReader br =
                     new BufferedReader(new InputStreamReader(is))) {
            return br.readLine();
        }
    }


    public String processFile(BufferedReaderProcessor p) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("chapter3/data.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            return p.process(br);
        }

    }

    public interface BufferedReaderProcessor {
        public String process(BufferedReader b) throws IOException;

    }
}
