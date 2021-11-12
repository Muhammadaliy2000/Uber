import com.sun.org.apache.xpath.internal.operations.Or;
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

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Order orderS ;
    static Driver driver;
    public static void main(String[] args) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);

        UserService userService = new UserService();
        DriverService driverService = new DriverService();
        OrderService orderService = new OrderService();

        int stepcode = 4;

        while (stepcode != 0) {
            System.out.println(ConsoleColors.BLACK_BOLD+"1. Sign in\t2. Sign up\t3. Sign in as driver"+ConsoleColors.RESET);
            stepcode = scannerInt.nextInt();
            User user;
            Driver driver;
            if (stepcode == 1) {
                System.out.println("Enter user_name: ");
                String user_name = scannerStr.next();
                System.out.println("Enter password: ");
                String password = scannerStr.next();
                Responce res = userService.login(user_name, password);
                user = (User) res.getData();
                System.out.println(res.message);
                if (user != null) {
                    if (user.isAdmin()) {
                        System.out.println("admin");
                        menuAdmin(user, userService, driverService, orderService, scannerInt, scannerStr);
                    } else {
                        System.out.println("user");
                        menuUser(user, userService, driverService, orderService, scannerInt, scannerStr);
                    }
                } else {
                    System.out.println("Username or password is not valid!");
                }
            } else if (stepcode == 2) {
                user = new User();
                String userName;
                do {
                    System.out.println("Enter user_name: ");
                    userName = scannerStr.next();
                    if (!userService.checkUser(userName)) {
                        System.out.println("Bunday username lik akkaunt mavjud!");
                    }
                } while (!userService.checkUser(userName));

                user.setUsername(userName);
                System.out.println("Enter password: ");
                String password = scannerStr.next();
                user.setPassword(password);
                System.out.println("Enter name: ");
                String name = scannerStr.next();
                user.setName(name);
                System.out.println("Enter phone_number: ");
                String phoneNumber = scannerStr.next();
                user.setPhoneNumber(phoneNumber);

                int xCoor = 0;
                do {
                    System.out.print("Enter destination of X coordinate = ");
                    xCoor = scannerInt.nextInt();
                    user.setLocX(xCoor);
                    if (xCoor < 0 || xCoor > 40) {
                        System.out.println("Wrong x coordinate!");
                    }
                } while (xCoor < 0 || xCoor > 40);
                int yCoor = 0;
                do {
                    System.out.print("Enter destination of Y coordinate = ");
                    yCoor = scannerInt.nextInt();
                    user.setLocY(yCoor);
                    if (yCoor < 0 || yCoor > 15) {
                        System.out.println("Wrong y coordinate!");
                    }
                } while (yCoor < 0 || yCoor > 15);

                Responce res = userService.add(user);
                user = (User) res.getData();
                System.out.println(res.message);

                System.out.println("user");
                menuUser(user, userService, driverService, orderService, scannerInt, scannerStr);
            } else {
                System.out.println("Enter user_name: ");
                String user_name = scannerStr.next();
                System.out.println("Enter password: ");
                String password = scannerStr.next();

                Responce res = driverService.login(user_name, password);
                driver = (Driver) res.getData();
                System.out.println(res.message);
                if (driver != null) {
                    System.out.println("Driver");
                    menuDriver(userService, driver, driverService, orderService, scannerInt, scannerStr);
                } else {
                    System.out.println("Username or password is not valid!");
                }
            }
        }

//
//        System.out.println("Processing: Done!");
//        GameScreen screen = new GameScreen(40, 15);
//        Driver driver = new Driver("awd","awd","w1","3",
//                                    (int) (Math.random() * (screen.getScreenWidth() - 1)),
//                                    (int) (Math.random() * (screen.getScreenHeight() - 1)),
//                                    "dqw",true);
//
//        User user = new User("wd","aw","fs","fe",
//                            (int) (Math.random() * (screen.getScreenWidth() - 1)),
//                            (int) (Math.random() * (screen.getScreenHeight() - 1)));
//
//        Order order = new Order(user, driver, OrderStatus.NEW,
//                            (int) (Math.random() * (screen.getScreenWidth() - 1)),
//                            (int) (Math.random() * (screen.getScreenHeight() - 1)));
//
//        visualDriver(driver,order,user);
    }

    public static void menuAdmin(User user, UserService userService, DriverService driverService, OrderService orderService, Scanner scannerInt, Scanner scannerStr) {
        boolean stepcode = true;

        while (stepcode) {
            System.out.println(ConsoleColors.BLACK_BOLD + "1. Add driver\t2. Delete driver\t3. Edit driver\t4. Show driver list\t0. Exit" + ConsoleColors.RESET);
            List<Driver> dlist;
            int idx;
            int keyId;
            Responce res;

            switch (scannerInt.nextInt()) {
                case 1:
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
                        if (xCoor <= 0 || xCoor >= 40) {
                            System.out.println("Wrong x coordinate!");
                        }
                    } while (xCoor <= 0 || xCoor >= 40);
                    int yCoor = 0;
                    do {
                        System.out.print("Enter destination of Y coordinate = ");
                        yCoor = scannerInt.nextInt();
                        driver.setLocY(yCoor);
                        if (yCoor <= 0 || yCoor >= 15) {
                            System.out.println("Wrong y coordinate!");
                        }
                    } while (yCoor <= 0 || yCoor >= 15);
                    driverService.add(driver);
                    break;
                case 2:
                    dlist = driverService.getList();
                    idx = 1;
                    for (Driver driver1 : dlist) {
                        if(driver1.isActive()) {
                            System.out.println(ConsoleColors.GREEN_BOLD + "id:" + (idx++) + driver1.toString());
                        } else {
                            System.out.println(ConsoleColors.RED_BOLD + "id:" + (idx++) + driver1.toString());
                        }
                        System.out.println("===================================");
                    }
                    System.out.println(ConsoleColors.RED_BOLD + "Enter driver's id: " + ConsoleColors.RESET);
                    keyId = scannerInt.nextInt();

                    res = driverService.delete(dlist.get(keyId - 1).getId());
                    System.out.println(res.message);

                    break;
                case 3:
                    dlist = driverService.getList();
                    idx = 1;
                    for (Driver driver1 : dlist) {
                        if(driver1.isActive()) {
                            System.out.println((ConsoleColors.GREEN)+ "id=" + (idx++) + driver1.toString() + ConsoleColors.RESET);
                        } else {
                            System.out.println((ConsoleColors.RED)+ "id=" + (idx++) + driver1.toString() + ConsoleColors.RESET);
                        }
                        System.out.println(ConsoleColors.RESET + "===================================");
                    }
                    System.out.println(ConsoleColors.GREEN_BOLD + "Enter driver's id: " + ConsoleColors.RESET);
                    keyId = scannerInt.nextInt();

                    boolean isSucced = driverService.edit(dlist.get(keyId - 1).getId());
                    if (isSucced) {
                        System.out.println("The unactived driver is actived!");
                    } else {
                        System.out.println("The unactived driver is not actived!");
                    }
                    break;
                case 4:
                    dlist = driverService.getList();
                    idx = 1;
                    for (Driver driver1 : dlist) {
                        if(driver1.isActive()) {
                            System.out.println(ConsoleColors.GREEN_BOLD + "id:" + (idx++) + " | " + driver1.toString() + ConsoleColors.RESET);
                        } else {
                            System.out.println(ConsoleColors.RED_BOLD + "id:" + (idx++) + " | " + driver1.toString() + ConsoleColors.RESET);

                        }
                        System.out.println("===================================");
                    }
                    break;
                default:
                    stepcode = false;
            }
        }
    }

    public static void menuUser(User user, UserService userService, DriverService driverService, OrderService orderService, Scanner scannerInt, Scanner scannerStr) {
        boolean stepcode = true;
        while (stepcode) {

            Order order = orderService.getOrder(user.getId());

            if (order != null) {

                Driver driver = null;
                if (order.getDriverId() != null) {
                    driver = driverService.getDriver(order.getDriverId());
                }
                System.out.println(order.getDriverId() != null ? ("driver name" + driver.getName()) : "" + order.toString());
                location(user.getLocX(), user.getLocY(), order.getFinalX(), order.getFinalY());

            }
            //System.out.println("name: " + user.getName() + "\nphone number: " + user.getPhoneNumber() + "\nusername: " + user.getUsername() + "\npassword: " + user.getPassword());
            System.out.println("====================================================");


            System.out.println(ConsoleColors.BLACK_BOLD +"1. Order taxi\t2. History\t3. My profile\t4. My location\t 5.Order status \t0. Exit"+ConsoleColors.RESET);
            Driver driver = null;
            switch (scannerInt.nextInt()) {
                case 1:
                    if (order == null) {
                        order = new Order();
                        order.setUserId(user.getId());
                        int xCoor = 0;
                        do {
                            System.out.print("Enter destination of X coordinate = ");
                            xCoor = scannerInt.nextInt();
                            order.setFinalX(xCoor);
                            if (xCoor <= 0 || xCoor >= 40) {
                                System.out.println("Wrong x coordinate!");
                            }
                        } while (xCoor < 0 || xCoor > 40);
                        int yCoor = 0;
                        do {
                            System.out.print("Enter destination of Y coordinate = ");
                            yCoor = scannerInt.nextInt();
                            order.setFinalY(yCoor);
                            if (yCoor <= 0 || yCoor >= 15) {
                                System.out.println("Wrong y coordinate!");
                            }
                        } while (yCoor < 0 || yCoor > 15);

                        Driver driver1 = driverService.getDriver(user);
                        order.setDriverId(driver1.getId());
                        order.setStatus(OrderStatus.NEW);
                        Responce res = orderService.addOrder(order);
                        setOrderS(order);
                        setDriver(driver1);
                        System.out.println("Waiting for accepting driver");
                    } else {
                        System.out.println("You already ordered!");
                    }

                    break;
                case 2:
                    List<Order> orderl = orderService.getList(user.getId());

                    int idx = 1;

                    for (Order order2 : orderl) {
                        Driver driver1 = driverService.getDriver(order2.getDriverId());
                        System.out.println("id: " + idx++ + driver1 != null ? ("\ndriver name: " + driver1.getName() + "\nphone number: " + driver1.getPhoneNumber() + "\ncar number: " + driver1.getCarNumber()) : "" + order2.toString());
                        System.out.println("====================================================");
                    }

                    break;
                case 3:
                    System.out.println("name: " + user.getName() + "\nphone number: " + user.getPhoneNumber() + "\nusername: " + user.getUsername() + "\npassword: " + user.getPassword());
                    System.out.println("====================================================");
                    break;
                case 4:
                    location(user.getLocY(), user.getLocX(),user);
                    break;
                case 5:
                    if (getOrderS() != null) {
                        Order order1 = orderService.getOrder(getOrderS().getId());
                        if (order1.getStatus() == OrderStatus.ACCEPT) {
                            char[] animationChars = new char[]{'|', '/', '-', '\\'};
                            for (int i = 0; i <= 100; i++) {
                                System.out.print("Processing: " + i + "% " + animationChars[i % 4] + "\r");
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                            if (getDriver()!= null) {
                                System.out.println("Driver accepted");
                                visualDriver(getDriver(), order1, user);
                                order1.setStatus(OrderStatus.FINISH);
                            } else {
                                System.out.println("Driver is not accepted");
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

    public static void location(int locX, int locY, BaseModel baseModel) {
        String  iconP ="\uD83E\uDDCD";
        String  iconD ="\uD83D\uDE95";

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 40; j++) {
                if (i == locX && j == locY) {
                    if (baseModel instanceof Driver) {
                         System.out.print(iconD);}
                    else
                    if (baseModel instanceof User) {
                        System.out.print(iconP);}
                    else {
                        System.out.print("o");
                    }
                    j++;
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

    public static void location(int locX, int locY, int finX, int finY) {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 40; j++) {
                if (i == locX && j == locY) {
                    System.out.print("\uD83E\uDDCD"); // person
                    continue;
                }
                //System.out.print("\uD83D\uDE95"); // car
                if (i == finX && j == finY) {
                    System.out.print("\uD83D\uDEA9"); // destination
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

    public static void menuDriver(UserService userService, Driver driver, DriverService driverService, OrderService orderService, Scanner scannerInt, Scanner scannerStr) {
        boolean stepcode = true;

        while (stepcode) {

            System.out.println("1. Message\t2. History\t3. My profile\t4. My location\t0. Exit");

            switch (scannerInt.nextInt()) {
                case 1:
                    Order orderNew = orderService
                            .getNewOrder(driver.getId());
//                    System.out.println("dId" + driver.getId());
                    if (orderNew != null) {
                        System.out.println(orderNew.toString());
                        System.out.println("1. Tasdiqlash\t2.Rad etish");
                        if (scannerInt.nextInt() == 1) {
                            driverService.accept(orderNew.getId());
                        } else {
                            driver.setFree(false);
                            orderService.cancelOrder(orderNew.getId());
                            User userOrdered = userService.getOne(orderNew.getId());
                            orderNew.setDriverId(driverService.getDriver(userOrdered).getId());
                            driver.setFree(true);
                        }
                    } else {
                        System.out.println("No new message!");
                    }
                    break;
                case 2:
                    List<Order> orderl = orderService.getList(driver.getId());

                    if (orderl.size() > 0) {
                        int idx = 1;

                        for (Order order2 : orderl) {
                            Driver driver1 = driverService.getDriver(order2.getDriverId());
                            System.out.println("id: " + idx++ + order2.toString());
                            System.out.println("====================================================");
                        }
                    } else {
                        System.out.println("Empty history!");
                    }
                    break;
                case 3:
                    System.out.println("name: " + driver.getName() + "\nphone number: " + driver.getPhoneNumber() + "\nusername: " + driver.getUsername() + "\npassword: " + driver.getPassword() + "\nstatus: " + driver.isFree());
                    System.out.println("====================================================");
                    break;
                case 4:
                    location(getDriver().getLocX(), getDriver().getLocY(),getDriver());
                    break;
                default:
                    stepcode = false;
                    break;
            }
        }
    }

    public static void visualDriver(Driver driver, Order order, User user) {
        driver.setSymbol('D');
        order.setSymbol('F');
        user.setSymbol('C');

        final int SCREEN_WIDTH = 40; // Columns
        final int SCREEN_HEIGHT = 15; // Rows


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
            System.out.print("\033[H\033[2J");
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

    public static Order getOrderS() {
        return orderS;
    }

    public static void setOrderS(Order orderS) {
        Main.orderS = orderS;
    }

    public static Driver getDriver() {
        return driver;
    }

    public static void setDriver(Driver driver) {
        Main.driver = driver;
    }
}
