package application;

import dtos.AddBankDTO;
import entityDO.BankAccount;
import entityDO.User;
import mapper.BankMapper;

public class BankLogic {

    Repositories repositories;
    private BankMapper bs;

    public BankLogic(Repositories repositories) {
        this.repositories = repositories;
        this.bs = new BankMapper();
    }

    public User createNewBank(AddBankDTO dto, User user) {
        BankAccount newBank = bs.convertAddBankDTOToBank(dto, user);
        user.setBankAccount(newBank);
        repositories.userRepository.update(user);
        User updatedUser = repositories.getUserRepository().findUserById(user.getId());
        return updatedUser;
    }
}
