package myUtil;

import java.util.ArrayList;
import java.util.List;

public class Compare {

    private boolean[][] barr ;
    private int[][] miarr ;
    private int[][] diarr ;

    //初始化一个boolean数组，存放各个字符是否匹配的
    private boolean[][] getCompareBooleanArray(String source, String compareTo) {
        boolean[][] comps;
        if (compareTo == null || source == null) {
            throw new IllegalArgumentException("必须设置完两个文本后再进行初始化");
        }
        comps = new boolean[compareTo.length()][source.length()];
        for (int i = 0; i < compareTo.length(); i++) {
            for (int j = 0; j < source.length(); j++) {
                comps[i][j] = compareTo.charAt(i) == source.charAt(j);
            }
        }
        return comps;
    }

    //初始化一个int矩阵的函数，这个函数就对应寻找最大匹配路径的函数
    private int[][] getStepPathArr(boolean[][] barr) {
        int[][] iarr = new int[barr.length][barr[0].length];
        for (int i = barr.length - 1; i >= 0; i--) {
            for (int j = barr[i].length - 1; j >= 0; j--) {
                int v_i_j = barr[i][j] ? 1 : 0;
                int n_i_1_j_1 = i + 1 >= iarr.length || j + 1 >= iarr[i].length ? 0
                        : iarr[i + 1][j + 1];
                int n_i_1_j = i + 1 >= iarr.length ? 0 : iarr[i + 1][j];
                int n_i_j_1 = j + 1 >= iarr[i].length ? 0 : iarr[i][j + 1];
                int v_n_1_1 = v_i_j + n_i_1_j_1;
                iarr[i][j] = v_n_1_1 > n_i_1_j ? v_n_1_1 > n_i_j_1 ? v_n_1_1
                        : n_i_j_1 : n_i_1_j > n_i_j_1 ? n_i_1_j : n_i_j_1;
            }
        }
        return iarr;
    }

    //寻找最优路径的函数，也是初始化一个int[][]
    private int[][] getMinStepArr(int[][] miarr, boolean[][] barr) {
        int[][] iarr = new int[miarr.length][miarr[0].length];
        for (int i = iarr.length - 1; i >= 0; i--) {
            for (int j = iarr[i].length - 1; j >= 0; j--) {
                if (barr[i][j]) {
                    iarr[i][j] = getV(iarr, i + 1, j + 1) + 1;
                } else if (getV(miarr, i, j + 1) >= getV(miarr, i + 1, j)) {
                    iarr[i][j] = getV(iarr, i, j + 1);
                } else {
                    iarr[i][j] = getV(iarr, i + 1, j) + 1;
                }
            }
        }
        return iarr;
    }

    private int getV(int[][] arr, int i, int j) {
        if (i >= arr.length || j >= arr[i].length) {
            return 0;
        }
        return arr[i][j];
    }

    public List<TextDiff> doCompare(String source,String compareTo){
        this.barr = this.getCompareBooleanArray(source,compareTo) ;
        this.miarr = this.getStepPathArr(barr);
        this.diarr = this.getMinStepArr(miarr,barr);

        List<Point> points = new ArrayList<Point>();
        int maxJ = -1;
        for (int i = 0; i < barr.length; i++) {
            int tempMaxJ = -1;
            for (int j = 0; j < barr[i].length; j++) {
                if (j > maxJ && maxJ != -1) {
                    break;
                }
                if (barr[i][j]) {
                    if (tempMaxJ == -1) {
                        tempMaxJ = j;
                    }
                    points.add(new Point(i, j));
                }
            }
            if (tempMaxJ != -1) {
                maxJ = tempMaxJ;
            }
        }

        int max = 0;
        int minSetp = -1;
        for (Point point : points) {
            if (miarr[point.getX()][point.getY()] > max) {
                max = miarr[point.getX()][point.getY()];
            }
        }
        for (Point point : points) {
            if (minSetp == -1) {
                minSetp = diarr[point.getX()][point.getY()];
            } else if (miarr[point.getX()][point.getY()] >= max
                    && diarr[point.getX()][point.getY()] < minSetp) {
                minSetp = diarr[point.getX()][point.getY()];
            }
        }
        List<Point> willRemove = new ArrayList<Point>();
        for (Point point : points) {
            if (miarr[point.getX()][point.getY()] < max
                    || diarr[point.getX()][point.getY()] > minSetp) {
                willRemove.add(point);
            }
        }
        points.removeAll(willRemove);
        willRemove.clear();

        List<TextDiff> diffs = new ArrayList<TextDiff>();
        if (points.size() >= 1) {
            Point startPoint = points.get(0);
            points.clear();

            if (startPoint.getX() != 0) {
                diffs.add(new TextDiff(TextDiff.TYPE_INSERTED, 0, startPoint
                        .getX()));
            }
            if (startPoint.getY() != 0) {
                diffs.add(new TextDiff(TextDiff.TYPE_DELETED, 0, startPoint
                        .getY()));
            }
            Point next = getNext(startPoint, miarr, diarr, barr);
            while (next != null) {
                if (!barr[startPoint.getX()][startPoint.getY()]) {
                    startPoint = new Point(startPoint.getX() + 1, startPoint
                            .getY() + 1);
                }
                if (startPoint.getX() != next.getX() - 1
                        || startPoint.getY() != next.getY() - 1) {
                    diffs.add(new TextDiff(TextDiff.TYPE_INSERTED, startPoint
                            .getX(), next.getX() - startPoint.getX()));

                    diffs.add(new TextDiff(TextDiff.TYPE_DELETED, startPoint
                            .getY(), next.getY() - startPoint.getY()));
                }
                startPoint = next;
                next = getNext(startPoint, miarr, diarr, barr);
            }
            if (startPoint.getX() != barr.length) {
                diffs.add(new TextDiff(TextDiff.TYPE_INSERTED, startPoint
                        .getX(), barr.length - startPoint.getX()));
            }
            if (startPoint.getY() != barr[0].length) {
                diffs.add(new TextDiff(TextDiff.TYPE_DELETED,
                        startPoint.getY(), barr[0].length - startPoint.getY()));
            }
        }
        return diffs;
    }



    public static class Point {
        private int x;
        private int y;

        private Point next;

        public Point getNext() {
            return next;
        }

        public void setNext(Point next) {
            this.next = next;
        }

        public Point(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    /**
     * 获取下一个匹配节点
     */
    private static Point getNext(Point p, int[][] miarr, int[][] diarr,
                                 boolean[][] barr) {
        if (p.getX() >= barr.length || p.getY() >= barr[p.getX()].length) {
            return null;
        }
        if (barr[p.getX()][p.getY()]) {
            return new Point(p.getX() + 1, p.getY() + 1);
        }
        List<Point> points = new ArrayList<Point>();
        int min = -1;
        for (int i = p.getX(); i < barr.length; i++) {
            for (int j = p.getY(); j < barr[i].length; j++) {
                if (miarr[i][j] != miarr[p.getX()][p.getY()]) {
                    break;
                }
                if (barr[i][j] && miarr[i][j] == miarr[p.getX()][p.getY()]) {
                    if (min == -1) {
                        min = diarr[i][j];
                    } else if (diarr[i][j] < min) {
                        min = diarr[i][j];
                    }
                    points.add(new Point(i, j));
                }
            }
        }
        if (points.size() > 0) {
            List<Point> remove = new ArrayList<Point>();
            for (Point point : points) {
                if (diarr[point.getX()][point.getY()] > min) {
                    remove.add(point);
                }
            }
            points.removeAll(remove);
            remove.clear();
            remove = null;
            if (points.size() > 0) {
                return points.get(0);
            }
        }
        return null;
    }

}
