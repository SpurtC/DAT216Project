package classes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class BetalningController extends Controller {

    @FXML
    private TextField firstNameTxtF, lastNameTxtF, addressTxtF, phoneNumberTxtF, mobileNumberTxtF, zipCodeTxtF, emailTxtF;

    @FXML
    private TextField cardHolderTxtF, cardNumberTxtF, validMonthTxtF, validYearTxtF, cvcTxtF;

    @FXML
    private ImageView firstNameArrow, lastNameArrow, addressArrow, zipCodeArrow, phoneNumberArrow, mobileNumberArrow, emailArrow;

    @FXML
    private ImageView cardHolderArrow, cardNumberArrow, validArrow, cvcArrow;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();


    @FXML
    public void onGoToConfirmationClicked() {

        controlArrow(firstNameTxtF, firstNameArrow);
        controlArrow(lastNameTxtF,lastNameArrow);
        controlArrow(addressTxtF,addressArrow);
        controlArrow(zipCodeTxtF,zipCodeArrow);
        controlArrow(phoneNumberTxtF,phoneNumberArrow);
        controlArrow(mobileNumberTxtF,mobileNumberArrow);
        controlArrow(emailTxtF,emailArrow);

        controlArrow(cardHolderTxtF,cardHolderArrow);
        controlArrow(cardNumberTxtF,cardNumberArrow);
        control2ndArrow(validMonthTxtF,validArrow);
        control2ndArrow(validYearTxtF,validArrow);
        control2ndArrow(cvcTxtF,cvcArrow);

        if(!firstNameArrow.isVisible() && !lastNameArrow.isVisible() && !addressArrow.isVisible() &&
                !zipCodeArrow.isVisible() && !phoneNumberArrow.isVisible() && !mobileNumberArrow.isVisible() &&
                !mobileNumberArrow.isVisible() && !emailArrow.isVisible() && !cardHolderArrow.isVisible() &&
                !cardNumberArrow.isVisible() && !validArrow.isVisible() && !cvcArrow.isVisible()) {

            MainWindowController.spManager.showPane("../fxml/confirmation.fxml");

        }
}

    @FXML
    public void onGoBackToLeveranstidClicked() {
        MainWindowController.spManager.showPane("../fxml/leveranstid.fxml");
    }

    public void saveButton() {
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

    private void control2ndArrow(TextField textField, ImageView imageView){
        if (!checkUsage(textField.getText()) || textField.getText().equals("0")){
            imageView.setVisible(true);
        }
        else {
            imageView.setVisible(false);
        }
    }

    private void controlArrow(TextField textField, ImageView imageView){
        if(!checkUsage(textField.getText())){
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

    private void printIfNotNull (TextField textField) {
        if (textField.getText() != null) {
            textField.textProperty().set(iMatDataHandler.getCustomer().getFirstName());
        }
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
        printIfNotNull(firstNameTxtF);
        printIfNotNull(lastNameTxtF);
        printIfNotNull(addressTxtF);
        printIfNotNull(emailTxtF);
        printIfNotNull(phoneNumberTxtF);
        printIfNotNull(mobileNumberTxtF);
        printIfNotNull(zipCodeTxtF);

        printIfNotNull(cardHolderTxtF);
        printIfNotNull(cardNumberTxtF);

        validMonthTxtF.textProperty().set(iMatDataHandler.getCreditCard().getValidMonth() + "");
        validYearTxtF.textProperty().set(iMatDataHandler.getCreditCard().getValidYear() + "");
        cvcTxtF.textProperty().set(iMatDataHandler.getCreditCard().getVerificationCode() + "");

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
