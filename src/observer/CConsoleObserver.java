package observer;

import java.util.Observer;
import solver.CStepData;

public class CConsoleObserver implements IObserver {

    @Override
    public void update(CStepData data) {
        System.out.printf("%-6.4f    %-6.4f    %-6.4f\n",
                data.T, data.Alpha, data.Omega);
    }
}
