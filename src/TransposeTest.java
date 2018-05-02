import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Field.*;
import java.util.ArrayList;
import java.util.Arrays;

class TransposeTest {
    @org.junit.jupiter.api.Test
    void processArguments() {
        Transpose TestTranspose = new Transpose();
        String[] argsTest = {"InputTest.txt","-o","OutFileTest.txt","-a","5","-t","-r"};
        TestTranspose.main(argsTest);
        try {
            Field fileLocation = TransposeTest.class.getField("fileLocation");
            Field outFileName = TransposeTest.class.getField("outFileName");
            Field symbols = TransposeTest.class.getField("symbols");
            Field flagT = TransposeTest.class.getField("flagT");
            Field flagR = TransposeTest.class.getField("flagR");
            assertEquals(fileLocation,"InputTest.txt");
            assertEquals(outFileName, "OutFileTest.txt");
            assertEquals(symbols, 5);
            assertEquals(flagT, true);
            assertEquals(flagR, true);
        } catch (NoSuchFieldException e) {
            System.out.println("неверная ссылка на поле");
        }
    }
    @org.junit.jupiter.api.Test
    void main() {
        Transpose TestTranspose = new Transpose();
        String[] argsTest = {"InputTest.txt","-o","OutFileTest.txt","-a","5","-t","-r"};
        TestTranspose.main(argsTest);
        ArrayList<String> string_1 = new ArrayList<>(Arrays.asList( " test"," test","ttttt"));
        ArrayList<String> string_2 = new ArrayList<>(Arrays.asList("TESSS","TTTEE","tTEST"));
        ArrayList<String> string_3 = new ArrayList<>(Arrays.asList("TTTTE"));
        ArrayList<ArrayList<String>> contentTest = new ArrayList<ArrayList<String>>();
        contentTest.add(string_1);
        contentTest.add(string_2);
        contentTest.add(string_3);
        try {
            Field content = Transpose.class.getField("content");
            assertEquals(content, contentTest);
        } catch (NoSuchFieldException e) {
            System.out.println("неверная ссылка на поле");
        }
    }
}