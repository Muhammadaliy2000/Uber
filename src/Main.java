import models.driver.Driver;
import models.order.Order;
import models.user.User;
import models.visual.GameScreen;
import models.visual.Wall;

public class Main {
    public static void main(String[] args) {
        char[] animationChars = new char[]{'|', '/', '-', '\\'};

        for (int i = 0; i <= 100; i++) {
            System.out.print("Processing: " + i + "% " + animationChars[i % 4] + "\r");

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

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
//                            (int) (Math.random() * (screen.getScreenHeight() - 1)),
//                            (int) (Math.random() * (screen.getScreenHeight() - 1)));
//
//        visualDriver(driver,order,user);
    }

    public static  void visualDriver(Driver driver, Order order, User user){

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

            if(driver.getLocX()==user.getLocX() && driver.getLocY()==user.getLocY()) isArrived = false;
            System.out.print("\033[H\033[2J");
            System.out.flush();
            screen.PrintScreen();
            if(driver.getLocX()<user.getLocX()){
                driver.moveRight(screen, driver);
            }else if(driver.getLocX()>user.getLocX()){
                driver.moveLeft(screen, driver);
            }else if(driver.getLocY()<user.getLocY()){
                driver.moveDown(screen, driver);
            }else if(driver.getLocY()>user.getLocY()){
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
            if(driver.getLocX()==order.getFinalX() && driver.getLocY()==order.getFinalY()) isArrived1 = false;
            System.out.print("\033[H\033[2J");
            System.out.flush();
            screen.PrintScreen();
            if(driver.getLocX()<order.getFinalX()){
                driver.moveRight(screen, driver);
            }else if(driver.getLocX()>order.getFinalX()){
                driver.moveLeft(screen, driver);
            }else if(driver.getLocY()<order.getFinalY()){
                driver.moveDown(screen, driver);
            }else if(driver.getLocY()>order.getFinalY()){
                driver.moveUp(screen, driver);
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
