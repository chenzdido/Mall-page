package com.thoughtworks.rslist.api;

import com.thoughtworks.rslist.domain.BuyEvent;
import com.thoughtworks.rslist.dto.BuyEventDto;
import com.thoughtworks.rslist.repository.BuyEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BuyController {
    @Autowired
    BuyEventRepository buyEventRepository;

    @GetMapping("/buy/list")
    public ResponseEntity getRsEventListBetween() {
        List<BuyEventDto> all=buyEventRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
