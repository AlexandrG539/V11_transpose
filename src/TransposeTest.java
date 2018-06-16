import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Field.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

class TransposeTest {
    @org.junit.jupiter.api.Test
    void processArguments() {
        Transpose TestTranspose = new Transpose();
        String[] argsTest = {"InputTest.txt", "-o", "OutFileTest.txt", "-a", "5", "-t", "-r"};
        try {
            TestTranspose.processArguments(Arrays.asList(argsTest).listIterator());
            Field fileLocation = TestTranspose.getClass().getDeclaredField("fileLocation");
            fileLocation.setAccessible(true);
            Field outFileName = TestTranspose.getClass().getDeclaredField("outFileName");
            outFileName.setAccessible(true);
            Field symbols = TestTranspose.getClass().getDeclaredField("symbols");
            symbols.setAccessible(true);
            Field flagT = TestTranspose.getClass().getDeclaredField("flagT");
            flagT.setAccessible(true);
            Field flagR = TestTranspose.getClass().getDeclaredField("flagR");
            flagR.setAccessible(true);
            assertEquals(fileLocation.get(TestTranspose), "InputTest.txt");
            assertEquals(outFileName.get(TestTranspose), "OutFileTest.txt");
            assertEquals(symbols.get(TestTranspose), 5);
            assertEquals(flagT.get(TestTranspose), true);
            assertEquals(flagR.get(TestTranspose), true);
        } catch (IllegalAccessException e) {
            assert(false);
            System.out.println("ошибка доступа test 1");
        } catch (NoSuchFieldException e) {
            assert(false);
            System.out.println("неверная ссылка на поле test 1");
        }
    }

    @Test
    void main() {
        Transpose TestTranspose = new Transpose();
        String[] argsTest = {"InputTest.txt", "-o", "OutFileTest.txt", "-a", "5", "-t", "-r"};
        ArrayList<String> string_1 = new ArrayList<>(Arrays.asList(" test", "  TST", "ttttt"));
        ArrayList<String> string_2 = new ArrayList<>(Arrays.asList("TESSS", "TTTEE", "tTEST"));
        ArrayList<String> string_3 = new ArrayList<>(Arrays.asList("TTTTE"));
        ArrayList<ArrayList<String>> contentTest = new ArrayList<ArrayList<String>>();
        contentTest.add(string_1);
        contentTest.add(string_2);
        contentTest.add(string_3);
                try {
            TestTranspose.processArguments(Arrays.asList(argsTest).listIterator());
            Field fileLocation = TestTranspose.getClass().getDeclaredField("fileLocation");
            fileLocation.setAccessible(true);
            Method reading = TestTranspose.getClass().getDeclaredMethod("reading");
            reading.setAccessible(true);
            reading.invoke(TestTranspose);
            Method checkFlagA = TestTranspose.getClass().getDeclaredMethod("checkFlagA");
            checkFlagA.setAccessible(true);
            Method flagMinusA = TestTranspose.getClass().getDeclaredMethod("flagMinusA");
            flagMinusA.setAccessible(true);
            if ((Boolean) (checkFlagA.invoke(TestTranspose))) {
                flagMinusA.invoke(TestTranspose);
            }
            Method checkFlagT = TestTranspose.getClass().getDeclaredMethod("checkFlagT");
            checkFlagT.setAccessible(true);
            Method flagMinusT = TestTranspose.getClass().getDeclaredMethod("flagMinusT");
            flagMinusT.setAccessible(true);
            if ((Boolean) (checkFlagT.invoke(TestTranspose))) {
                flagMinusT.invoke(TestTranspose);
            }
            Method checkFlagR = TestTranspose.getClass().getDeclaredMethod("checkFlagR");
            checkFlagR.setAccessible(true);
            Method flagMinusR = TestTranspose.getClass().getDeclaredMethod("flagMinusR");
            flagMinusR.setAccessible(true);
            if ((Boolean) (checkFlagR.invoke(TestTranspose))) {
                flagMinusR.invoke(TestTranspose);
            }
            Method writing = TestTranspose.getClass().getDeclaredMethod("writing");
            writing.setAccessible(true);
            writing.invoke(TestTranspose);
            Field content = TestTranspose.getClass().getDeclaredField("content");
            content.setAccessible(true);
            assertEquals(content.get(TestTranspose), contentTest);
        } catch (IllegalAccessException e) {
            assert(false);
            System.out.println("ошибка доступа test 2");
        } catch (NoSuchMethodException e) {
            assert(false);
            System.out.println("неверная ссылка на метод test 2");
        } catch (NoSuchFieldException e) {
            assert(false);
            System.out.println("неверная ссылка на поле test 2");
        } catch (InvocationTargetException e) {
            assert(false);
            System.out.println("ошибка исполнения метода test 2");
        }
    }
}