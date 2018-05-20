package classes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class BetalningController extends Controller {

    @FXML
    private TextField firstNameTxtF, lastNameTxtF, addressTxtF, phoneNumberTxtF, mobileNumberTxtF, zipCodeTxtF, emailTxtF;

    @FXML
    private TextField cardHolderTxtF, cardNumberTxtF, validMonthTxtF, validYearTxtF, cvcTxtF;

    @FXML
    Label messageLbl;

    @FXML
    private ImageView firstNameArrow, lastNameArrow, addressArrow, zipCodeArrow, phoneNumberArrow, mobileNumberArrow, emailArrow;

    @FXML
    private ImageView cardHolderArrow, cardNumberArrow, validArrow, cvcArrow;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();


    @FXML
    public void onGoToConfirmationClicked() {

        controlLettersOnlyArrow(firstNameTxtF, firstNameArrow);
        controlLettersOnlyArrow(lastNameTxtF,lastNameArrow);
        controlArrow(addressTxtF,addressArrow);
        controlNumbersOnlyArrow(zipCodeTxtF,zipCodeArrow);
        controlNumbersOnlyArrow(phoneNumberTxtF,phoneNumberArrow);
        controlNumbersOnlyArrow(mobileNumberTxtF,mobileNumberArrow);
        controlArrow(emailTxtF,emailArrow);

        controlLettersOnlyArrow(cardHolderTxtF,cardHolderArrow);
        controlNumbersOnlyArrow(cardNumberTxtF,cardNumberArrow);
        controlNumbersOnlyArrow(validMonthTxtF,validArrow);
        controlNumbersOnlyArrow(validYearTxtF,validArrow);
        controlNumbersOnlyArrow(cvcTxtF,cvcArrow);

        if(!firstNameArrow.isVisible() && !lastNameArrow.isVisible() && !addressArrow.isVisible() &&
                !zipCodeArrow.isVisible() && !phoneNumberArrow.isVisible() && !mobileNumberArrow.isVisible() &&
                !mobileNumberArrow.isVisible() && !emailArrow.isVisible() && !cardHolderArrow.isVisible() &&
                !cardNumberArrow.isVisible() && !validArrow.isVisible() && !cvcArrow.isVisible()) {

            iMatDataHandler.getCustomer().setEmail(emailTxtF.getText());
            MainWindowController.spManager.showPane("../fxml/confirmation.fxml");

        }

        else {
            messageLbl.textProperty().set("Vänligen kontrollera att fälten är korrekt ifyllda");
        }
}

    @FXML
    public void onGoBackToLeveranstidClicked() {
        MainWindowController.spManager.showPane("../fxml/leveranstid.fxml");
    }

    public void saveButton() {
        messageLbl.textProperty().set("");
        firstNameArrow.setVisible(false);
        lastNameArrow.setVisible(false);
        addressArrow.setVisible(false);
        zipCodeArrow.setVisible(false);
        phoneNumberArrow.setVisible(false);
        mobileNumberArrow.setVisible(false);
        emailArrow.setVisible(false);

        cardHolderArrow.setVisible(false);
        cardNumberArrow.setVisible(false);
        validArrow.setVisible(false);
        cvcArrow.setVisible(false);

        if (checkUsage(firstNameTxtF)){
            if(checkIfNumbers(firstNameTxtF)){
                messageLbl.setText("Kontrollera att personuppgifterna stämmer!");
                return;
            }
        }

        if (checkUsage(lastNameTxtF)){
            if(checkIfNumbers(lastNameTxtF)){
                messageLbl.setText("Kontrollera att personuppgifterna stämmer!");
                return;
            }
        }

        if (checkUsage(phoneNumberTxtF)){
            if(checkIfLetters(phoneNumberTxtF)){
                messageLbl.setText("Kontrollera att personuppgifterna stämmer!");
                return;
            }
        }

        if (checkUsage(mobileNumberTxtF)){
            if(checkIfLetters(mobileNumberTxtF)){
                messageLbl.setText("Kontrollera att personuppgifterna stämmer!");
                return;
            }
        }

        if (checkUsage(zipCodeTxtF)){
            if(checkIfLetters(zipCodeTxtF)){
                messageLbl.setText("Kontrollera att personuppgifterna stämmer!");
                return;
            }
        }

        if (checkUsage(cardHolderTxtF)){
            if(checkIfNumbers(cardHolderTxtF)){
                this.messageLbl.setText("Kontrollera att kortuppgifterna stämmer!");
                return;
            }
        }

        if (checkUsage(cardNumberTxtF)){
            if(checkIfLetters(cardNumberTxtF)){
                messageLbl.setText("Kontrollera att kortuppgifterna stämmer!");
                return;
            }
        }

        if (checkUsage(validMonthTxtF)){
            if (checkIfLetters(validMonthTxtF)){
                if (Integer.parseInt(validMonthTxtF.getText()) > 12 || Integer.parseInt(validMonthTxtF.getText()) < 1) {
                    messageLbl.setText("Kontrollera att kortinformationen stämmer!");
                    return;
                }
            }
        }

        if (checkUsage(validYearTxtF)){
            if (checkIfLetters(validYearTxtF)){
                if(Integer.parseInt(validYearTxtF.getText()) < 18){
                    messageLbl.setText("Kontrollera att kortinformationen stämmer!");
                    return;
                }
            }
        }

        if (checkUsage(cvcTxtF)){
            if(checkIfLetters(cvcTxtF)){
                messageLbl.setText("Kontrollera att kortuppgifterna stämmer!");
                return;
            }
        }

        if (checkUsage(firstNameTxtF) && checkUsage(lastNameTxtF) && checkUsage(phoneNumberTxtF) && checkUsage(mobileNumberTxtF) &&
                checkUsage(emailTxtF) && checkUsage(addressTxtF) && checkUsage(zipCodeTxtF) && checkUsage(cardHolderTxtF) &&
                checkUsage(cardHolderTxtF) && checkUsage(cvcTxtF)){
            this.messageLbl.setText("Dina uppgifter har sparats!");
            iMatDataHandler.getCustomer().setFirstName(firstNameTxtF.getText());
            iMatDataHandler.getCustomer().setLastName(lastNameTxtF.getText());
            iMatDataHandler.getCustomer().setAddress(addressTxtF.getText());
            iMatDataHandler.getCustomer().setEmail(emailTxtF.getText());
            iMatDataHandler.getCustomer().setPhoneNumber(phoneNumberTxtF.getText());
            iMatDataHandler.getCustomer().setMobilePhoneNumber(mobileNumberTxtF.getText());
            iMatDataHandler.getCustomer().setPostCode(zipCodeTxtF.getText());
            iMatDataHandler.getCreditCard().setHoldersName(cardHolderTxtF.getText());
            iMatDataHandler.getCreditCard().setCardNumber(cardNumberTxtF.getText());
            iMatDataHandler.getCreditCard().setValidMonth(Integer.parseInt(validMonthTxtF.getText()));
            iMatDataHandler.getCreditCard().setValidYear(Integer.parseInt(validYearTxtF.getText()));
            iMatDataHandler.getCreditCard().setVerificationCode(Integer.parseInt(cvcTxtF.getText()));
        }
    }

    private boolean checkUsage(TextField textField){
        return textField.getText() != null;
    }

    private boolean checkIfNumbers(TextField textField){
        if(textField.getText() == null){
            return false;
        }
        char [] charArray = textField.getText().toCharArray();
        for (int i = 0; i < charArray.length; i++){
            if (Character.isDigit(charArray[i])){
                return true;
            }
        }
        return false;
    }

    private boolean checkIfLetters(TextField textField) {
        if(textField.getText() == null){
            return false;
        }
        char[] charArray = textField.getText().toCharArray();
        for (int i = 0; i < charArray.length; i++){
            if (Character.isAlphabetic(charArray[i])){
                return true;
            }
        }
        return false;
    }

    private void controlLettersOnlyArrow(TextField textField, ImageView imageView){
        if(!checkUsage(textField.getText()) || checkIfNumbers(textField)){
            imageView.setVisible(true);
        }
        else {
            imageView.setVisible(false);
        }
    }

    private void controlNumbersOnlyArrow(TextField textField, ImageView imageView){
        if (!checkUsage(textField.getText()) || textField.getText().equals("0") || checkIfLetters(textField)){
            imageView.setVisible(true);
        }
        else {
            imageView.setVisible(false);
        }
    }

    private void controlArrow(TextField textField, ImageView imageView){
        if(!checkUsage(textField)){
            imageView.setVisible(true);
        }
        else {
            imageView.setVisible(false);
        }
    }



    private boolean checkUsage(String textField){
        return textField != null;
    }

    private void charLimiter(TextField textField, int maxLength){
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (newValue != null && newValue.length() > maxLength ) {
                    String s = newValue.substring(0, maxLength);
                    textField.textProperty().set(s);
                }
            }
        });
    }

    private void checkPreviousInfo(){
        if (firstNameTxtF.getText() != null ){
            firstNameTxtF.textProperty().set(iMatDataHandler.getCustomer().getFirstName());
        }


        if (lastNameTxtF.getText() != null){
            lastNameTxtF.textProperty().set(iMatDataHandler.getCustomer().getLastName());
        }

        if(addressTxtF.getText() != null) {
            addressTxtF.textProperty().set(iMatDataHandler.getCustomer().getAddress());
        }

        if(emailTxtF.getText() != null) {
            emailTxtF.textProperty().set(iMatDataHandler.getCustomer().getEmail());
        }

        if( zipCodeTxtF.getText() != null) {
            zipCodeTxtF.textProperty().set(iMatDataHandler.getCustomer().getPostCode());
        }

        if(mobileNumberTxtF.getText() != null) {
            mobileNumberTxtF.textProperty().set(iMatDataHandler.getCustomer().getMobilePhoneNumber());
        }

        if(phoneNumberTxtF.getText() != null ){
            phoneNumberTxtF.textProperty().set(iMatDataHandler.getCustomer().getMobilePhoneNumber());
        }

        if(cardHolderTxtF.getText() != null){
            cardHolderTxtF.textProperty().set(iMatDataHandler.getCreditCard().getHoldersName());
        }

        if(cardNumberTxtF.getText() != null) {
            cardNumberTxtF.textProperty().set(iMatDataHandler.getCreditCard().getCardNumber());
        }

        validMonthTxtF.textProperty().set(iMatDataHandler.getCreditCard().getValidMonth() + "");
        validYearTxtF.textProperty().set(iMatDataHandler.getCreditCard().getValidYear() + "");
        cvcTxtF.textProperty().set(iMatDataHandler.getCreditCard().getVerificationCode() + "");
    }


    @Override
    public void init() {
        charLimiter(phoneNumberTxtF,11);
        charLimiter(mobileNumberTxtF, 10);
        charLimiter(zipCodeTxtF,5);
        charLimiter(cardNumberTxtF,16);
        charLimiter(validMonthTxtF, 2);
        charLimiter(validYearTxtF, 2);
        charLimiter(cvcTxtF,3);

    }

    public void opened() {

        checkPreviousInfo();

        firstNameArrow.setVisible(false);
        lastNameArrow.setVisible(false);
        addressArrow.setVisible(false);
        zipCodeArrow.setVisible(false);
        phoneNumberArrow.setVisible(false);
        mobileNumberArrow.setVisible(false);
        emailArrow.setVisible(false);

        cardHolderArrow.setVisible(false);
        cardNumberArrow.setVisible(false);
        validArrow.setVisible(false);
        cvcArrow.setVisible(false);
    }
}
