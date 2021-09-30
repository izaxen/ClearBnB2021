package mapper;

import dtos.AddBankDTO;
import entityDO.BankAccount;
import entityDO.User;

public class BankService {

    public BankAccount convertAddBankDTOToBank(AddBankDTO dto, User user){
        return new BankAccount(dto.getBankAddress(), user);
    }
}
