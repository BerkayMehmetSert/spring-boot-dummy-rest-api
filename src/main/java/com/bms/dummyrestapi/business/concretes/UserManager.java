package com.bms.dummyrestapi.business.concretes;

import com.bms.dummyrestapi.business.abstracts.UserService;
import com.bms.dummyrestapi.core.utilities.*;
import com.bms.dummyrestapi.dataAccess.UserDao;
import com.bms.dummyrestapi.entities.concretes.user.User;
import com.bms.dummyrestapi.entities.dtos.user.AddressDto;
import com.bms.dummyrestapi.entities.dtos.user.BankDto;
import com.bms.dummyrestapi.entities.dtos.user.CompanyDto;
import com.bms.dummyrestapi.entities.dtos.user.UserDto;
import com.bms.dummyrestapi.exceptions.AllReadyExistsException;
import com.bms.dummyrestapi.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserManager implements UserService {
    private final UserDao userDao;
    private final ModelMapper modelMapper;

    public UserManager(UserDao userDao, ModelMapper modelMapper) {
        this.userDao = userDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<UserDto>> getAllUser(Optional<String> firstName, Optional<Integer> pageNo, Optional<Integer> pageSize) {
        List<User> users = userDao.findAll();
        if (firstName.isPresent()) {
            users = userDao.findByFirstNameIgnoreCase(firstName.get());
        }

        if (pageNo.isPresent() && pageSize.isPresent()) {

            Pageable pageable = PageRequest.of((pageNo.get()) - 1, pageSize.get());
            users = userDao.findAll(pageable).getContent();
        }

        List<UserDto> userDto = users.stream().map(user -> modelMapper
                        .map(user, UserDto.class))
                .collect(Collectors.toList());

        if (userDto.isEmpty()) throw new NotFoundException("User not found");

        return new SuccessDataResult<>(userDto, "Users listed successfully");

    }

    @Override
    public DataResult<UserDto> getUserById(int id) {
        User user = userDao.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        UserDto userDto = modelMapper.map(user, UserDto.class);

        return new SuccessDataResult<UserDto>(userDto, "User listed");
    }

    @Override
    public DataResult<AddressDto> getUserAddressById(int id) {
        User user = userDao.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        AddressDto addressDto = modelMapper.map(user.getAddress(), AddressDto.class);

        return new SuccessDataResult<AddressDto>(addressDto, "User " + id + " address listed");
    }

    @Override
    public DataResult<CompanyDto> getUserCompanyById(int id) {
        User user = userDao.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        CompanyDto companyDto = modelMapper.map(user.getCompany(), CompanyDto.class);

        return new SuccessDataResult<CompanyDto>(companyDto, "User " + id + " company listed");
    }

    @Override
    public DataResult<AddressDto> getUserCompanyAddressById(int id) {
        User user = userDao.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        AddressDto addressDto = modelMapper.map(user.getCompany().getAddress(), AddressDto.class);

        return new SuccessDataResult<AddressDto>(addressDto, "User " + id + " company address listed");
    }

    @Override
    public DataResult<BankDto> getUserBankById(int id) {
        User user = userDao.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
        BankDto bankDto = modelMapper.map(user.getBank(), BankDto.class);

        return new SuccessDataResult<BankDto>(bankDto, "User " + id + " bank listed");
    }

    @Override
    public Result addUser(User user) {
        if (userDao.existsByEmail(user.getEmail()) || userDao.existsByFirstNameIgnoreCase(user.getFirstName()))
            throw new AllReadyExistsException("User " + user.getFirstName() + " already exists");

        userDao.save(user);
        return new SuccessResult("User added");
    }

    @Override
    public Result updateUser(int id, User user) {
        User userToUpdate = userDao.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setAddress(user.getAddress());
        userToUpdate.setCompany(user.getCompany());
        userToUpdate.setBank(user.getBank());

        userDao.save(userToUpdate);
        return new SuccessResult("User updated");
    }

    @Override
    public Result deleteUser(int id) {
        User user = userDao.findById(id).orElseThrow(() -> new NotFoundException("User " + id + " not found"));

        userDao.delete(user);
        return new SuccessResult("User deleted");
    }

}