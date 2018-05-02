
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.String;
import java.util.Iterator;
import java.io.FileReader;


public class Transpose {
    public Transpose() {
        content = new ArrayList();
    }

    String f;
    private boolean flagT = false; //проверка флага -t
    private boolean flagR = false; //проверка флага -r
    private String fileLocation; // местанохаждение входного файла(или имя)
    private String outFileName; // имя выходного файла
    private Integer symbols; // количество символов, заданное -a
    private ArrayList<ArrayList<String>> content;

    protected void minusO(Iterator<String> args) {
        if (!args.hasNext())
            throw new IllegalArgumentException();
        outFileName = args.next();
        System.out.println(outFileName);
        processArguments(args);
    }

    protected void minusA(Iterator<String> args) {
        if (!args.hasNext())
            return;
        try {
            symbols = Integer.valueOf(args.next());
        } catch (NumberFormatException e) {
            System.out.println("неверный формат флага '-a'");
        }
        System.out.println(symbols);
        processArguments(args);
    }

    protected void processArguments(Iterator<String> args) {
        if (!args.hasNext())
            return;
        String arg = args.next();
        switch (arg) {
            case "-o": {
                if (outFileName != null)
                    throw new IllegalArgumentException();
                System.out.println("o");
                minusO(args);
                break;
            }
            case "-a": {
                if (symbols != null)
                    throw new IllegalArgumentException();
                System.out.println("a");
                minusA(args);
                break;
            }
            case "-t": {
                if (flagT)
                    throw new IllegalArgumentException();
                flagT = true;
                System.out.println("t");
                processArguments(args);
                break;
            }
            case "-r": {
                if (flagR)
                    throw new IllegalArgumentException();
                flagR = true;
                System.out.println("r");
                processArguments(args);
                break;
            }
            default: {
                if (fileLocation != null)
                    throw new IllegalArgumentException();
                fileLocation = arg;
                System.out.println("def");
                System.out.println(fileLocation);
                processArguments(args);
                break;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.printf("Arg #%d: %s\n", i, args[i]);
        }
        Transpose instance = new Transpose();
        instance.processArguments(Arrays.asList(args).listIterator());
        instance.reading();
        instance.flagMinusA();
        instance.flagMinusT();
        instance.flagMinusR();
        try {
            instance.writing();
        } catch (FileNotFoundException e) {
            System.out.println("Writing error");
        }
    }

    private void reading() {
        InputStreamReader reader;
        try {
            if (fileLocation == null)
                reader = new InputStreamReader(System.in);
            else reader = new FileReader(fileLocation);
            BufferedReader br = new BufferedReader(reader);
            String localStr;
            String[] localArray;
            while (true) {
                localStr = br.readLine();
                if (localStr == null)
                    break;
                localArray = localStr.split("\\s+");
                for (int i = 0; i <= localArray.length - 1; i++) {
                    if (content.size() <= i)
                        content.add(new ArrayList<String>());
                    content.get(i).add(localArray[i]);
                }
            }
        } catch (IOException e) {
            System.out.println("Reading error");
        }
    }

    private void flagMinusA() {
        if (symbols == null) return;
        if (!flagR) {
            for (int i = 0; i <= content.size() - 1; i++) {
                for (int j = 0; j <= content.get(i).size() - 1; j++) {
                    f = String.format("%%%ds", symbols);
                    if (content.get(i).get(j).length() < symbols)
                        content.get(i).set(j, String.format(f, content.get(i).get(j)));
                }
            }
        }
    }

    private void flagMinusT() {
        if (!flagT)
            return;
        if (symbols == null)
            symbols = 10;
        for (int i = 0; i <= content.size() - 1; i++) {
            for (int j = 0; j <= content.get(i).size() - 1; j++) {
                if (content.get(i).get(j).length() > symbols) {
                    content.get(i).set(j, content.get(i).get(j).substring(0,symbols));
                }
            }
        }
    }

    private void flagMinusR() {
        if (!flagR)
           return;
        if (symbols == null)
            symbols = 10;
        f = String.format("%%%ds", symbols);
              for (int i = 0; i <= content.size() - 1; i++) {
            for (int j = 0; j <= content.get(i).size() - 1; j++) {
                content.get(i).set(j, String.format(f, content.get(i).get(j)));
            }
        }
    }

    private void writing() throws FileNotFoundException {
        PrintWriter writer;
        if (outFileName != null) {
            writer = new PrintWriter(outFileName);
        } else {
            writer = new PrintWriter(System.out);
        }
        for (int i = 0; i <= content.size() - 1; i++) {
            for (int j = 0; j <= content.get(i).size() - 1; j++) {
                String strToWrite = content.get(i).get(j);
                if (j == content.get(i).size() - 1)
                    writer.println(strToWrite);
                else {
                    writer.print(strToWrite);
                    writer.print(" ");
                }
            }
        }
        writer.flush();
    }
}