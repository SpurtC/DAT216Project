package classes;

import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.control.TextField;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class BetalningController extends Controller {

    @FXML
    private TextField firstNameTxtF, lastNameTxtF, addressTxtF, phoneNumberTxtF, mobileNumberTxtF, zipCodeTxtF, emailTxtF;

    @FXML
    private TextField cardHolderTxtF, cardNumberTxtF, validMonthTxtF, validYearTxtF, cvcTxtF;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();


    @FXML
    public void onGoToConfirmationClicked() {
        MainWindowController.spManager.showPane("../fxml/confirmation.fxml");
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

    @Override
    public void init() {

    }

    public void opened() {
        firstNameTxtF.textProperty().set(iMatDataHandler.getCustomer().getFirstName());
        lastNameTxtF.textProperty().set(iMatDataHandler.getCustomer().getLastName());
        emailTxtF.textProperty().set(iMatDataHandler.getCustomer().getEmail());
        addressTxtF.textProperty().set(iMatDataHandler.getCustomer().getAddress());
        phoneNumberTxtF.textProperty().set(iMatDataHandler.getCustomer().getPhoneNumber());
        mobileNumberTxtF.textProperty().set(iMatDataHandler.getCustomer().getMobilePhoneNumber());
        zipCodeTxtF.textProperty().set(iMatDataHandler.getCustomer().getPostCode());

        cardHolderTxtF.textProperty().set(iMatDataHandler.getCreditCard().getHoldersName());
        cardNumberTxtF.textProperty().set(iMatDataHandler.getCreditCard().getCardNumber());
        validMonthTxtF.textProperty().set(iMatDataHandler.getCreditCard().getValidMonth() + "");
        validYearTxtF.textProperty().set(iMatDataHandler.getCreditCard().getValidYear() + "");
        cvcTxtF.textProperty().set(iMatDataHandler.getCreditCard().getVerificationCode() + "");
    }


}
