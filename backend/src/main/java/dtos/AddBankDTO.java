package dtos;

public class AddBankDTO {

    private String bankAddress;

    public AddBankDTO() {
    }

    public AddBankDTO(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankAddress() {
        return bankAddress;
    }
}
