import models.BaseModel;
import models.driver.Driver;
import models.order.Order;
import models.order.OrderStatus;
import models.responce.Responce;
import models.user.User;
import models.visual.GameScreen;
import models.visual.Wall;
import service.driver.DriverService;
import service.order.OrderService;
import service.user.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Order orderS;
    static Driver driver;

    public static void main(String[] args) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        UserService userService = new UserService();
        DriverService driverService = new DriverService();
        OrderService orderService = new OrderService();
        System.out.println("Murodjon usernameni bilmedi");
        int stepcode = 4;

        while (stepcode != 0) {
            System.out.println(ConsoleColors.BLACK_BOLD + "1. Sign in\t2. Sign up\t3. Sign in as driver" + ConsoleColors.RESET);
            stepcode = scannerInt.nextInt();
            if (stepcode == 1) {
                signIn(userService, driverService, orderService, scannerInt, scannerStr);
            } else if (stepcode == 2) {
                signUp(userService, driverService, orderService, scannerInt, scannerStr);
            } else {
                signForDrivers(userService, driverService, orderService, scannerInt, scannerStr);
            }
        }
    }

    public static void signUp(UserService userService, DriverService driverService, OrderService orderService, Scanner scannerInt, Scanner scannerStr) {
        User user = new User();
        String userName;

        do {
            System.out.println("Enter user_name: ");
            userName = scannerStr.next();
            if (!userService.checkUser(userName)) {
                System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "Bunday username lik akkaunt mavjud!" + ConsoleColors.RESET);
            }
        } while (!userService.checkUser(userName));

        user.setUsername(userName);
        System.out.println("Enter password: ");
        user.setPassword(scannerStr.next());
        System.out.println("Enter name: ");
        user.setName(scannerStr.next());
        System.out.println("Enter phone_number: ");
        user.setPhoneNumber(scannerStr.next());

        int xCoor = 0;
        do {
            System.out.print("Enter destination of X coordinate = ");
            xCoor = scannerInt.nextInt();
            user.setLocX(xCoor);
            if (xCoor <= 0 || xCoor > 40) {
                System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "Wrong x coordinate!" + ConsoleColors.RESET);
            }
        } while (xCoor <= 0 || xCoor > 40);

        int yCoor = 0;
        do {
            System.out.print("Enter destination of Y coordinate = ");
            yCoor = scannerInt.nextInt();
            user.setLocY(yCoor);
            if (yCoor <= 0 || yCoor > 15) {
                System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "Wrong y coordinate!" + ConsoleColors.RESET);
            }
        } while (yCoor <= 0 || yCoor > 15);

        Responce res = userService.add(user);
        user = (User) res.getData();
        System.out.println(res.message);

        menuUser(user, userService, driverService, orderService, scannerInt, scannerStr);
    }

    public static void signIn(UserService userService, DriverService driverService, OrderService orderService, Scanner scannerInt, Scanner scannerStr) {
        System.out.println("Enter user_name: ");
        String user_name = scannerStr.next();
        System.out.println("Enter password: ");
        String password = scannerStr.next();
        Responce res = userService.login(user_name, password);
        User user = (User) res.getData();
        System.out.println(res.message);
        if (user != null) {
            if (user.isAdmin()) {
                menuAdmin(user, userService, driverService, orderService, scannerInt, scannerStr);
            } else {
                menuUser(user, userService, driverService, orderService, scannerInt, scannerStr);
            }
        } else {
            System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "Username or password is not valid!" + ConsoleColors.RESET);
        }
    }

    public static void signForDrivers(UserService userService, DriverService driverService, OrderService orderService, Scanner scannerInt, Scanner scannerStr) {
        System.out.println("Enter user_name: ");
        String user_name = scannerStr.next();
        System.out.println("Enter password: ");
        String password = scannerStr.next();

        Responce res = driverService.login(user_name, password);
        Driver driver = (Driver) res.getData();
        System.out.println(res.message);
        if (driver != null) {
            menuDriver(userService, driver, driverService, orderService, scannerInt, scannerStr);
        } else {
            System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "Username or password is not valid!" + ConsoleColors.RESET);
        }
    }

    public static void menuAdmin(User user, UserService userService, DriverService driverService, OrderService orderService, Scanner scannerInt, Scanner scannerStr) {
        boolean stepcode = true;

        while (stepcode) {
            System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "***************************Admin*****************************" + ConsoleColors.RESET);

            System.out.println(ConsoleColors.BLACK_BOLD + "1. Driver section\t2. User section\t3. Order section\t0. Exit" + ConsoleColors.RESET);

            switch (scannerInt.nextInt()) {
                case 1:
                    driverSection(driverService, scannerInt, scannerStr);
                    break;
                case 2:
                    userSection(userService, scannerInt);
                    break;
                case 3:
                    orderSection(orderService, scannerInt);
                    break;
                default:
                    stepcode = false;
            }
        }
    }

    public static void driverSection(DriverService driverService, Scanner scannerInt, Scanner scannerStr) {
        boolean stepcode = true;

        while (stepcode) {
            int idx;
            int keyId;
            Responce res;
            System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "************************Driver section***********************" + ConsoleColors.RESET);

            idx = 1;
            List<Driver> dlist = driverService.getList();

            if (dlist.size() > 0) {
                for (Driver driver1 : dlist) {
                    if (driver1.isActive()) {
                        System.out.println(ConsoleColors.GREEN + "id:" + (idx++) + " | " + driver1.toString() + ConsoleColors.RESET);
                    } else {
                        System.out.println(ConsoleColors.RED + "id:" + (idx++) + " | " + driver1.toString() + ConsoleColors.RESET);

                    }
                    System.out.println("===================================");
                }
            } else {
                System.out.println("The list is empty!");
            }

            System.out.println(ConsoleColors.BLACK_BOLD + "1. Add driver\t2. Make inactive\t3. Make active\t0. Exit" + ConsoleColors.RESET);
            int commandId = scannerInt.nextInt();

            switch (commandId) {
                case 1:
                    System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "************************Add driver***************************" + ConsoleColors.RESET);

                    Driver driver = new Driver();
                    System.out.println("Enter driver name: ");
                    driver.setName(scannerStr.next());
                    System.out.println("Enter driver phone number: ");
                    driver.setPhoneNumber(scannerStr.next());
                    System.out.println("Enter car number: ");
                    driver.setCarNumber(scannerStr.next());
                    System.out.println("Enter driver username: ");
                    driver.setUsername(scannerStr.next());
                    System.out.println("Enter driver password: ");
                    driver.setPassword(scannerStr.next());
                    int xCoor = 0;
                    do {
                        System.out.print("Enter destination of X coordinate = ");
                        xCoor = scannerInt.nextInt();
                        driver.setLocX(xCoor);
                        if (xCoor <= 0 || xCoor > 40) {
                            System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "Wrong x coordinate!" + ConsoleColors.RESET);
                        }
                    } while (xCoor <= 0 || xCoor > 40);
                    int yCoor = 0;
                    do {
                        System.out.print("Enter destination of Y coordinate = ");
                        yCoor = scannerInt.nextInt();
                        driver.setLocY(yCoor);
                        if (yCoor <= 0 || yCoor > 15) {
                            System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "Wrong y coordinate!" + ConsoleColors.RESET);
                        }
                    } while (yCoor <= 0 || yCoor > 15);
                    driverService.add(driver);
                    break;
                case 2:
                    System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "************************Delete driver************************" + ConsoleColors.RESET);

                    dlist = driverService.getList();
                    idx = 1;
                    for (Driver driver1 : dlist) {
                        if (driver1.isActive()) {
                            System.out.println(ConsoleColors.GREEN + "id:" + (idx++) + driver1.toString());
                        } else {
                            System.out.println(ConsoleColors.RED + "id:" + (idx++) + driver1.toString());
                        }
                        System.out.println("===================================");
                    }
                    System.out.println(ConsoleColors.RED_BOLD + "Enter driver's id: " + ConsoleColors.RESET);
                    keyId = scannerInt.nextInt();
                    if (dlist.size() > 0 && keyId > 0 && keyId <= dlist.size()) {
                        res = driverService.delete(dlist.get(keyId - 1).getId());
                        System.out.println(res.message);
                    } else {
                        System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "Driver with this id is not found!" + ConsoleColors.RESET);
                    }
                    break;
                case 3:
                    System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "************************Edit driver**************************" + ConsoleColors.RESET);

                    dlist = driverService.getList();
                    idx = 1;
                    for (Driver driver1 : dlist) {
                        if (driver1.isActive()) {
                            System.out.println((ConsoleColors.GREEN) + "id=" + (idx++) + driver1.toString() + ConsoleColors.RESET);
                        } else {
                            System.out.println((ConsoleColors.RED) + "id=" + (idx++) + driver1.toString() + ConsoleColors.RESET);
                        }
                        System.out.println(ConsoleColors.RESET + "===================================");
                    }

                    System.out.println(ConsoleColors.GREEN_BOLD + "Enter driver's id: " + ConsoleColors.RESET);
                    keyId = scannerInt.nextInt();

                    if (dlist.size() > 0 && keyId > 0 && keyId <= dlist.size()) {
                        boolean isSucced = driverService.edit(dlist.get(keyId - 1).getId());
                        if (isSucced) {
                            System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT + "The unactived driver is actived!" + ConsoleColors.RESET);
                        } else {
                            System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "The unactived driver is not actived!" + ConsoleColors.RESET);
                        }
                    } else {
                        System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "Driver with this id is not found!" + ConsoleColors.RESET);
                    }
                    break;
                default:
                    stepcode = false;
                    break;
            }
        }
    }

    public static void userSection(UserService userService, Scanner scannerInt) {
        System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "************************User section*************************" + ConsoleColors.RESET);

        List<User> ulist = userService.getList();
        int idx = 1;
        for (User user : ulist) {
            if (user.isActive()) {
                System.out.println((ConsoleColors.GREEN) + "id=" + (idx++) + user.toString() + ConsoleColors.RESET);
            } else {
                System.out.println((ConsoleColors.RED) + "id=" + (idx++) + user.toString() + ConsoleColors.RESET);
            }
            System.out.println(ConsoleColors.RESET + "===================================");
        }

        System.out.println(ConsoleColors.BLACK_BOLD + "1. Make inactive\t2. Make active\t0. Exit" + ConsoleColors.RESET);
        int commandId = scannerInt.nextInt();

        if (commandId > 0 && commandId < 3) {
            System.out.println(ConsoleColors.GREEN_BOLD + "Enter user's id: " + ConsoleColors.RESET);
            int keyId = scannerInt.nextInt();

            if (ulist.size() > 0 && keyId > 0 && keyId <= ulist.size()) {
                boolean isSucced;
                if (commandId == 1) {
                    isSucced = userService.delete(ulist.get(keyId - 1).getId()).isOk();
                } else {
                    isSucced = userService.edit(ulist.get(keyId - 1).getId());
                }

                if (isSucced) {
                    System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT + "The unactived user is actived!" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "The unactived user is not actived!" + ConsoleColors.RESET);
                }
            } else {
                System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "User with this id is not found!" + ConsoleColors.RESET);
            }
        }
    }

    public static void orderSection(OrderService orderService, Scanner scannerInt) {
        System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "************************Order section************************" + ConsoleColors.RESET);

        List<Order> olist = orderService.getList();
        if (olist.size() > 0) {
            int idx = 1;
            for (Order order : olist) {
                if (order.isActive()) {
                    System.out.println((ConsoleColors.GREEN) + "id=" + (idx++) + order.toString() + ConsoleColors.RESET);
                } else {
                    System.out.println((ConsoleColors.RED) + "id=" + (idx++) + order.toString() + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.RESET + "===================================");
            }
        } else {
            System.out.println("The list is empty!");
        }

        System.out.println(ConsoleColors.BLACK_BOLD + "1. Make inactive\t2. Make active\t0. Exit" + ConsoleColors.RESET);
        int commandId = scannerInt.nextInt();

        if (commandId > 0 && commandId < 3) {
            System.out.println(ConsoleColors.GREEN_BOLD + "Enter order's id: " + ConsoleColors.RESET);
            int keyId = scannerInt.nextInt();

            if (olist.size() > 0 && keyId > 0 && keyId <= olist.size()) {
                boolean isSucced;
                if (commandId == 1) {
                    isSucced = orderService.delete(olist.get(keyId - 1).getId()).isOk();
                } else {
                    isSucced = orderService.edit(olist.get(keyId - 1).getId());
                }

                if (isSucced) {
                    System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT + "The unactived order is actived!" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "The unactived order is not actived!" + ConsoleColors.RESET);
                }
            } else {
                System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "Order with this id is not found!" + ConsoleColors.RESET);
            }
        }
    }

    public static void menuUser(User user, UserService userService, DriverService driverService, OrderService orderService, Scanner scannerInt, Scanner scannerStr) {
        boolean stepcode = true;
        while (stepcode) {
            System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "***************************User******************************" + ConsoleColors.RESET);

            Responce res = null;
            Order order = orderService.getOrderByUser(user.getId());

            if (order != null) {
                Driver driver = null;
                if (order.getDriverId() != null) {
                    driver = driverService.getDriver(order.getDriverId());
                    System.out.println("driver name: " + driver.getName() + "\ndriver car number: " + driver.getCarNumber() + "\ndriver phone number" + driver.getPhoneNumber() + order.toString());
                } else {
                    System.out.println(order.toString());
                }
                location(user.getLocX(), user.getLocY(), order.getFinalX(), order.getFinalY());
            }
            //System.out.println("name: " + user.getName() + "\nphone number: " + user.getPhoneNumber() + "\nusername: " + user.getUsername() + "\npassword: " + user.getPassword());
            //System.out.println("====================================================");


            System.out.println(ConsoleColors.BLACK_BOLD + "1. Order taxi\t2. History\t3. My profile\t4. My location\t 5.Order status \t0. Exit" + ConsoleColors.RESET);

            switch (scannerInt.nextInt()) {
                case 1:
                    System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "**************************Order taxi*************************" + ConsoleColors.RESET);

                    if (order == null) {
                        order = new Order();
                        order.setUserId(user.getId());
                        int xCoor = 0;
                        do {
                            System.out.print("Enter destination of X coordinate = ");
                            xCoor = scannerInt.nextInt();
                            order.setFinalX(xCoor);
                            if (xCoor <= 0 || xCoor > 40) {
                                System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "Wrong x coordinate!" + ConsoleColors.RESET);
                            }
                        } while (xCoor <= 0 || xCoor > 40);
                        int yCoor = 0;
                        do {
                            System.out.print("Enter destination of Y coordinate = ");
                            yCoor = scannerInt.nextInt();
                            order.setFinalY(yCoor);
                            if (yCoor <= 0 || yCoor > 15) {
                                System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "Wrong y coordinate!" + ConsoleColors.RESET);
                            }
                        } while (yCoor <= 0 || yCoor > 15);

                        Driver driver1 = driverService.getDriver(user);
                        if(driver1 != null) {
                            order.setDriverId(driver1.getId());
                            order.setStatus(OrderStatus.NEW);
                            res = orderService.add(order);
//                            setOrderS(order);
//                            setDriver(driver1);

                            preloading(7);

                            System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT + "Waiting for accepting driver" + ConsoleColors.RESET);
                        } else {
                            System.out.println(ConsoleColors.RED_BACKGROUND_BRIGHT + "Driver is unavailable!" + ConsoleColors.RESET);
                        }
                    } else {
                        System.out.println("You already ordered!");
                    }

                    break;
                case 2:
                    System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "***************************History***************************" + ConsoleColors.RESET);

                    List<Order> orderl = orderService.getList(user.getId());
                    if (orderl.size() > 0) {
                        int idx = 1;

                        for (Order order2 : orderl) {
                            Driver driver1 = driverService.getDriver(order2.getDriverId());
                            System.out.println("id: " + idx++ + driver1 != null ? ("\ndriver name: " + driver1.getName() + "\nphone number: " + driver1.getPhoneNumber() + "\ncar number: " + driver1.getCarNumber()) : "" + order2.toString());
                            System.out.println("====================================================");
                        }
                    } else {
                        System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT + "The history is empty!" + ConsoleColors.RESET);
                    }

                    break;
                case 3:
                    System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "**************************My profile*************************" + ConsoleColors.RESET);

                    System.out.println("name: " + user.getName() + "\nphone number: " + user.getPhoneNumber() + "\nusername: " + user.getUsername() + "\npassword: " + user.getPassword());
                    System.out.println("====================================================");
                    break;
                case 4:
                    System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "*************************My location*************************" + ConsoleColors.RESET);

                    location(user.getLocY(), user.getLocX(), user);
                    break;
                case 5:
                    System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "************************Order status*************************" + ConsoleColors.RESET);

                    Order order1 = orderService.getOrderByUser(user.getId());
                    //if (getOrderS() != null) {
                    if(order1 != null) {
                        //Order order1 = orderService.getOne(getOrderS().getId());

                        if (order1.getStatus() == OrderStatus.ACCEPT) {
                            preloading(10);
                            Driver driver1 = driverService.getOne(order1.getDriverId());
                            //if (getDriver() != null) {
                            if(driver1 != null) {
                                System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT + "Driver accepted" + ConsoleColors.RESET);
//                                visualDriver(getDriver(), order1, user);
                                visualDriver(driver1, order1, user);

                                order1.setStatus(OrderStatus.FINISH);
                            } else {
                                System.out.println(ConsoleColors.YELLOW_BACKGROUND + "Driver is not accepted" + ConsoleColors.RESET);
                            }
                        } else {
                            System.out.println("Driver not accepted");
                        }
                    } else {
                        System.out.println("Order is not valid");
                    }
                    break;
                default:
                    stepcode = false;
                    break;
            }
        }
    }

    public static void menuDriver(UserService userService, Driver driver, DriverService driverService, OrderService orderService, Scanner scannerInt, Scanner scannerStr) {
        boolean stepcode = true;

        while (stepcode) {
            System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "****************************Driver***************************" + ConsoleColors.RESET);

            System.out.println(ConsoleColors.BLACK_BOLD + "1. Message\t2. History\t3. My profile\t4. My location\t0. Exit" + ConsoleColors.RESET);

            switch (scannerInt.nextInt()) {
                case 1:
                    System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "***************************Message***************************" + ConsoleColors.RESET);

                    Order orderNew = orderService.getNewOrder(driver.getId());
                    if (orderNew != null) {
                        System.out.println(orderNew.toString());
                        System.out.println(ConsoleColors.BLACK_BOLD + "1. Accept\t2. Cancel" + ConsoleColors.RESET);
                        if (scannerInt.nextInt() == 1) {
                            driverService.accept(orderNew.getId());
                            System.out.println("The order is accepted");
                        } else {
                            driver.setFree(false);
                            orderService.cancelOrder(orderNew.getId());

                            User userOrdered = userService.getOne(orderNew.getUserId());
                            Driver driverToOrder = driverService.getDriver(userOrdered);

                            if (driverToOrder != null)
                                orderNew.setDriverId(driverToOrder.getId());

                            driver.setFree(true);
                            System.out.println("The order is canceled");
                        }
                    } else {
                        System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT + "No new message!" + ConsoleColors.RESET);
                    }
                    break;
                case 2:
                    System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "***************************History***************************" + ConsoleColors.RESET);

                    List<Order> orderl = orderService.getList(driver.getId());

                    if (orderl.size() > 0) {
                        int idx = 1;

                        for (Order order2 : orderl) {
                            //Driver driver1 = driverService.getDriver(order2.getDriverId());
                            System.out.println("id: " + idx++ + order2.toString());
                            System.out.println("====================================================");
                        }
                    } else {
                        System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT + "The history is empty!" + ConsoleColors.RESET);
                    }
                    break;
                case 3:
                    System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "*************************My profile**************************" + ConsoleColors.RESET);

                    System.out.println("name: " + driver.getName() + "\nphone number: " + driver.getPhoneNumber() + "\nusername: " + driver.getUsername() + "\npassword: " + driver.getPassword() + "\nstatus: " + driver.isFree());
                    System.out.println("====================================================");
                    break;
                case 4:
                    System.out.println(ConsoleColors.CYAN_BACKGROUND_BRIGHT + "*************************My location*************************" + ConsoleColors.RESET);

                    location(driver.getLocY(), driver.getLocX(), driver);
                    break;
                default:
                    stepcode = false;
                    break;
            }
        }
    }

    public static void location(int locX, int locY, BaseModel baseModel) {
        String iconP = "\uD83E\uDDCD";
        String iconD = "\uD83D\uDE95";

        for (int i = 0; i <= 15; i++) {
            for (int j = 0; j <= 40; j++) {
                if (i == locX && j == locY) {
                    if (baseModel instanceof Driver) {
                        System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT + iconD + ConsoleColors.RESET);
                    } else if (baseModel instanceof User) {
                        System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT + iconP + ConsoleColors.RESET);
                    } else {
                        System.out.print("o");
                    }
                    j++;
                    continue;
                }
                if (i == 0 || i == 15 || j == 0 || j == 40)
                    System.out.print("#");
                else
                    System.out.print(" ");

            }
            System.out.println("");
        }
    }

    public static void location(int locX, int locY, int finX, int finY) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 40; j++) {
                if (i == locX && j == locY) {
                    System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT + "\uD83E\uDDCD" + ConsoleColors.RESET); // person
                    continue;
                }
                //System.out.print("\uD83D\uDE95"); // car
                if (i == finX && j == finY) {
                    System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT + "\uD83D\uDEA9" + ConsoleColors.RESET); // destination
                    continue;
                }
                if (i == 0 || i == 14 || j == 0 || j == 39)
                    System.out.print("#");
                else
                    System.out.print(" ");

            }
            System.out.println("");
        }
    }

    public static void preloading(int mills) {
        char[] animationChars = new char[]{'|', '/', '-', '\\'};
        for (int i = 0; i <= 100; i++) {
            System.out.print("Processing: " + i + "% " + animationChars[i % 4] + "\r");
            try {
                Thread.sleep(mills);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void visualDriver(Driver driver, Order order, User user) {
        driver.setSymbol('D');
        order.setSymbol('F');
        user.setSymbol('C');

        final int SCREEN_WIDTH = 41; // Columns
        final int SCREEN_HEIGHT = 16; // Rows


        GameScreen screen = new GameScreen(SCREEN_WIDTH, SCREEN_HEIGHT);
        screen.InitScreen();


        Wall wall = new Wall('#');
        wall.addWallsRow(screen, wall, 0); // First row
        wall.addWallsRow(screen, wall, screen.getScreenHeight() - 1); // Last

        wall.addWallsColumn(screen, wall, 0); // First column
        wall.addWallsColumn(screen, wall, screen.getScreenWidth() - 1); // Last

        screen.setObjectOnLocation(driver, driver.getLocX(), driver.getLocY());
        user.addLocation(screen, user);
        order.addLocation(screen, order);

        boolean isArrived = true;
        while (isArrived) {
            if (driver.getLocX() == user.getLocX() && driver.getLocY() == user.getLocY()) isArrived = false;
            System.out.print(ConsoleColors.RED_BOLD + "\033[H\033[2J" + ConsoleColors.RESET);
            System.out.flush();
            screen.PrintScreen();
            if (driver.getLocX() < user.getLocX()) {
                driver.moveRight(screen, driver);
            } else if (driver.getLocX() > user.getLocX()) {
                driver.moveLeft(screen, driver);
            } else if (driver.getLocY() < user.getLocY()) {
                driver.moveDown(screen, driver);
            } else if (driver.getLocY() > user.getLocY()) {
                driver.moveUp(screen, driver);
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        boolean isArrived1 = true;
        while (isArrived1) {
            if (driver.getLocX() == order.getFinalX() && driver.getLocY() == order.getFinalY()) isArrived1 = false;
            System.out.print("\033[H\033[2J");
            System.out.flush();
            screen.PrintScreen();
            if (driver.getLocX() < order.getFinalX()) {
                driver.moveRight(screen, driver);
            } else if (driver.getLocX() > order.getFinalX()) {
                driver.moveLeft(screen, driver);
            } else if (driver.getLocY() < order.getFinalY()) {
                driver.moveDown(screen, driver);
            } else if (driver.getLocY() > order.getFinalY()) {
                driver.moveUp(screen, driver);
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    public static Order getOrderS() {
//        return orderS;
//    }
//
//    public static void setOrderS(Order orderS) {
//        Main.orderS = orderS;
//    }
//
//    public static Driver getDriver() {
//        return driver;
//    }
//
//    public static void setDriver(Driver driver) {
//        Main.driver = driver;
//    }
}
