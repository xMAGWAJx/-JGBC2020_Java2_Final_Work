package lv.javaguru.productlist.businesslogic.services.deleteproductservice;

public class DeleteProductByIdResponse {

    private boolean success;
    private String errorMessages;

    public DeleteProductByIdResponse(boolean success, String errorMessages) {
        this.success = success;
        this.errorMessages = errorMessages;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessages() {
        return errorMessages;
    }
}
