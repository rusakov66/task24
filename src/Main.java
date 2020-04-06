import java.util.ArrayList;
import java.util.List;

public class Main {
    // Четыре массива для выявления маршрута содержащего макс. кол-во вишен
    static List<Integer> px = new ArrayList<>(); // Список посещенных координат X
    static List<Integer> py = new ArrayList<>(); // Список посещенных координат Y
    static List<Integer> index = new ArrayList<>(); // Список номеров маршрутов
    static List<Integer> summ = new ArrayList<>(); // Список сумм набранных в текущий момент
    public static int ind1 = 0; // Номер текущего маршрута
    int[][] matrix = {
            { 0, 0, 2, 2, 0, 0},
            { 1, 1, 0, 0, 0, 0},
            { 2, 2, 2, 2, 0, 0},
            { 0, 2, 1, 1, 0, 0},
            { 1, 2, 0, 2, 0, 0},
            { 2, 2, 2, 0, 0, 0}
    };
    // Распечатка матрицы (использовалась для отладки)
    public void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------------");
    }
    // метод обхода матрицы вглубь (используется только инкремент X и Y "вправо" и "вниз")
    public  void fill(int x, int y,int sum) {
        px.add(x);
        py.add(y);
        index.add(ind1);
        summ.add(sum);
        if (matrix[x][y] == 2) { // встретилась вишенка
            sum = sum+2;
        }

        if (x==5 & y==5) { // доситгнут правый нижний угол
            //System.out.println(sum + " <=========== SUM");
            //print(matrix);
            sum = 0;
            ind1 = ind1 + 1; // следующий номер маршрута
            return;
        }

        int k = 0; // Переменная k - если она равна двойке, то двигаться вправо и вниз нельзя
        if (x+1 != 6)
            if (matrix[x + 1][y] == 1) k = k + 1;
        if (y+1 != 6)
            if (matrix[x][y + 1] == 1) k = k + 1;
        if (k == 2) return;

        if (x+1 != 6) // собственно обход: если можно вправо, или если можно вниз
            if (matrix[x + 1][y] != 1) fill(x + 1, y,sum);
        if (y+1 < 6)
            if (matrix[x][y + 1] != 1) fill(x, y + 1,sum);
    }

    public void analiz(){
        int max = 0; // поиск максимальной суммы
        int indmax = 0; // номер маршрута, содержащий максимальную сумму

        for (int i=0; i < summ.size(); i++) {
            if (summ.get(i) > max) {
                max = summ.get(i);
                indmax = index.get(i);
                System.out.print("  ");
            }
        }
        System.out.print(" Max = " + max);
        System.out.print(" IndMax = " + indmax);
        System.out.println();
        // Вывод пути: правй нижний угол: (5,5). Отсчёт от 0 до 5
        for (int i=0; i < summ.size(); i++) {
            if (index.get(i) == indmax) {
                System.out.println("Path : ("+py.get(i)+", "+px.get(i)+")");
            }
        }
    }

    public static void main(String[] args) {
        Main aa = new Main();
        aa.fill(0,0, 0);
        aa.analiz();
    }
}
