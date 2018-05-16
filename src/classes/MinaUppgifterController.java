package classes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class MinaUppgifterController extends Controller{

    private IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    @FXML
    TextField firstName, lastName, phoneNumber, mobileNumber, email, address, zipCode,
            cardHolder, cardNumber, validMonth, validYear, cvc;
    @FXML
    Label confirmation;


    public void setFirstName() {
        iMatDataHandler.getCustomer().setFirstName(firstName.getText());
        System.out.println(firstName.getText());
    }

    public void setLastName() {
        iMatDataHandler.getCustomer().setLastName(lastName.getText());
        System.out.println(lastName.getText());
    }

    public void setPhoneNumber() {
        iMatDataHandler.getCustomer().setPhoneNumber(phoneNumber.getText());
        System.out.println(phoneNumber.getText());
    }

    public void setMobileNumber() {
        iMatDataHandler.getCustomer().setMobilePhoneNumber(mobileNumber.getText());
        System.out.println(mobileNumber.getText());
    }

    public void setEmail() {
        iMatDataHandler.getCustomer().setEmail(email.getText());
        System.out.println(email.getText());
    }

    public void setAddress() {
        iMatDataHandler.getCustomer().setAddress(address.getText());
        System.out.println(address.getText());
    }

    public void setZipCode() {
        iMatDataHandler.getCustomer().setPostCode(zipCode.getText());
        System.out.println(zipCode.getText());
    }

    public void setCardHolder() {
        iMatDataHandler.getCreditCard().setHoldersName(cardHolder.getText());
        System.out.println(cardHolder.getText());
    }

    public void setCardNumber() {
        iMatDataHandler.getCreditCard().setCardNumber(cardNumber.getText());
        System.out.println(cardNumber.getText());
    }

    public void setValidMonth() {
        if (validMonth.getText().length() < 1){
            return;
        }
        iMatDataHandler.getCreditCard().setValidMonth(Integer.parseInt(validMonth.getText()));
    }

    public void setValidYear() {
        if (validYear.getText().length() < 1){
            return;
        }
        iMatDataHandler.getCreditCard().setValidYear(Integer.parseInt(validYear.getText()));
    }

    public void setCvc () {
        if (cvc.getText().length() < 1){
            return;
        }
        iMatDataHandler.getCreditCard().setVerificationCode(Integer.parseInt(cvc.getText()));
    }

    private void charLimiter(TextField textField, int maxLength){
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (textField.getText().length() > maxLength) {
                    String s = textField.getText().substring(0, maxLength);
                    textField.setText(s);
                }
            }
        });
    }

    public void saveBtn() {

        if (checkUsage(firstName)){
            if(checkIfNumbers(firstName)){
                this.confirmation.setText("Kontrollera att personuppgifterna stämmer!");
                return;
            }
        }

        if (checkUsage(lastName)){
            if(checkIfNumbers(lastName)){
                this.confirmation.setText("Kontrollera att personuppgifterna stämmer!");
                return;
            }
        }

        if (checkUsage(phoneNumber)){
            if(checkIfLetters(phoneNumber)){
                this.confirmation.setText("Kontrollera att personuppgifterna stämmer!");
                return;
            }
        }

        if (checkUsage(mobileNumber)){
            if(checkIfLetters(mobileNumber)){
                this.confirmation.setText("Kontrollera att personuppgifterna stämmer!");
                return;
            }
        }

        if (checkUsage(zipCode)){
            if(checkIfLetters(zipCode)){
                this.confirmation.setText("Kontrollera att personuppgifterna stämmer!");
                return;
            }
        }

        if (checkUsage(cardHolder)){
            if(checkIfNumbers(cardHolder)){
                this.confirmation.setText("Kontrollera att kortuppgifterna stämmer!");
                return;
            }
        }

        if (checkUsage(cardNumber)){
            if(checkIfLetters(cardNumber)){
                this.confirmation.setText("Kontrollera att kortuppgifterna stämmer!");
                return;
            }
        }


        if (checkUsage(validMonth)){
            if(checkIfLetters(validMonth)){
                this.confirmation.setText("Kontrollera att kortuppgifterna stämmer!");
                return;
            }
        }


        if (checkUsage(validYear)){
            if(checkIfLetters(validYear)){
                this.confirmation.setText("Kontrollera att kortuppgifterna stämmer!");
                return;
            }
        }


        if (checkUsage(cvc)){
            if(checkIfLetters(cvc)){
                this.confirmation.setText("Kontrollera att kortuppgifterna stämmer!");
                return;
            }
        }




        System.out.println(iMatDataHandler.getCreditCard().getValidMonth());
        if (checkUsage(validMonth)){
            if (Integer.parseInt(validMonth.getText()) > 12 || Integer.parseInt(validMonth.getText()) < 1) {
                this.confirmation.setText("Kontrollera att kortinformationen stämmer!");
                return;
            }
            else{
                this.confirmation.setText("Dina uppgifter har sparats!");
            }
        }

        if (checkUsage(validYear)){
            if(Integer.parseInt(validYear.getText()) < 18){
                this.confirmation.setText("Kontrollera att kortinformationen stämmer!");
                return;
            }
            else{
                this.confirmation.setText("Dina uppgifter har sparats!");
            }
        }
        if (checkUsage(firstName) || checkUsage(lastName) || checkUsage(phoneNumber)|| checkUsage(mobileNumber) ||
                checkUsage(email) || checkUsage(address) || checkUsage(zipCode) || checkUsage(cardHolder) ||
                checkUsage(cardHolder) || checkUsage(cvc)){
            this.confirmation.setText("Dina uppgifter har sparats!");
        }

    }

    private boolean checkUsage(TextField textField){
        return textField.getText().length() != 0;
    }

    private boolean checkIfNumbers(TextField textField){
        char [] charArray = textField.getText().toCharArray();
        for (int i = 0; i < charArray.length; i++){
            if (Character.isDigit(charArray[i])){
                return true;
            }
        }
        return false;
    }

    private boolean checkIfLetters(TextField textField) {
        char[] charArray = textField.getText().toCharArray();
        for (int i = 0; i < charArray.length; i++){
            if (Character.isAlphabetic(charArray[i])){
                return true;
            }
        }
        return false;
    }

    @Override
    public void init() {
        this.confirmation.setText("");
        charLimiter(phoneNumber,9);
        charLimiter(mobileNumber, 10);
        charLimiter(zipCode,5);
        charLimiter(cardNumber,16);
        charLimiter(validMonth, 2);
        charLimiter(validYear, 2);
        charLimiter(cvc,3);
    }
}