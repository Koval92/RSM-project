package pathfinder;

import java.awt.*;
import java.util.List;

public interface PathPlanningConnection {
    void setProgress(double progress);

    void setCalcTime(double calcTimeInNano);

    void setRoute(List<Point> route);

    CostFunctionType getCostFunctionType();

    List<Point> getCopyOfLayerAsListOfPoints();

    List<List<Boolean>> getCopyOfLayerAsTable();

    boolean[][] getCopyOfLayerAsSimpleTable();

    Layer getCopyOfLayer();

    Point getInitialPrinterPosition();
}
