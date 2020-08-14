package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.domain.BuyEvent;
import com.thoughtworks.rslist.dto.BuyEventDto;
import com.thoughtworks.rslist.repository.BuyEventRepository;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BuyController {
    @Autowired
    BuyEventRepository buyEventRepository;

    @GetMapping("/buy/list")
    @ResponseBody
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity getBuyEventListBetween() {
        List<BuyEventDto> all=buyEventRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @DeleteMapping("/buy/delete/{name}")
    @ResponseBody
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity deleteBuyEvent(@PathVariable String name){
        buyEventRepository.deleteByName(name);
        return ResponseEntity.ok().build();
    }
}
