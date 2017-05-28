package ru.kpfu.itis.khayrullin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.khayrullin.service.CarService;
import ru.kpfu.itis.khayrullin.service.UserService;

/**
 * Created by Ruslan on 23.05.2017.
 */
@Component
public class Validator {

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;
    private static final String SYMBOLS_REGEX = ".*[^а-яА-Яa-zA-Z\\S]+.*";
    private static final String NUMBERS_ONLY_REGEX = "\\d*";
    private static final String DATE_REGEX = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
    private static final String FULL_NAME_REGEX = "[а-яА-Яa-zA-Z]+\\s[а-яА-Яa-zA-Z]+";
    private static final String NUMBER_REGEX = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";

    private boolean isEmpty(String s){
        if (s == null || s.equals("")){
            return true;
        }
        return false;
    }

    private boolean containsSym(String s){
        if (!s.matches(SYMBOLS_REGEX)){
            return false;
        }
        return true;
    }

    public void isRegistrationValid(String login, String pass, String confirm) throws Exception {
        if (isEmpty(login) || isEmpty(pass) || isEmpty(confirm)){
            throw new Exception("fields must be not empty");
        }
        if (!pass.equals(confirm)){
            throw new Exception("passwords doesn't match");
        }
        if (containsSym(login) || pass.contains(" ")){
            throw new Exception("fields contains unacceptable symbols");
        }
        if (userService.findByLogin(login) != null){
            throw new Exception("user with that login is already exists");
        }
    }

    public void isAuthValid(String login, String pass) throws Exception {
        if (isEmpty(login) || isEmpty(pass)){
            throw new Exception("fields must be not empty");
        }
        if (userService.findByLogin(login) == null){
            throw new Exception("there are no login like that");
        }
        if (!userService.findByLogin(login).getPassword().equals(pass)){
            throw new Exception("incorrect password");
        }
    }

    public void isCarFormValid(String model,String year,String mileage,String enginePower,String costPerHour) throws Exception {
        if (isEmpty(model) || isEmpty(year) || isEmpty(mileage) || isEmpty(enginePower) || isEmpty(costPerHour)){
            throw new Exception("fields must be not empty");
        }
        if (!year.matches(NUMBERS_ONLY_REGEX)){
            throw new Exception("year must be integer");
        }
        if (!mileage.matches(NUMBERS_ONLY_REGEX)){
            throw new Exception("mileage must be integer");
        }
        if (!enginePower.matches(NUMBERS_ONLY_REGEX)){
            throw new Exception("enginePower must be integer");
        }
        if (!costPerHour.matches(NUMBERS_ONLY_REGEX)){
            throw new Exception("cost per hour must be integer");
        }

    }

    public void isRentFormValid(String fullName, String number , String model , String deliveryDate, String returnDate) throws Exception {
        if (isEmpty(fullName) || isEmpty(number) || isEmpty(model) || isEmpty(deliveryDate) || isEmpty(returnDate)){
            throw new Exception("fields must be not empty");
        }
        if (!fullName.matches(FULL_NAME_REGEX)){
            throw new Exception("incorrect fullname: it must be 'Name Surename'");
        }
        if (!number.matches(NUMBER_REGEX)){
            throw new Exception("incorrect number");
        }
        if (!deliveryDate.matches(DATE_REGEX) || !returnDate.matches(DATE_REGEX)){
            throw new Exception("dates must be in 'DD.MM.YYYY' format");
        }

        int devMonth = Integer.parseInt("" + deliveryDate.charAt(5) + deliveryDate.charAt(6));
        int retMonth = Integer.parseInt("" + returnDate.charAt(5) + returnDate.charAt(6));

        if (retMonth<devMonth){
            throw new Exception("month of the delivery date can not be bigger than month of the return date");
        }

        int devDay = Integer.parseInt("" + deliveryDate.charAt(8) + deliveryDate.charAt(9));
        int retDay = Integer.parseInt("" + returnDate.charAt(8) + returnDate.charAt(9));

        if (retMonth == devMonth && retDay < devDay){
            throw new Exception("day of the delivery date can not be bigger than day of the return date");
        }
        if (carService.findbyModel(model) == null){
            throw new Exception("there is no car with that kind of model");
        }
    }

}
