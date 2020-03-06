import javax.swing.*;
import java.awt.*;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.List;

public class FileUtility {

    public static void readCurrencyFromFile(String fileName) {
        XMLDecoder decoder = null;
        try {
            decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
            CurrencyRepository.setCurrencyList((List<Currency>) decoder.readObject());
            System.out.println("Currency List Updated.");
        } catch (FileNotFoundException e) {
            System.out.printf("ERROR: File \"%s\" not found", fileName);
        }
    }

    public static void writeCurrencyToFile(String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                System.out.println("Exception! :" + e.toString());
            }
        });
        encoder.writeObject(CurrencyRepository.getCurrencyList());
        encoder.close();
        fos.close();
    }
}
