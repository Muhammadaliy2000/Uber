package service;

import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SignUpImpl implements SingUpService{

    private Scanner sc = new Scanner(System.in);
    private List<BaseModel> list = new ArrayList<>();
    private List<Order> orderList = new ArrayList();

    public SignUpImpl() {
        list.add(new User("","admin","123",true, Type.ADMIN, ""));
    }

    @Override
    public void singUp() {
        int type = -1;
        System.out.println("choose type: \n1: User; 2: Driver");
        switch (sc.nextInt()) {
            case 1:
                type = 1;
                break;
            case 2:
                type = 2;
                break;
        }

        System.out.println("enter name: "); String name = sc.next();
        System.out.println("enter username: "); String username = sc.next();
        System.out.println("enter password: "); String password = sc.next(); // ToDo
        if (type == 1) {
            System.out.println("phone number: "); String number = sc.next();
            User user = new User(name, username, password, true, Type.USER, number);
            list.add(user);
        } else  {
            System.out.println("car number: "); String car_number = sc.next();
            Driver driver = new Driver(name, username, password, true, Type.DRIVER, car_number, true);
            list.add(driver);
        }
    }

    @Override
    public void singIn() {
        System.out.println("choose one type: \n1: ADMIN; 2: DRIVER; 3: USER\nenter: ");
        int n = sc.nextInt();
        System.out.println("enter password: "); String password = sc.next();
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPassword().equals(password)) {
                index = i;
            }
        }

        if (index > -1) {
            switch (n) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
            }
        } else  {
            System.out.println("not found");
        }

    }
}