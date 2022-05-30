package com.example.crushandi.serviceImpl.lovecalc;

import com.example.crushandi.entity.PickUpLines;
import com.example.crushandi.exception.LoveAppException;
import com.example.crushandi.repository.PickUpLinesRepository;
import com.example.crushandi.service.PickUpLineService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PickUpLineServiceImpl implements PickUpLineService {
    private final PickUpLinesRepository pickUpLinesRepository;

    public PickUpLineServiceImpl(PickUpLinesRepository pickUpLinesRepository) {
        this.pickUpLinesRepository = pickUpLinesRepository;
    }

    @Override
    public List<PickUpLines> getAllPickUpLines() {
        return pickUpLinesRepository.findAll();
    }

    @Override
    public PickUpLines getPickUpById(String id) {
        return pickUpLinesRepository.findById(id).orElseThrow(() -> new LoveAppException("Pickup line not found"));
    }

    @Override
    public void addPickUpFromApi() throws URISyntaxException {

        List<PickUpLines> pickUpLinesList = new ArrayList<>();
        WebClient client = WebClient.create();

        Mono<PickUpLines[]> response = client.get()
                .uri(new URI("https://getpickuplines.herokuapp.com/lines"))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PickUpLines[].class);

        PickUpLines[] pickUpLines = response.block();

        pickUpLinesList.addAll(Arrays.asList(pickUpLines));
        pickUpLinesRepository.saveAll(pickUpLinesList);
    }

    @Override
    public void deletePickUpLine(String id) {
        pickUpLinesRepository.deleteById(id);
    }
}
