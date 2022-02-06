package cinema;

import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);
    private static int purchasedTickets = 0;
    private static int currentIncome = 0;
    private static int totalIncome = 0;

    public static void main(String[] args) {
        // Write your code here

        System.out.println("Enter the number of rows:");
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int col = scanner.nextInt();
        String[][] cinemaSeats = new String[row][col];
        initializeCinemaSeats(cinemaSeats);
        calculateTotalIncome(row, col);
        showMenu();
        int menu = scanner.nextInt();

        while (menu != 0) {
            switch (menu) {
                case 1:
                    showCinemaSeats(cinemaSeats);
                    break;
                case 2:
                    buyTicket(cinemaSeats, row, col);
                    break;
                case 3:
                    showStatistics(row, col);
                    break;
                default:
                    break;
            }
            showMenu();
            menu = scanner.nextInt();
        }
    }

    private static void showStatistics(int row, int col) {
        System.out.printf("Number of purchased tickets: %d%n", purchasedTickets);
        System.out.printf("Percentage: %.2f%s%n", ((double) purchasedTickets / (row * col)) * 100, "%");
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
        System.out.println();
    }

    private static void calculateTotalIncome(int row, int col) {
        if (row * col <= 60) {
            totalIncome = row * col * 10;
        } else {
            int half1 = (row / 2) * col * 10;
            int half2 = (row - (row / 2)) * col * 8;
            totalIncome = half1 + half2;
        }
    }

    private static void buyTicket(String[][] cinema, int row, int col) {
        boolean sold = true;
        int rowNo = 0;
        int seatNo = 0;
        while (sold) {
            System.out.println("Enter a row number:");
            rowNo = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNo = scanner.nextInt();
            if (rowNo > row || seatNo > col) {
                System.out.println("Wrong input!");
                continue;
            }
            sold = isSold(cinema, rowNo, seatNo);
            if (sold) {
                System.out.println("That ticket has already been purchased");
            }
        }

        int price;
        if (row * col <= 60) {
            price = 10;
        } else {
            int half = row / 2;
            price = rowNo <= half ? 10 : 8;
        }
        currentIncome += price;
        purchasedTickets++;
        reserveSeat(cinema, rowNo, seatNo);
        System.out.printf("Ticket price: $%d", price);
        System.out.println();
    }

    private static void showMenu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics`");
        System.out.println("0. Exit");
    }


    private static void initializeCinemaSeats(String[][] cinema) {
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                cinema[i][j] = "S";
            }
        }
    }

    private static void reserveSeat(String[][] cinema, int rowNo, int seatNo) {
        cinema[rowNo - 1][seatNo - 1] = "B";
    }

    private static void showCinemaSeats(String[][] cinema) {
        System.out.println();
        System.out.println("Cinema:");
        System.out.printf(" ");
        for (int i = 1; i <= cinema[0].length; i++) {
            System.out.printf(" %d", i);
        }

        for (int i = 0; i < cinema.length; i++) {
            System.out.printf("%n%d", i + 1);
            for (int j = 0; j < cinema[i].length; j++) {
                System.out.printf(" %s", cinema[i][j]);
            }
        }
        System.out.println();
    }

    public static boolean isSold(String[][] cinema, int row, int col) {
        return cinema[row - 1][col - 1].equals("B");
    }
}