package production;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Layer {
    // TODO think about moving back to boolean[][]
    List<List<Boolean>> array;

    public Layer(List<List<Boolean>> array) {
        this.array = array;
    }

    public Layer(Layer layerToCopy) {
        this.array = layerToCopy.toTable();
    }

    public List<List<Boolean>> getArray() {
        return array;
    }

    public void setArray(List<List<Boolean>> array) {
        this.array = array;
    }

    public int getWidth() {
        if (getHeight() == 0)
            return 0;
        return array.get(0).size();
    }

    public int getHeight() {
        if (array == null)
            return 0;
        return array.size();
    }

    public boolean get(int i, int j) {
        return array.get(i).get(j);
    }

    public List<Point> toPoints() {
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < array.get(i).size(); j++) {
                if (get(i, j)) {
                    list.add(new Point(i, j));
                }
            }
        }
        return list;
    }

    public List<List<Boolean>> toTable() {
        return new Layer(this).getArray();
    }

    public boolean[][] toSimpleTable() {
        boolean[][] simpleTable = new boolean[array.size()][];
        int i = 0;
        for (List<Boolean> row : array) {
            simpleTable[i] = new boolean[row.size()];
            int j = 0;
            for (Boolean pixel : row) {
                simpleTable[i][j] = pixel;
                j++;
            }
            i++;
        }
        return simpleTable;
    }

    public void printAsTable() {
        boolean[][] table = this.toSimpleTable();
        for (boolean[] row : table) {
            for (boolean pixel : row) {
                System.out.print((pixel ? 'x' : '_') + " ");
            }
            System.out.println();
        }
    }

    public void printAsPoints() {
        List<Point> list = this.toPoints();
        for (Point point : list) {
            System.out.println("(" + point.x + ", " + point.y + ")");
        }
    }
}
