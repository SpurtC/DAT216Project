package classes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class MinaUppgifterController extends Controller{

    private IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    @FXML
    TextField firstName, lastName, phoneNumber, mobileNumber, email, address, zipCode,
            cardHolder, cardNumber, validMonth, validYear, cvc;
    @FXML
    Label confirmation;

    @FXML
    ImageView firstNameArrow, lastNameArrow, phoneNumberArrow, mobileNumberArrow, emailArrow, addressArrow, zipCodeArrow,
            cardHolderArrow, cardNumberArrow, validArrow, cvcArrow;

    SPManager spManager;

    @FXML
    public void onContinueShopMyAccountClicked () {
        MainWindowController.spManager.showPane("../fxml/framsida.fxml");
        MainWindowController.cssManager.changeCSS("handlaPane", "upperPaneFill", "upperPaneFillPressed");
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

    public void saveBtn() {
        firstNameArrow.setVisible(false);
        lastNameArrow.setVisible(false);
        phoneNumberArrow.setVisible(false);
        mobileNumberArrow.setVisible(false);
        emailArrow.setVisible(false);
        addressArrow.setVisible(false);
        zipCodeArrow.setVisible(false);


        if (checkUsage(firstName)){
            if(checkIfNumbers(firstName)){
                firstNameArrow.setVisible(true);
            }
            else{
                iMatDataHandler.getCustomer().setFirstName(firstName.getText());
            }
        }

        if (checkUsage(lastName)){
            if(checkIfNumbers(lastName)){
                lastNameArrow.setVisible(true);
            }
            else{
                iMatDataHandler.getCustomer().setLastName(lastName.getText());
            }
        }

        if (checkUsage(phoneNumber)){
            if(checkIfLetters(phoneNumber)){
                phoneNumberArrow.setVisible(true);
            }
            else{
                iMatDataHandler.getCustomer().setPhoneNumber(phoneNumber.getText());
            }
        }

        if (checkUsage(mobileNumber)){
            if(checkIfLetters(mobileNumber)){
                mobileNumberArrow.setVisible(true);
            }else{
                iMatDataHandler.getCustomer().setMobilePhoneNumber(mobileNumber.getText());
            }
        }

        if (checkUsage(zipCode)){
            if(checkIfLetters(zipCode)){
                zipCodeArrow.setVisible(true);
            }
            else{
                iMatDataHandler.getCustomer().setPostCode(zipCode.getText());
            }
        }

        if (checkUsage(cardHolder)){
            if(checkIfNumbers(cardHolder)){
                cardHolderArrow.setVisible(true);
            }
            else{
                iMatDataHandler.getCreditCard().setHoldersName(cardHolder.getText());
            }
        }

        if (checkUsage(cardNumber)){
            if(checkIfLetters(cardNumber)){
                cardNumberArrow.setVisible(true);
            }
            else{
                iMatDataHandler.getCreditCard().setCardNumber(cardNumber.getText());
            }
        }

        if (checkUsage(validMonth)){
            if (validMonth.textProperty().get().equals("0")){

            }
            else if (Integer.parseInt(validMonth.getText()) > 12 || Integer.parseInt(validMonth.getText()) < 1) {
                validArrow.setVisible(true);
            }
            else{
                iMatDataHandler.getCreditCard().setValidMonth(Integer.parseInt(validMonth.getText()));
            }
        }

        if (checkUsage(validYear)){
            if(validYear.textProperty().get().equals("0")){

            }
            if(Integer.parseInt(validYear.getText()) < 18){
                validArrow.setVisible(true);
            }
            else{
                iMatDataHandler.getCreditCard().setValidYear(Integer.parseInt(validYear.getText()));
            }
        }

        if (checkUsage(cvc)){
            if(checkIfLetters(cvc)){
                cvcArrow.setVisible(true);
            }
            else{
                iMatDataHandler.getCreditCard().setVerificationCode(Integer.parseInt(cvc.getText()));
            }
        }

        if (firstNameArrow.isVisible() || lastNameArrow.isVisible() || phoneNumberArrow.isVisible() || mobileNumberArrow.isVisible() ||
                zipCodeArrow.isVisible() || cardHolderArrow.isVisible() || cardNumberArrow.isVisible() || validArrow.isVisible() ||
                cvcArrow.isVisible()){
            confirmation.textProperty().set("Kontrollera att de markerade uppgifterna stämmer");
            confirmation.setVisible(true);
        }

        else if (checkUsage(firstName) || checkUsage(lastName) || checkUsage(phoneNumber)|| checkUsage(mobileNumber) ||
                checkUsage(email) || checkUsage(address) || checkUsage(zipCode) || checkUsage(cardHolder) ||
                checkUsage(cardHolder) || checkUsage(cvc)){
            confirmation.setText("Dina uppgifter har sparats!");
            confirmation.setVisible(true);
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


    private void checkPreviousInfo(){

        firstName.textProperty().set(iMatDataHandler.getCustomer().getFirstName());
        lastName.textProperty().set(iMatDataHandler.getCustomer().getLastName());
        address.textProperty().set(iMatDataHandler.getCustomer().getAddress());
        email.textProperty().set(iMatDataHandler.getCustomer().getEmail());
        zipCode.textProperty().set(iMatDataHandler.getCustomer().getPostCode());
        mobileNumber.textProperty().set(iMatDataHandler.getCustomer().getMobilePhoneNumber());
        phoneNumber.textProperty().set(iMatDataHandler.getCustomer().getMobilePhoneNumber());

        cardHolder.textProperty().set(iMatDataHandler.getCreditCard().getHoldersName());
        cardNumber.textProperty().set(iMatDataHandler.getCreditCard().getCardNumber());
        validMonth.textProperty().set(iMatDataHandler.getCreditCard().getValidMonth() + "");
        validYear.textProperty().set(iMatDataHandler.getCreditCard().getValidYear() + "");
        cvc.textProperty().set(iMatDataHandler.getCreditCard().getVerificationCode() + "");
    }

    @Override
    public void init() {
        this.confirmation.setText("");
        charLimiter(phoneNumber,11);
        charLimiter(mobileNumber, 10);
        charLimiter(zipCode,5);
        charLimiter(cardNumber,16);
        charLimiter(validMonth, 2);
        charLimiter(validYear, 2);
        charLimiter(cvc,3);
    }

    @Override
    public void opened() {
        checkPreviousInfo();
        confirmation.setVisible(false);
        firstNameArrow.setVisible(false);
        lastNameArrow.setVisible(false);
        phoneNumberArrow.setVisible(false);
        mobileNumberArrow.setVisible(false);
        emailArrow.setVisible(false);
        addressArrow.setVisible(false);
        zipCodeArrow.setVisible(false);
        cardHolderArrow.setVisible(false);
        cardNumberArrow.setVisible(false);
        validArrow.setVisible(false);
        cvcArrow.setVisible(false);
    }
}
