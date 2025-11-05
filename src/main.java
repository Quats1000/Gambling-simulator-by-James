import java.util.Random;
import java.util.Scanner;

public class main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random rng = new Random();

    public static void main(String[] args) {
        System.out.print("" +
                "                                                                                                                                               \n" +
                "  /$$$$$$                      /$$                           /$$                          /$$$$$                                               \n" +
                " /$$__  $$                    |__/                          | $$                         |__  $$                                              \n" +
                "| $$  \\__/  /$$$$$$   /$$$$$$$ /$$ /$$$$$$$   /$$$$$$       | $$$$$$$  /$$   /$$            | $$  /$$$$$$  /$$$$$$/$$$$   /$$$$$$   /$$$$$$$ \n" +
                "| $$       |____  $$ /$$_____/| $$| $$__  $$ /$$__  $$      | $$__  $$| $$  | $$            | $$ |____  $$| $$_  $$_  $$ /$$__  $$ /$$_____/ \n" +
                "| $$        /$$$$$$$|  $$$$$$ | $$| $$  \\ $$| $$  \\ $$      | $$  \\ $$| $$  | $$       /$$  | $$  /$$$$$$$| $$ \\ $$ \\ $$| $$$$$$$$|  $$$$$$ \n" +
                "| $$    $$ /$$__  $$ \\____  $$| $$| $$  | $$| $$  | $$      | $$  | $$| $$  | $$      | $$  | $$ /$$__  $$| $$ | $$ | $$| $$_____/ \\____  $$\n" +
                "|  $$$$$$/|  $$$$$$$ /$$$$$$$/| $$| $$  | $$|  $$$$$$/      | $$$$$$$/|  $$$$$$$      |  $$$$$$/|  $$$$$$$| $$ | $$ | $$|  $$$$$$$ /$$$$$$$/\n" +
                " \\______/  \\_______/|_______/ |__/|__/  |__/ \\______/       |_______/  \\____  $$       \\______/  \\_______/|__/ |__/ |__/ \\_______/|_______/ \n" +
                "                                                                       /$$  | $$                                                            \n" +
                "                                                                      |  $$$$$$/                                                            \n" +
                "                                                                       \\______/                                                            \n" +
                "                                                                                                                                            \n" +
                "                                       Glückspiel kann süchtig machen! (Aber das ist eh kein echtes Geld)                                   \n" +
                "                                                                        Ver.: 0.3.1.0 SE                                                             " +
                "");

        System.out.println("  \n" +
                " \n"+
                " ");


        double balance = askInitialBalance();
        boolean running = true;

        while (running) {
            showMenu(balance);
            int choice = readIntInRange("Deine Wahl: ", 1, 12);

            switch (choice) {
                case 1 -> balance = coinFlip(balance);
                case 2 -> balance = slotMachine(balance);
                case 3 -> balance = roulette(balance);
                case 4 -> balance = diceGame(balance);
                case 5 -> balance = highLowGame(balance);
                case 6 -> balance = deposit(balance);
                case 7 -> balance = cardsGame(balance);
                case 8 -> balance = rouletteDozen(balance);
                case 9 -> balance = luckyWheel(balance);
                case 10 -> balance = bonusSlot(balance);
                case 11 -> balance = miniLottery(balance);
                case 12 -> {
                    System.out.printf("Danke fürs Spielen! Endguthaben: €%.2f%n", balance);
                    running = false;
                }
            }
        }
        scanner.close();
    }

    // ===== Menü =====
    private static void showMenu(double balance) {
        System.out.printf("%nAktueller Kontostand: €%.2f%n", balance);
        System.out.println("Wähle ein Spiel:");
        System.out.println("1) Münzwurf");
        System.out.println("2) Slot-Maschine");
        System.out.println("3) Roulette (mit Grün 0)");
        System.out.println("4) Würfelspiel");
        System.out.println("5) High-Low Kartenspiel");
        System.out.println("6) Einzahlen");
        System.out.println("7) Karten-Paar");
        System.out.println("8) Roulette-Dutzende");
        System.out.println("9) Glücksrad");
        System.out.println("10) Bonus-Slot");
        System.out.println("11) Mini-Lotterie");
        System.out.println("12) Beenden");
    }

    // ===== Eingaben =====
    private static double askInitialBalance() {
        System.out.print("Gib dein Startguthaben in Euro ein (z.B. 50): ");
        while (true) {
            try {
                double val = Double.parseDouble(scanner.nextLine().trim());
                if (val <= 0) throw new NumberFormatException();
                return round2(val);
            } catch (NumberFormatException e) {
                System.out.print("Ungültige Eingabe. Bitte positive Zahl eingeben: ");
            }
        }
    }

    private static int readIntInRange(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                if (val >= min && val <= max) return val;
                System.out.printf("Bitte eine Zahl zwischen %d und %d wählen.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Ungültige Eingabe — Zahl erwartet.");
            }
        }
    }

    private static double readBet(double balance) {
        System.out.printf("Einsatz (Kontostand: €%.2f): ", balance);
        while (true) {
            try {
                double bet = Double.parseDouble(scanner.nextLine().trim());
                if (bet <= 0) {
                    System.out.print("Einsatz muss größer als 0 sein. Versuch's nochmal: ");
                } else if (bet > balance) {
                    System.out.print("Nicht genug Guthaben. Neuer Einsatz: ");
                } else {
                    return round2(bet);
                }
            } catch (NumberFormatException e) {
                System.out.print("Ungültige Zahl — Versuch's nochmal: ");
            }
        }
    }

    private static double round2(double x) {
        return Math.round(x * 100.0) / 100.0;
    }

    private static double deposit(double balance) {
        System.out.print("Wie viel möchtest du einzahlen? ");
        while (true) {
            try {
                double add = Double.parseDouble(scanner.nextLine().trim());
                if (add <= 0) {
                    System.out.print("Bitte positiven Betrag eingeben: ");
                    continue;
                }
                balance += round2(add);
                System.out.printf("Neuer Kontostand: €%.2f%n", balance);
                return round2(balance);
            } catch (NumberFormatException e) {
                System.out.print("Ungültige Eingabe — gib eine Zahl ein: ");
            }
        }
    }

    // ===== Spiele =====

    // Münzwurf
    private static double coinFlip(double balance) {
        System.out.println("\n--- Münzwurf ---");
        double bet = readBet(balance);
        System.out.print("Wähle: (1) Kopf  (2) Zahl : ");
        int pick = readIntInRange("", 1, 2);
        int result = rng.nextBoolean() ? 1 : 2;
        System.out.println("Geworfen: " + (result == 1 ? "Kopf" : "Zahl"));
        if (pick == result) {
            balance += bet;
            System.out.printf("Gewonnen! +€%.2f%n", bet);
        } else {
            balance -= bet;
            System.out.printf("Verloren. -€%.2f%n", bet);
        }
        return round2(balance);
    }

    // Slot-Maschine mit Symbolen
    private static double slotMachine(double balance) {
        System.out.println("\n--- Slot-Maschine ---");
        String[] symbols = {"🍒", "🍋", "🍉", "⭐", "💎", "🍇"};
        double bet = readBet(balance);
        int a = rng.nextInt(symbols.length);
        int b = rng.nextInt(symbols.length);
        int c = rng.nextInt(symbols.length);
        System.out.println("Walzen: [" + symbols[a] + "] [" + symbols[b] + "] [" + symbols[c] + "]");
        if (a == b && b == c) {
            double win = bet * 5;
            balance += win;
            System.out.printf("Drei Gleiche! +€%.2f%n", win);
        } else if (a == b || b == c || a == c) {
            double win = bet * 2;
            balance += win;
            System.out.printf("Zwei Gleiche! +€%.2f%n", win);
        } else {
            balance -= bet;
            System.out.printf("Kein Treffer. -€%.2f%n", bet);
        }
        return round2(balance);
    }

    // Roulette
    private static double roulette(double balance) {
        System.out.println("\n--- Roulette ---");
        System.out.println("Optionen:");
        System.out.println("1) Zahl (0–36) — Auszahlung 35:1");
        System.out.println("2) Gerade / Ungerade — Auszahlung 1:1 (0 verliert)");
        System.out.println("3) Farbe (Rot/Schwarz/Grün) — Rot/Schwarz 1:1, Grün 35:1");

        int opt = readIntInRange("Wähle Option: ", 1, 3);
        double bet = readBet(balance);
        int spun = rng.nextInt(37);
        boolean isEven = (spun % 2 == 0 && spun != 0);
        String color = getColor(spun);

        switch (opt) {
            case 1 -> {
                System.out.print("Auf welche Zahl setzt du (0–36)? ");
                int chosen = readIntInRange("", 0, 36);
                System.out.printf("Roulette dreht... Zahl: %d (%s)%n", spun, color);
                if (chosen == spun) {
                    double win = bet * 35;
                    balance += win;
                    System.out.printf("Gewonnen! +€%.2f%n", win);
                } else {
                    balance -= bet;
                    System.out.printf("Verloren. -€%.2f%n", bet);
                }
            }
            case 2 -> {
                System.out.print("Setze auf (1) Gerade  (2) Ungerade : ");
                int pick = readIntInRange("", 1, 2);
                System.out.printf("Roulette dreht... Zahl: %d (%s)%n", spun, spun == 0 ? "Grün" : isEven ? "Gerade" : "Ungerade");
                if (spun == 0) {
                    System.out.println("Grün! Haus gewinnt.");
                    balance -= bet;
                } else if ((isEven && pick == 1) || (!isEven && pick == 2)) {
                    balance += bet;
                    System.out.printf("Gewonnen! +€%.2f%n", bet);
                } else {
                    balance -= bet;
                    System.out.printf("Verloren. -€%.2f%n", bet);
                }
            }
            case 3 -> {
                System.out.print("Setze auf (1) Rot  (2) Schwarz  (3) Grün : ");
                int pick = readIntInRange("", 1, 3);
                System.out.printf("Roulette dreht... Zahl: %d (%s)%n", spun, color);
                if ((pick == 1 && color.equals("Rot")) || (pick == 2 && color.equals("Schwarz")) || (pick == 3 && color.equals("Grün"))) {
                    double win = pick == 3 ? bet * 35 : bet;
                    balance += win;
                    System.out.printf("Gewonnen! +€%.2f%n", win);
                } else {
                    balance -= bet;
                    System.out.printf("Verloren. -€%.2f%n", bet);
                }
            }
        }
        return round2(balance);
    }

    private static String getColor(int number) {
        if (number == 0) return "Grün";
        return (number % 2 == 0) ? "Schwarz" : "Rot";
    }

    // Würfelspiel
    private static double diceGame(double balance) {
        System.out.println("\n--- Würfelspiel ---");
        double bet = readBet(balance);
        int player = rng.nextInt(6) + 1;
        int house = rng.nextInt(6) + 1;
        System.out.printf("Du würfelst: %d, Haus würfelt: %d%n", player, house);
        if (player > house) {
            balance += bet;
            System.out.printf("Gewonnen! +€%.2f%n", bet);
        } else if (player < house) {
            balance -= bet;
            System.out.printf("Verloren. -€%.2f%n", bet);
        } else {
            System.out.println("Unentschieden! Einsatz zurück.");
        }
        return round2(balance);
    }

    // High-Low Kartenspiel
    private static double highLowGame(double balance) {
        System.out.println("\n--- High-Low Kartenspiel ---");
        double bet = readBet(balance);
        int firstCard = rng.nextInt(13) + 1;
        System.out.printf("Erste Karte: %d%n", firstCard);
        System.out.print("Wird die nächste Karte (1) höher oder (2) niedriger? ");
        int guess = readIntInRange("", 1, 2);
        int nextCard = rng.nextInt(13) + 1;
        System.out.printf("Nächste Karte: %d%n", nextCard);
        if ((guess == 1 && nextCard > firstCard) || (guess == 2 && nextCard < firstCard)) {
            balance += bet;
            System.out.printf("Richtig! +€%.2f%n", bet);
        } else if (nextCard == firstCard) {
            System.out.println("Gleichstand! Einsatz zurück.");
        } else {
            balance -= bet;
            System.out.printf("Falsch. -€%.2f%n", bet);
        }
        return round2(balance);
    }

    // Karten-Paar
    private static double cardsGame(double balance) {
        System.out.println("\n--- Karten-Paar ---");
        double bet = readBet(balance);
        int player = rng.nextInt(13) + 1;
        int house = rng.nextInt(13) + 1;
        System.out.printf("Deine Karte: %d, Haus-Karte: %d%n", player, house);
        if (player > house) {
            balance += bet;
            System.out.printf("Gewonnen! +€%.2f%n", bet);
        } else if (player < house) {
            balance -= bet;
            System.out.printf("Verloren. -€%.2f%n", bet);
        } else {
            System.out.println("Unentschieden! Einsatz zurück.");
        }
        return round2(balance);
    }

    // Roulette-Dutzende
    private static double rouletteDozen(double balance) {
        System.out.println("\n--- Roulette-Dutzende ---");
        double bet = readBet(balance);
        System.out.println("Wähle Dutzend: 1) 1–12  2) 13–24  3) 25–36");
        int pick = readIntInRange("", 1, 3);
        int spun = rng.nextInt(36) + 1;
        int dozen = (spun - 1) / 12 + 1;
        System.out.printf("Roulette dreht... Zahl: %d%n", spun);
        if (pick == dozen) {
            double win = bet * 2;
            balance += win;
            System.out.printf("Gewonnen! +€%.2f%n", win);
        } else {
            balance -= bet;
            System.out.printf("Verloren. -€%.2f%n", bet);
        }
        return round2(balance);
    }

    // Glücksrad
    private static double luckyWheel(double balance) {
        System.out.println("\n--- Glücksrad ---");
        double bet = readBet(balance);
        System.out.print("Wähle Zahl 1–10: ");
        int pick = readIntInRange("", 1, 10);
        int spun = rng.nextInt(10) + 1;
        System.out.printf("Glücksrad dreht... Zahl: %d%n", spun);
        if (pick == spun) {
            double win = bet * 10;
            balance += win;
            System.out.printf("Gewonnen! +€%.2f%n", win);
        } else {
            balance -= bet;
            System.out.printf("Verloren. -€%.2f%n", bet);
        }
        return round2(balance);
    }

    // Bonus-Slot (4 Walzen)
    private static double bonusSlot(double balance) {
        System.out.println("\n--- Bonus-Slot ---");
        String[] symbols = {"🍒", "🍋", "🍉", "⭐", "💎", "🍇"};
        double bet = readBet(balance);
        int[] reels = new int[4];
        for (int i = 0; i < 4; i++) reels[i] = rng.nextInt(symbols.length);
        System.out.printf("Walzen: [%s] [%s] [%s] [%s]%n",
                symbols[reels[0]], symbols[reels[1]], symbols[reels[2]], symbols[reels[3]]);
        if (reels[0] == reels[1] && reels[1] == reels[2] && reels[2] == reels[3]) {
            double win = bet * 20;
            balance += win;
            System.out.printf("Vier Gleiche! +€%.2f%n", win);
        } else if (reels[0] == reels[1] || reels[1] == reels[2] || reels[2] == reels[3] || reels[0] == reels[2] || reels[1] == reels[3] || reels[0] == reels[3]) {
            double win = bet * 3;
            balance += win;
            System.out.printf("Zwei Gleiche! +€%.2f%n", win);
        } else {
            balance -= bet;
            System.out.printf("Kein Treffer. -€%.2f%n", bet);
        }
        return round2(balance);
    }

    // Mini-Lotterie
    private static double miniLottery(double balance) {
        System.out.println("\n--- Mini-Lotterie ---");
        double bet = readBet(balance);
        int[] playerNums = new int[3];
        System.out.println("Wähle 3 Zahlen zwischen 1–10:");
        for (int i = 0; i < 3; i++) playerNums[i] = readIntInRange("Zahl " + (i + 1) + ": ", 1, 10);

        int[] draw = new int[3];
        for (int i = 0; i < 3; i++) draw[i] = rng.nextInt(10) + 1;

        System.out.printf("Gezogene Zahlen: %d, %d, %d%n", draw[0], draw[1], draw[2]);

        int matches = 0;
        for (int n : playerNums) {
            for (int d : draw) if (n == d) matches++;
        }

        if (matches == 3) {
            double win = bet * 50;
            balance += win;
            System.out.printf("3 richtige! +€%.2f%n", win);
        } else if (matches == 2) {
            double win = bet * 5;
            balance += win;
            System.out.printf("2 richtige! +€%.2f%n", win);
        } else if (matches == 1) {
            System.out.printf("1 richtige! Einsatz zurück: €%.2f%n", bet);
        } else {
            balance -= bet;
            System.out.printf("Kein Treffer. -€%.2f%n", bet);
        }

        return round2(balance);
    }
}

