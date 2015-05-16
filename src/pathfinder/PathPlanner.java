package pathfinder;

import java.awt.*;
import java.util.List;

/*
 * IMPORTANT!
 * All child classes must implement two methods:
 * getName - returning name of algorithm)
 * planPath - doing all calculations
 *
 * There is also a possibility to override empty setUp method,
 * to set up all variables, asking user for algorithm parameters,
 * getting a layer to print and so on...
 * Remember that you shouldn't use default values for variables,
 * because user can invoke algorithm more times using the same algorithm's object.
 * Therefore you should do all constructor's work in this method,
 * because it's the only way to revert to algorithm's initial state.
 */

public abstract class PathPlanner {
    protected PathPlanningConnection connection;
    protected Logger logger = Logger.getInstance();

    public PathPlanner() {
        logger.log("New instance of " + getName() + " algorithm created");
    }

    final public PathPlanningConnection getConnection() {
        return connection;
    }

    final public void setConnection(PathPlanningConnection connection) {
        this.connection = connection;
    }

    final protected void sendCalculationTime(double calcTimeInNano) {
        if (connection != null)
            connection.setCalcTime(calcTimeInNano);
    }

    final protected void sendCurrentProgress(double progress) {
        if (connection != null)
            connection.setProgress(progress);
    }

    final protected void sendRoute(List<Point> route) {
        if (connection != null)
            connection.setRoute(route);
    }

    final public void invoke() {
        logger.log(getName() + " algorithm invoked");
        logger.log("\tSetting up algorithm");
        setUp();
        logger.log("\tSetting up completed");
        logger.log("\tAlgorithm starting");
        long startTime = System.nanoTime();
        List<Point> route = planPath();
        long endTime = System.nanoTime();
        logger.log("\tAlgorithm finished");

        Point initialPrinterPosition = connection.getInitialPrinterPosition();
        if (initialPrinterPosition != null && route.get(0) != null && route.get(0) != initialPrinterPosition)
            route.add(0, initialPrinterPosition);

        long durationInNano = endTime - startTime;
        double cost = MoveCostCalculator.calculate(route, connection.getCostFunctionType());

        sendCalculationTime(durationInNano);
        sendRoute(route);
    }

    protected void setUp() {
    }

    protected abstract List<Point> planPath();

    protected String getName() {
        return this.getClass().getSimpleName();
    }
}