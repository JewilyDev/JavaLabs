import java.util.*;

public class CinemaSystem {
    private List<Cinema> cinemas;

    public CinemaSystem() {
        cinemas = new ArrayList<>();
    }


    public void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void chooseAccount() {
        Scanner in = new Scanner(System.in);

        System.out.println("Выберите учетную запись: ");
        System.out.println("0: Администратор");
        System.out.println("1: Пользователь");
        int choice = in.nextInt();

        switch (choice) {
            case 0: {
                System.out.println("Вы вошли учетную запись администратора.");
                this.loginAsAdmin();
                break;
            }
            case 1: {
                System.out.println("Вы вошли учетную запись пользователя.");
                this.loginAsUser();
                break;
            }
        }

        in.close();
    }

    private void loginAsAdmin() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить кинотеатр");
            System.out.println("2. Добавить зал в кинотеатр");
            System.out.println("3. Добавить сеанс в зал");
            System.out.println("4. Сменить учетную запись");
            System.out.println("5. Выход");

            int choice = in.nextInt();
            in.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Введите название кинотеатра:");
                    String name = in.nextLine();
                    Cinema cinema = new Cinema(name);
                    this.addCinema(cinema);
                    System.out.println("Кинотеатр "+ name +" успешно добавлен.");
                    break;
                case 2:
                    System.out.println("Введите название кинотеатра, в который нужно добавить зал:");
                    String cinemaToAddHall = in.nextLine();

                    System.out.println("Введите название зала:");
                    String hallName = in.nextLine();

                    System.out.println("Введите количество рядов:");
                    int rows_2 = in.nextInt();

                    System.out.println("Введите количество мест в ряду:");
                    int seatsPerRow_2 = in.nextInt();

                    Hall hall = new Hall(hallName, rows_2, seatsPerRow_2);
                    for (Cinema c : this.getCinemas()) {
                        if (c.getName().equals(cinemaToAddHall)) {
                            c.addHall(hall);
                            System.out.println("Зал добавлен в кинотеатр " + cinemaToAddHall);
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Введите название кинотеатра:");
                    String cinemaName = in.nextLine();

                    System.out.println("Введите название зала:");
                    String hallNameForSession = in.nextLine();

                    System.out.println("Введите название фильма:");
                    String movieName = in.nextLine();

                    System.out.println("Введите длительность фильма (в минутах):");
                    int duration = in.nextInt();

                    System.out.println("Введите время начала сеанса (в миллисекундах (timestamp)):");
                    long startTimeInMillis = in.nextLong();

                    int[][] bookedSeats = null;

                    for (Cinema c : this.getCinemas()) {
                        if (c.getName().equals(cinemaName)) {
                            for (Hall h : c.getHalls()) {
                                if (h.getName().equals(hallNameForSession)) {
                                    int rows_3 = h.getSeatLayout().length;
                                    int seatsPerRow_3 = h.getSeatLayout()[0].length;
                                    bookedSeats = new int[rows_3][seatsPerRow_3];
                                    Session session = new Session(movieName, duration, new Date(startTimeInMillis), bookedSeats);
                                    h.addSession(session);
                                    System.out.println("Сеанс добавлен в зал " + hallNameForSession + " кинотеатра " + cinemaName);
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                case 4:
                    chooseAccount();
                case 5:
                    System.out.println("Выход из программы.");
                    in.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Команда не найдена. Попробуйте снова.");
            }
        }
    }

    private void loginAsUser() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Показать ближайший сеанс фильма");
            System.out.println("2. Показать план зала на сеанс");
            System.out.println("3. Сменить учетную запись");
            System.out.println("4. Забронировать место");
            System.out.println("5. Выход");

            int choice = in.nextInt();
            in.nextLine();  // Чтобы считать перевод строки после ввода числа.

            switch (choice) {
                case 1:
                    System.out.println("Введите название фильма:");
                    String movieToFind = in.nextLine();

                    Session nearestSession = null;
                    long currentTime = System.currentTimeMillis();
                    long minTimeDifference = Long.MAX_VALUE;

                    for (Cinema c : this.getCinemas()) {
                        for (Hall h : c.getHalls()) {
                            for (Session s : h.getSessions()) {
                                if (s.getMovieName().equals(movieToFind) && s.getStartTime().getTime() > currentTime) {
                                    long timeDifference = s.getStartTime().getTime() - currentTime;
                                    if (timeDifference < minTimeDifference) {
                                        nearestSession = s;
                                        minTimeDifference = timeDifference;
                                    }
                                }
                            }
                        }
                    }

                    if (nearestSession != null) {
                        System.out.println("Ближайший сеанс фильма " + movieToFind + " начнется в " + nearestSession.getStartTime());
                    } else {
                        System.out.println("Сеансы фильма " + movieToFind + " не найдены.");
                    }
                    break;
                case 2:
                    System.out.println("Введите название кинотеатра:");
                    String cinemaNameToPrintPlan = in.nextLine();

                    System.out.println("Введите название зала:");
                    String hallNameToPrintPlan = in.nextLine();

                    for (Cinema c : this.getCinemas()) {
                        if (c.getName().equals(cinemaNameToPrintPlan)) {
                            for (Hall h : c.getHalls()) {
                                if (h.getName().equals(hallNameToPrintPlan)) {
                                    int[][] seatLayout = h.getSeatLayout();
                                    System.out.println("План зала " + hallNameToPrintPlan + " в кинотеатре " + cinemaNameToPrintPlan + ":");
                                    for (int[] row : seatLayout) {
                                        for (int seat : row) {
                                            if (seat == 0) {
                                                System.out.print("O ");
                                            } else {
                                                System.out.print("X ");
                                            }
                                        }
                                        System.out.println();
                                    }
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                case 3:
                    chooseAccount();
                case 4:
                    System.out.println("Введите название кинотеатра:");
                    String cinemaNameToBook = in.nextLine();

                    System.out.println("Введите название зала:");
                    String hallNameToBook = in.nextLine();

                    System.out.println("Введите название фильма:");
                    String movieNameToBook = in.nextLine();

                    long currentTime_4 = System.currentTimeMillis();

                    for (Cinema c : this.getCinemas()) {
                        if (c.getName().equals(cinemaNameToBook)) {
                            for (Hall h : c.getHalls()) {
                                if (h.getName().equals(hallNameToBook)) {
                                    for (Session s : h.getSessions()) {
                                        if (s.getMovieName().equals(movieNameToBook) && s.getStartTime().getTime() > currentTime_4) {
                                            int[][] seats = s.getBookedSeats();

                                            int rowToBook = -1;
                                            int seatToBook = -1;
                                            boolean seatFound = false;
                                            for (int row = 0; row < seats.length; row++) {
                                                for (int seat = 0; seat < seats[row].length; seat++) {
                                                    if (seats[row][seat] == 0) {
                                                        rowToBook = row;
                                                        seatToBook = seat;
                                                        seatFound = true;
                                                        break;
                                                    }
                                                }

                                                if(seatFound) {
                                                    break;
                                                }
                                            }

                                            if (!seatFound) {
                                                System.out.println("Свободных мест нет.");
                                                break;
                                            }

                                            s.bookSeat(rowToBook, seatToBook);

                                            int[][] seatsNew = s.getBookedSeats();
                                            for (int[] row : seatsNew) {
                                                for (int seat : row) {
                                                    if (seat == 0) {
                                                        System.out.print("O ");
                                                    } else {
                                                        System.out.print("X ");
                                                    }
                                                }
                                                System.out.println();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 5:
                    System.out.println("Выход из программы.");
                    in.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Команда не найдена. Попробуйте снова.");
            }
        }
    }
}
