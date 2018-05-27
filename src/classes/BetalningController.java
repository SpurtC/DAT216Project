package classes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class BetalningController extends Controller {

    @FXML
    private TextField firstNameTxtF, lastNameTxtF, addressTxtF, phoneNumberTxtF, mobileNumberTxtF, zipCodeTxtF, emailTxtF;

    @FXML
    private TextField cardHolderTxtF, cardNumber, cardNumber1, cardNumber2, cardNumber3, validMonthTxtF, validYearTxtF, cvcTxtF;

    @FXML
    Label messageLbl;

    @FXML
    private ImageView firstNameArrow, lastNameArrow, addressArrow, zipCodeArrow, phoneNumberArrow, mobileNumberArrow, emailArrow;

    @FXML
    private ImageView cardHolderArrow, cardNumberArrow, validArrow, cvcArrow;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    private boolean arrow = false;

    @FXML
    public void onGoToConfirmationClicked() {

        arrow = false;
        controlLettersOnlyArrow(firstNameTxtF, firstNameArrow);
        controlLettersOnlyArrow(lastNameTxtF,lastNameArrow);
        controlArrow(addressTxtF,addressArrow);
        controlNumbersOnlyArrow(zipCodeTxtF,zipCodeArrow);
        controlNumbersOnlyArrow(phoneNumberTxtF,phoneNumberArrow);
        controlNumbersOnlyArrow(mobileNumberTxtF,mobileNumberArrow);
        controlArrow(emailTxtF,emailArrow);

        controlLettersOnlyArrow(cardHolderTxtF,cardHolderArrow);
        controlNumbersOnlyArrow2(cardNumber,cardNumberArrow);
        controlNumbersOnlyArrow2(cardNumber1,cardNumberArrow);
        controlNumbersOnlyArrow2(cardNumber2,cardNumberArrow);
        controlNumbersOnlyArrow2(cardNumber3,cardNumberArrow);
        controlNumbersOnlyArrow(validMonthTxtF,validArrow);
        controlNumbersOnlyArrow(validYearTxtF,validArrow);
        controlNumbersOnlyArrow(cvcTxtF,cvcArrow);

        if(!firstNameArrow.isVisible() && !lastNameArrow.isVisible() && !addressArrow.isVisible() &&
                !zipCodeArrow.isVisible() && !phoneNumberArrow.isVisible() && !mobileNumberArrow.isVisible() &&
                !mobileNumberArrow.isVisible() && !emailArrow.isVisible() && !cardHolderArrow.isVisible() &&
                !cardNumberArrow.isVisible() && !validArrow.isVisible() && !cvcArrow.isVisible()) {

            iMatDataHandler.getCustomer().setEmail(emailTxtF.getText());
            MainWindowController.spManager.showPane("../fxml/lastConfirmation.fxml");

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
        phoneNumberArrow.setVisible(false);
        mobileNumberArrow.setVisible(false);
        emailArrow.setVisible(false);
        addressArrow.setVisible(false);
        zipCodeArrow.setVisible(false);
        cardHolderArrow.setVisible(false);
        cardNumberArrow.setVisible(false);
        validArrow.setVisible(false);
        cvcArrow.setVisible(false);


        if (checkUsage(firstNameTxtF)){
            if(checkIfFineForLetters(firstNameTxtF)){
                iMatDataHandler.getCustomer().setFirstName(firstNameTxtF.getText());
            }
            else{
                firstNameArrow.setVisible(true);
            }
        }

        if (checkUsage(lastNameTxtF)){
            if(checkIfFineForLetters(lastNameTxtF)){
                iMatDataHandler.getCustomer().setLastName(lastNameTxtF.getText());
            }
            else{
                lastNameArrow.setVisible(true);
            }
        }

        if (checkUsage(phoneNumberTxtF)){
            if(checkIfFineForNumbers(phoneNumberTxtF)){
                iMatDataHandler.getCustomer().setPhoneNumber(phoneNumberTxtF.getText());
            }
            else{
                phoneNumberArrow.setVisible(true);
            }
        }

        if (checkUsage(mobileNumberTxtF)){
            if(checkIfFineForNumbers(mobileNumberTxtF)){
                iMatDataHandler.getCustomer().setMobilePhoneNumber(mobileNumberTxtF.getText());
            }
            else{
                mobileNumberArrow.setVisible(true);
            }
        }

        if (checkUsage(addressTxtF)){
            if(checkIfFineAddress(addressTxtF)){
                iMatDataHandler.getCustomer().setAddress(addressTxtF.getText());
            }
            else{
                addressArrow.setVisible(true);
            }
        }

        if (checkUsage(zipCodeTxtF)){
            if(checkIfFineForNumbers(zipCodeTxtF)){
                iMatDataHandler.getCustomer().setPostCode(zipCodeTxtF.getText());
            }
            else{
                zipCodeArrow.setVisible(true);
            }
        }

        if (checkUsage(cardHolderTxtF)){
            if(checkIfFineForLetters(cardHolderTxtF)){
                iMatDataHandler.getCreditCard().setHoldersName(cardHolderTxtF.getText());
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

        if (checkUsage(validMonthTxtF)){
            if(checkIfFineForNumbers(validMonthTxtF)){
                if (Integer.parseInt(validMonthTxtF.getText()) > 12 || Integer.parseInt(validMonthTxtF.getText()) < 1) {
                    validArrow.setVisible(true);
                }
                else{
                    iMatDataHandler.getCreditCard().setValidMonth(Integer.parseInt(validMonthTxtF.getText()));
                }
            }
            else {
                validArrow.setVisible(true);
            }
        }

        if (checkUsage(validYearTxtF)){
            if(checkIfFineForNumbers(validYearTxtF)){
                if(Integer.parseInt(validYearTxtF.getText()) < 18){
                    validArrow.setVisible(true);
                }
                else{
                    iMatDataHandler.getCreditCard().setValidYear(Integer.parseInt(validYearTxtF.getText()));
                }
            }
            else{
                validArrow.setVisible(true);
            }
        }

        if (checkUsage(cvcTxtF)){
            if(checkLengthCVC(cvcTxtF)){
                if(checkIfFineForNumbers(cvcTxtF)){
                    iMatDataHandler.getCreditCard().setVerificationCode(Integer.parseInt(cvcTxtF.getText()));
                }
                else{
                    cvcArrow.setVisible(true);
                }
            }
            else {
                cvcArrow.setVisible(true);
            }

        }

        if (firstNameArrow.isVisible() || lastNameArrow.isVisible() || phoneNumberArrow.isVisible() || addressArrow.isVisible() || mobileNumberArrow.isVisible() ||
                zipCodeArrow.isVisible() || cardHolderArrow.isVisible() || cardNumberArrow.isVisible() || validArrow.isVisible() ||
                cvcArrow.isVisible()){
            messageLbl.textProperty().set("Vänligen kontrollera att fälten är korrekt ifyllda");
            messageLbl.setTextFill(Color.RED);
            messageLbl.setVisible(true);
        }

        else if (checkUsage(firstNameTxtF) && checkUsage(lastNameTxtF) && checkUsage(phoneNumberTxtF) && checkUsage(mobileNumberTxtF) &&
                checkUsage(emailTxtF) && checkUsage(addressTxtF) && checkUsage(zipCodeTxtF) && checkUsage(cardHolderTxtF) &&
                checkUsage(cardHolderTxtF) && checkUsage(cvcTxtF)){
            messageLbl.setText("Dina uppgifter har sparats!");
            messageLbl.setTextFill(Color.GREEN);
            messageLbl.setVisible(true);
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

    private void controlNumbersOnlyArrow2(TextField textField, ImageView imageView){
        if(arrow){
            return;
        }
        if (!checkUsage(textField.getText()) || textField.getText().equals("0") || checkIfLetters(textField)){
            imageView.setVisible(true);
            arrow = true;
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
            phoneNumberTxtF.textProperty().set(iMatDataHandler.getCustomer().getPhoneNumber());
        }

        if(cardHolderTxtF.getText() != null){
            cardHolderTxtF.textProperty().set(iMatDataHandler.getCreditCard().getHoldersName());
        }

        populateCardNumber();

        validMonthTxtF.textProperty().set(iMatDataHandler.getCreditCard().getValidMonth() + "");
        validYearTxtF.textProperty().set(iMatDataHandler.getCreditCard().getValidYear() + "");
        cvcTxtF.textProperty().set(iMatDataHandler.getCreditCard().getVerificationCode() + "");
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

    private boolean checkIfFineForNumbers(TextField textField){
        char [] charArray = textField.textProperty().get().toCharArray();
        for(Character character: charArray){
            if(!Character.isDigit(character)){
                return false;
            }
        }
        return true;
    }

    private boolean checkIfFineAddress(TextField textField){
        char [] charArray = textField.textProperty().get().toCharArray();
        for (Character character: charArray){
            if(!Character.isSpaceChar(character)){
                if(!Character.isAlphabetic(character)){
                    if(!Character.isDigit(character)){
                        return false;
                    }
                }
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

    @Override
    public void init() {
        charLimiter(phoneNumberTxtF,11);
        charLimiter(mobileNumberTxtF, 10);
        charLimiter(zipCodeTxtF,5);
        charLimiter(cardNumber,4);
        charLimiter(cardNumber1,4);
        charLimiter(cardNumber2,4);
        charLimiter(cardNumber3,4);
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
        messageLbl.setVisible(false);
    }
}
