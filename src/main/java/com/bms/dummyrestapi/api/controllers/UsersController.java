package com.bms.dummyrestapi.api.controllers;

import com.bms.dummyrestapi.business.abstracts.UserService;
import com.bms.dummyrestapi.core.utilities.DataResult;
import com.bms.dummyrestapi.core.utilities.ErrorDataResult;
import com.bms.dummyrestapi.core.utilities.Result;
import com.bms.dummyrestapi.entities.concretes.user.User;
import com.bms.dummyrestapi.entities.dtos.user.AddressDto;
import com.bms.dummyrestapi.entities.dtos.user.BankDto;
import com.bms.dummyrestapi.entities.dtos.user.CompanyDto;
import com.bms.dummyrestapi.entities.dtos.user.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public DataResult<List<UserDto>> getAllUser(@RequestParam(name="search") Optional<String> firstName,
                                                @RequestParam(name="page") Optional<Integer> pageNo,
                                                @RequestParam(name="size") Optional<Integer> pageSize) {
        return this.userService.getAllUser(firstName, pageNo, pageSize);
    }

    @GetMapping("/{id}")
    public DataResult<UserDto> getUserById(@PathVariable int id) {
        return this.userService.getUserById(id);
    }

    @GetMapping("/{id}/address")
    public DataResult<AddressDto> getUserAddressById(@PathVariable int id) {
        return this.userService.getUserAddressById(id);
    }

    @GetMapping("/{id}/company")
    public DataResult<CompanyDto> getUserCompanyById(@PathVariable int id) {
        return this.userService.getUserCompanyById(id);
    }

    @GetMapping("/{id}/company/address")
    public DataResult<AddressDto> getUserCompanyAddressById(@PathVariable int id) {
        return this.userService.getUserCompanyAddressById(id);
    }

    @GetMapping("/{id}/bank")
    public DataResult<BankDto> getUserBankById(@PathVariable int id) {
        return this.userService.getUserBankById(id);
    }

    @PostMapping
    public Result addUser(@Valid @RequestBody User user) {
        return this.userService.addUser(user);
    }

    @PutMapping("/{id}")
    public Result updateUser(@PathVariable int id, @Valid @RequestBody User user) {
        return this.userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable int id) {
        return this.userService.deleteUser(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ErrorDataResult<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(
                        java.util.stream.Collectors.toMap(
                                fieldError -> fieldError.getField(),
                                fieldError -> fieldError.getDefaultMessage()
                        )
                );
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(errors, "Validation errors", "400");
        return errorDataResult;
    }
}
