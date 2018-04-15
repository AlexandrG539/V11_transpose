
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.String;
import java.util.Iterator;
import java.io.FileReader;


public class transpose {
    public transpose() {
        content = new ArrayList();
    }
    private boolean flagT = false; //проверка флага -t
    private boolean flagR = false; //проверка флага -r
    private String fileLocation; // местанохаждение входного файла
    private String outFileName; // имя выходного файла
    private Integer symbols; // оличество символов, заданное -a
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
        for (int i = 0; i < args.length; i++){
            System.out.printf("Arg #%d: %s\n",i,args[i]);
        }
        transpose instance = new transpose();
        instance.processArguments(Arrays.asList(args).listIterator());
        instance.reading();
        instance.flags();
        instance.writing();
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
        } catch (IOException e) {System.out.println("Reading error"); }
    }
    private void flags() {
        try {
            String f;// формат строки
            if (symbols != null) {
                for (int i = 0; i <= content.size() - 1; i++) {
                    ArrayList<String> flagAArray = content.get(i);
                    for (int j = 0; j <= flagAArray.size() - 1; j++) {
                        f = String.format("%%-%ds", symbols);
                        if (flagAArray.get(j).length() > symbols)
                            content.get(i).set(j, flagAArray.get(j).substring(0, symbols));
                        else content.get(i).set(j, String.format(f, content.get(i).get(j)));
                    }
                }
            }
            if (flagR) {
                if (symbols == null)
                    f = "%10s";
                else
                    f = String.format("%%-%ds", symbols);
                for (int i = 0; i <= content.size() - 1; i++) {
                    ArrayList<String> flagRArray = content.get(i);
                    for (int j = 0; j <= flagRArray.size() - 1; j++) {
                        content.get(i).set(j, String.format(f, content.get(i).get(j)));
                    }
                }
            }
        } catch (Exception e){}
    }
    private void writing() {
        PrintWriter writer;
        if (outFileName != null) {
            try {
                writer = new PrintWriter(outFileName);
            } catch (FileNotFoundException e){System.out.println("Writing error");}
        } else {
            writer = new PrintWriter(System.out);
        }
        for (int i = 0; i <= content.size() - 1; i++) {
            ArrayList<String> writeArray = content.get(i);
            for (int j = 0; j <= writeArray.size() - 1; j++) {
                String strToWrite = writeArray.get(j);


                if (j == writeArray.size() - 1)
                    writer.println(strToWrite);
                else {
                    writer.print(strToWrite);
                    writer.print(" ");
                }
            }
        }
    }
}