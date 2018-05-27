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
            cardHolder, cardNumber,cardNumber1,cardNumber2,cardNumber3, validMonth, validYear, cvc;
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
        cardHolderArrow.setVisible(false);
        cardNumberArrow.setVisible(false);
        validArrow.setVisible(false);
        cvcArrow.setVisible(false);


        if (checkUsage(firstName)){
            if(checkIfFineForLetters(firstName)){
                iMatDataHandler.getCustomer().setFirstName(firstName.getText());
            }
            else{
                firstNameArrow.setVisible(true);
            }
        }

        if (checkUsage(lastName)){
            if(checkIfFineForLetters(lastName)){
                iMatDataHandler.getCustomer().setLastName(lastName.getText());
            }
            else{
                lastNameArrow.setVisible(true);
            }
        }

        if (checkUsage(phoneNumber)){
            if(checkIfFineForNumbers(phoneNumber)){
                iMatDataHandler.getCustomer().setPhoneNumber(phoneNumber.getText());
            }
            else{
                phoneNumberArrow.setVisible(true);
            }
        }

        if (checkUsage(mobileNumber)){
            if(checkIfFineForNumbers(mobileNumber)){
                iMatDataHandler.getCustomer().setMobilePhoneNumber(mobileNumber.getText());
            }
            else{
                mobileNumberArrow.setVisible(true);
            }
        }

        if (checkUsage(zipCode)){
            if(checkIfFineForNumbers(zipCode)){
                iMatDataHandler.getCustomer().setPostCode(zipCode.getText());
            }
            else{
                zipCodeArrow.setVisible(true);
            }
        }

        if (checkUsage(cardHolder)){
            System.out.println(cardHolder.textProperty().get());
            System.out.println(checkIfFineForLetters(cardHolder));

            if(checkIfFineForLetters(cardHolder)){
                iMatDataHandler.getCreditCard().setHoldersName(cardHolder.getText());
            }
            else{
                cardHolderArrow.setVisible(true);
            }
        }

        if (checkUsage(cardNumber)){
            if(checkLengthCardNumber(cardNumber)){
                if(!checkIfFineForNumbers(cardNumber)){
                    cardNumberArrow.setVisible(true);
                }
            }
            else{
                cardNumberArrow.setVisible(true);
            }
        }

        if (checkUsage(cardNumber1)){
            if(checkLengthCardNumber(cardNumber1)){
                if(!checkIfFineForNumbers(cardNumber1)){
                    cardNumberArrow.setVisible(true);
                }
            }
            else{
                cardNumberArrow.setVisible(true);
            }
        }

        if (checkUsage(cardNumber2)){
            if(checkLengthCardNumber(cardNumber2)){
                if(!checkIfFineForNumbers(cardNumber2)){
                    cardNumberArrow.setVisible(true);
                }
            }
            else{
                cardNumberArrow.setVisible(true);
            }
        }

        if (checkUsage(cardNumber3)){
            if(checkLengthCardNumber(cardNumber3)){
                if(!checkIfFineForNumbers(cardNumber3)){
                    cardNumberArrow.setVisible(true);
                }
            }
            else{
                cardNumberArrow.setVisible(true);
            }
        }

        if(!cardNumberArrow.isVisible()){
            String stringBuilder = (cardNumber.textProperty().get() + cardNumber1.textProperty().get() +
                    cardNumber2.textProperty().get() + cardNumber3.textProperty().get());
            iMatDataHandler.getCreditCard().setCardNumber(stringBuilder);
        }

        if (checkUsage(validMonth)){
           if(checkIfFineForNumbers(validMonth)){
               if (Integer.parseInt(validMonth.getText()) > 12 || Integer.parseInt(validMonth.getText()) < 1) {
                   validArrow.setVisible(true);
               }
               else{
                   iMatDataHandler.getCreditCard().setValidMonth(Integer.parseInt(validMonth.getText()));
               }
           }
           else {
               validArrow.setVisible(true);
           }
        }

        if (checkUsage(validYear)){
            if(checkIfFineForNumbers(validYear)){
                if(Integer.parseInt(validYear.getText()) < 18){
                    validArrow.setVisible(true);
                }
                else{
                    iMatDataHandler.getCreditCard().setValidYear(Integer.parseInt(validYear.getText()));
                }
            }
            else{
                validArrow.setVisible(true);
            }
        }

        if (checkUsage(cvc)){
            if(checkLengthCVC(cvc)){
                if(checkIfFineForNumbers(cvc)){
                    iMatDataHandler.getCreditCard().setVerificationCode(Integer.parseInt(cvc.getText()));
                }
                else{
                    cvcArrow.setVisible(true);
                }
            }
            else {
                cvcArrow.setVisible(true);
            }

        }

        if (firstNameArrow.isVisible() || lastNameArrow.isVisible() || phoneNumberArrow.isVisible() || mobileNumberArrow.isVisible() ||
                zipCodeArrow.isVisible() || cardHolderArrow.isVisible() || cardNumberArrow.isVisible() || validArrow.isVisible() ||
                cvcArrow.isVisible()){
            confirmation.textProperty().set("Vänligen kontrollera att de markerade uppgifterna stämmer");
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


    private boolean checkIfFineForNumbers(TextField textField){
        char [] charArray = textField.textProperty().get().toCharArray();
        for(Character character: charArray){
            if(!Character.isDigit(character)){
                return false;
            }
        }
        return true;
    }

    private boolean checkIfFineForLetters(TextField textField){
        char [] charArray = textField.textProperty().get().toCharArray();
        for(Character character: charArray){
            if(!Character.isAlphabetic(character)) {
                if(!Character.isSpaceChar(character)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkLengthCardNumber(TextField textField){
        char [] charArray = textField.textProperty().get().toCharArray();
        return charArray.length == 4;
    }

    private boolean checkLengthCVC (TextField textField){
        char [] charArray = textField.textProperty().get().toCharArray();
        return charArray.length == 3;
    }

    private void populateCardNumber(){
        if(iMatDataHandler.getCreditCard().getCardNumber() == null){
            return;
        }
        if(iMatDataHandler.getCreditCard().getCardNumber().length() < 16){
            return;
        }

        char [] charArray = iMatDataHandler.getCreditCard().getCardNumber().toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < 4; i++){
            stringBuilder.append(charArray[i]);
        }
        cardNumber.textProperty().set(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(int i = 4; i < 8; i++){
            stringBuilder.append(charArray[i]);
        }
        cardNumber1.textProperty().set(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(int i = 8; i < 12; i++){
            stringBuilder.append(charArray[i]);
        }
        cardNumber2.textProperty().set(stringBuilder.toString());
        stringBuilder.setLength(0);

        for(int i = 12; i < 16; i++){
            stringBuilder.append(charArray[i]);
        }
        cardNumber3.textProperty().set(stringBuilder.toString());
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

        validMonth.textProperty().set(iMatDataHandler.getCreditCard().getValidMonth() + "");
        validYear.textProperty().set(iMatDataHandler.getCreditCard().getValidYear() + "");
        cvc.textProperty().set(iMatDataHandler.getCreditCard().getVerificationCode() + "");
        populateCardNumber();
    }

    @Override
    public void init() {
        this.confirmation.setText("");
        charLimiter(phoneNumber,11);
        charLimiter(mobileNumber, 10);
        charLimiter(zipCode,5);
        charLimiter(cardNumber,4);
        charLimiter(cardNumber1,4);
        charLimiter(cardNumber2,4);
        charLimiter(cardNumber3,4);
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
