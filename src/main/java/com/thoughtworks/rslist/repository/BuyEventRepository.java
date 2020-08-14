package com.thoughtworks.rslist.repository;

import com.thoughtworks.rslist.dto.BuyEventDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuyEventRepository extends CrudRepository<BuyEventDto, Integer> {
    @Override
    List<BuyEventDto> findAll();
    void deleteByName(String name);
}
