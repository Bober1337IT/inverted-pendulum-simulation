package observer;

import solver.CStepData;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CFileObserver implements IObserver {

    @Override
    public void update(CStepData data) {
        String fName = new File(".").getAbsolutePath();
        fName = fName.substring(0, fName.length() - 1) + "result.dat";
        File aFile = new File(fName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(aFile, true))) {
            bw.write(String.format("%-6.4f    %-10.7f    %-10.7f%n",
                    data.T, data.Alpha, data.Omega));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
