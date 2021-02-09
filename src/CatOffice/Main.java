package CatOffice;


import java.util.Random;
import java.util.Scanner;

public class Main {
    static final int N = 3;
    static int field[][];


    static Scanner scanner;

    public static void main(String[] args) {
        int game=0;
        int gameForrever=1;
        int human1Count=0;
        int human2Count = 0;
        int humanCount = 0;
        int compCount = 0;
        int stepCounter = 0;
        field = new int[N][N];
        scanner = new Scanner(System.in);
        do {
            System.out.println("Если хотите играть с компьютером введите 1, если с человеком - 2");
            game = scanner.nextInt();
            if (game == 1) {
                boolean isWinner = false;
                do {
                    stepCounter++;
                    System.out.println(stepCounter);
                    if (stepCounter <= N * N) {
                        move(stepCounter);
                    } else
                        break;
                    drawField();
                    isWinner = findWinner();
                } while (isWinner == false);
                if (isWinner)
                    if (stepCounter %2 ==1){
                        System.out.println("Компьютер X - победитель");
                        compCount++;
                        System.out.println("Счет компьютер:человек "+compCount+" : "+humanCount);

                    }
                    else {
                        System.out.println("Человек O - победитель");
                        humanCount++;
                        System.out.println("Счет компьютер:человек "+compCount+" : "+humanCount);
                    }
                else
                    System.out.println("Ничья");
            } else if (game == 2) {

                boolean isWinner = false;
                do {
                    stepCounter++;
                    System.out.println(stepCounter);
                    if (stepCounter <= N * N) {
                        move2human(stepCounter);
                    } else
                        break;
                    drawField();
                    isWinner = findWinnerMy();
                } while (isWinner == false);
                if (isWinner) {
                    if (stepCounter %2 ==1){
                        System.out.println("Человек1 X - победитель");
                        human1Count++;
                        System.out.println("Счет человек:человек "+human1Count+" : "+human2Count);

                    }
                    else {
                        System.out.println("Человек2 O - победитель");
                        human2Count++;
                        System.out.println("Счет человек:человек "+human1Count+" : "+human2Count);
                    }

                }
                else
                    System.out.println("Ничья");
            }
            else break;

            System.out.println("Хотите еще играть? Да = 1, Нет = 0");
            stepCounter=0;
            eraseField();
            gameForrever = scanner.nextInt();

        }while (gameForrever ==1);
    }
    private static boolean findWinnerMy() {
        int sumX = 3;
        int sumO = 15;
        int row, column;

        for (row = 0; row < N; row++) {
            for (column = 0; column < N ; column++) {
                field[row][column]++;

            }
            if (field[row][column]== sumX && field[row][column]== sumO){
                return true;
            }
            //else break;
        }

        //for (row = 0; row < N; row++) {
        for (column = 0; column < N; column++) {

        }
        field[row][0]++;
        field[row][1]++;
        field[row][2]++;
        //}
        for ( column = 0; column < N; column++) {
            if (field[row][column] == sumX && field[row][column] == sumO) {
                return true;
            }
            // else break;
        }

        for (column = 0; column < N ; column++) {
            field[0][column]++;
            field[1][column]++;
            field[2][column]++;

        }
        for ( row = 0; row < N; row++) {
            if (field[row][column] == sumX && field[row][column] == sumO) {
                return true;
            }
            // else break;
        }



        return false;
    }

    private static boolean findWinner() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N - 1; col++) {
                if (field[row][col] > 0 && field[row][col + 1] > 0) {
                    if (field[row][col] != field[row][col + 1])
                        break;
                    else if (col == N - 1 - 1 && field[row][col] > 0) {
                        System.out.println("Hor: " + (row + 1));
                        return true;
                    }
                } else
                    break;
            }
        }

        for (int col = 0; col < N; col++) {
            for (int row = 0; row < N - 1; row++) {
                if (field[row][col] > 0 && field[row + 1][col] > 0) {
                    if (field[row][col] != field[row + 1][col])
                        break;
                    else if (row == N - 1 - 1 && field[row][col] > 0) {
                        System.out.println("Ver: " + (col + 1));
                        return true;
                    }
                } else
                    break;
            }
        }

        exit:
        for (int col = 0; col < N; col++) {
            if (field[col][col] > 0 && field[col + 1][col + 1] > 0) {
                if (field[col][col] != field[col + 1][col + 1])
                    break exit;
                else if (col == N - 1 - 1 && field[col][col] > 0) {
                    System.out.println("DiagM: ");
                    return true;
                }
            } else
                break exit;
        }

        exit:
        for (int row = 0; row < N - 1; row++) {
            for (int col = 0; col < N; col++) {
                if (row + col == N - 1) {
                    if (field[row][col] > 0 && field[row + 1][col - 1] > 0) {
                        if (field[row][col] != field[row + 1][col - 1])
                            break exit;
                        else if (row == N - 1 - 1 && field[row][col] > 0) {
                            System.out.println("DiagS: ");
                            return true;
                        }
                    } else
                        break exit;
                }
            }
        }

        return false;
    }

    static void move(int step) {
        int row = -1, col = -1;

        Random random = new Random();
        System.out.println(step % 2 == 1 ? "Ход компьютера" : "Ходите, Игрок ");
        do {
            if (step % 2 == 1) {
                row = random.nextInt(N);
                col = random.nextInt(N);
            }
            else
            {
                System.out.print("Введите новые координаты вашего хода ");
                row = scanner.nextInt();
                col = scanner.nextInt();
            }
        } while (row >= N || row < 0 || col >= N || col < 0 || field[row][col] != 0);
        field[row][col] = step % 2 == 1 ? 1 : 5;
    }
    static void move2human(int step) {
        int row = -1, col = -1;
        System.out.println(step % 2 == 1 ? "Ваш ход Игрок1 " : "Ваш ход Игрок2 ");
        do {
            if (step % 2 == 1) {
                System.out.print("Введите новые координаты вашего хода");
                row = scanner.nextInt();
                col = scanner.nextInt();
            }
            else
            {
                System.out.print("Введите новые координаты вашего хода");
                row = scanner.nextInt();
                col = scanner.nextInt();
            }
        } while (row >= N || row < 0 || col >= N || col < 0 || field[row][col] != 0);
        field[row][col] = step % 2 == 1 ? 1 : 5;
    }
    static void eraseField(){
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                field[row][col] = 0;

            }
        }
    }

    static void drawField() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (field[row][col] == 1)
                    System.out.print(" X ");
                else if (field[row][col] == 2)
                    System.out.print(" O ");
                else
                    System.out.print(" * ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

