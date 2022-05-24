package com.example.crushandi.service;

import com.example.crushandi.entity.PickUpLines;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;

@Service
public interface PickUpLineService {
    List<PickUpLines> getAllPickUpLines();

    PickUpLines getPickUpById(Long id);

    void addPickUpFromApi() throws URISyntaxException;

    void deletePickUpLine(Long id);


}
