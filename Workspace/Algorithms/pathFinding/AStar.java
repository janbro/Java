package pathFinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * @author Rebekah
 */
public class AStar {

    public static Random random = new Random();
    public static int mapX = random.nextInt(20) + 30;
    public static int mapY = random.nextInt(20) + 30;
    public static int initialX, initialY;
    public static int[][] map;
    public static int[][] heuristic;
    public static int[][] delta = {{-1, 0},
        {0, -1},
        {1, 0},
        {0, 1},
        {-1, -1},
        {-1, 1},
        {1, -1},
        {1, 1},};

    public static void main(String[] args) {
        initialX = random.nextInt(mapX);
        initialY = random.nextInt(mapY);
        map = new int[mapX][mapY];
        for (int i = 0; i < mapX; i++) {
            for (int j = 0; j < mapY; j++) {
                if (getMine(i, j)) {
                    map[i][j] = 1;
                } else {
                    map[i][j] = 0;
                }
            }
        }
        System.out.println("Map: ");
        for (int i = 0; i < mapX; i++) {
            String s = "";
            for (int j = 0; j < mapY; j++) {
                s += map[i][j] + " ";
            }
            System.out.println(s);
        }
        System.out.println("Initial: ");
        System.out.println(initialX + " " + initialY);
        int destX = random.nextInt(mapX);
        int destY = random.nextInt(mapY);
        System.out.println("Goal: ");
        System.out.println(destX + " " + destY);
        System.out.println();
        ArrayList<int[]> path = getPath(destX, destY);
        if (path != null) {
            for (int[] i : path) {
                System.out.println(i[0] + " " + i[1]);
            }
        } else {
            System.out.println("No path.");
        }
    }

    public static ArrayList<int[]> getPath(int destX, int destY) {
        int[][] closed = new int[mapX][mapY];
        int[][] expand = new int[mapX][mapY];
        int[][] action = new int[mapX][mapY];
        for (int i = 0; i < mapX; i++) {
            for (int j = 0; j < mapY; j++) {
                closed[i][j] = 0;
                expand[i][j] = -1;
                action[i][j] = -1;
            }
        }
        closed[initialX][initialY] = 1;

        int x = initialX;
        int y = initialY;
        int g = 0;
        int h = 0;
        int f = 0;

        ArrayList<int[]> open = new ArrayList<>();
        int[] next = {f, g, x, y};
        open.add(next);

        boolean found = false;
        boolean resign = false;
        int count = 0;

        while (!found && !resign) {
            if (open.isEmpty()) {
                resign = true;
                return null;
            } else {
                sort(open);
                reverse(open);
                System.arraycopy(open.get(0), 0, next, 0, 4);
                open.remove(0);
                x = next[2];
                y = next[3];
                g = next[1];
                expand[x][y] = count;
                count++;

                if (x == destX && y == destY) {
                    found = true;
                } else {
                    for (int i = 0; i < delta.length; i++) {
                        int x2 = x + delta[i][0];
                        int y2 = y + delta[i][1];
                        if (x2 >= 0 && x2 < map.length && y2 >= 0 && y2 < map[0].length) {
                            if (closed[x2][y2] == 0 && map[x2][y2] == 0) {
                                int g2 = g + 1;
                                int h2 = Math.abs(x2 - destX) + Math.abs(y2 - destY);
                                int f2 = g2 + h2;
                                int[] next2 = {f2, g2, x2, y2};
                                open.add(next2);
                                closed[x2][y2] = 1;
                                action[x2][y2] = i;
                            }
                        }
                    }
                }
            }
        }
        ArrayList<int[]> path = new ArrayList<>();
        x = destX;
        y = destY;
        while (x != initialX || y != initialY) {
            int x2 = x - delta[action[x][y]][0];
            int y2 = y - delta[action[x][y]][1];
            int[] point = {x2, y2};
            path.add(point);
            x = x2;
            y = y2;
        }
        reverse(path);
        return path;
    }

    public static void sort(ArrayList<int[]> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i)[0] > list.get(j)[0]) {
                    int[] temp = new int[list.get(j).length];
                    System.arraycopy(list.get(j), 0, temp, 0, list.get(j).length);
                    System.arraycopy(list.get(i), 0, list.get(j), 0, list.get(j).length);
                    System.arraycopy(temp, 0, list.get(i), 0, list.get(j).length);
                }
            }
        }
    }

    public static void reverse(ArrayList<int[]> list) {
        for (int i = 0; i < list.size() / 2; i++) {
            int[] temp = new int[list.get(i).length];
            System.arraycopy(list.get(i), 0, temp, 0, list.get(i).length);
            System.arraycopy(list.get(list.size() - i - 1), 0, list.get(i), 0, list.get(i).length);
            System.arraycopy(temp, 0, list.get(i), 0, list.get(list.size() - i - 1).length);
        }
    }

    public static boolean getMine(int x, int y) {
        return (random.nextInt(100) < 10);
    }
}
