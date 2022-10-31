package com.bms.dummyrestapi.business.abstracts;

import com.bms.dummyrestapi.core.utilities.DataResult;
import com.bms.dummyrestapi.core.utilities.Result;
import com.bms.dummyrestapi.entities.concretes.user.User;
import com.bms.dummyrestapi.entities.dtos.user.AddressDto;
import com.bms.dummyrestapi.entities.dtos.user.BankDto;
import com.bms.dummyrestapi.entities.dtos.user.CompanyDto;
import com.bms.dummyrestapi.entities.dtos.user.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    DataResult<List<UserDto>> getAllUser(Optional<String> firstName, Optional<Integer> pageNo, Optional<Integer> pageSize);

    DataResult<UserDto> getUserById(int id);

    DataResult<AddressDto> getUserAddressById(int id);

    DataResult<CompanyDto> getUserCompanyById(int id);

    DataResult<AddressDto> getUserCompanyAddressById(int id);

    DataResult<BankDto> getUserBankById(int id);

    Result addUser(User user);

    Result updateUser(int id, User user);

    Result deleteUser(int id);
}
