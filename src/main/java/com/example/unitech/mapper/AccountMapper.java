package com.example.unitech.mapper;

import com.example.unitech.dto.response.AccountResponseDto;
import com.example.unitech.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {

    List<AccountResponseDto> toDtos(List<Account> accounts);
}
