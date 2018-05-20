package classes;

public class DeliveryHandler {

    public String deliveryDate;

    private DeliveryHandler() {}

    private static class DeliveryDateHolder {
        private static DeliveryHandler instance = new DeliveryHandler();
    }

    public static DeliveryHandler getInstance() {
        return DeliveryDateHolder.instance;
    }

}
