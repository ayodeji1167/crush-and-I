package com.example.crushandi.serviceImpl.lovecalc;

import com.example.crushandi.entity.LoveQuotes;
import com.example.crushandi.exception.LoveAppException;
import com.example.crushandi.repository.LoveQuotesRepository;
import com.example.crushandi.service.LoveQuotesService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@Service
public class LoveQuotesServiceImpl implements LoveQuotesService {
    private final LoveQuotesRepository loveQuotesRepository;

    public LoveQuotesServiceImpl(LoveQuotesRepository loveQuotesRepository) {
        this.loveQuotesRepository = loveQuotesRepository;
    }


    @Override
    public List<LoveQuotes> getAllLoveQuotes() {
        return loveQuotesRepository.findAll();
    }

    @Override
    public LoveQuotes getQuoteById(String id) {
        return loveQuotesRepository.findById(id).orElseThrow(() -> new LoveAppException("Quotes with id " + id + " not found"));
    }

    @Override
    public void addLoveQuotesFromApi() throws URISyntaxException {

        WebClient client = WebClient.create();
        for (int i = 1; i <= 30; i++) {
            LoveQuotes response = client.get()
                    .uri(new URI("https://love-quote.p.rapidapi.com/lovequote"))
                    .header("X-RapidAPI-Host", "love-quote.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "2b325a89aamshdda43fe7680b78dp1b8848jsne988359fb8b2")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(LoveQuotes.class)
                    .block();

            if (!Objects.isNull(response.getQuote())) {
                if (response.getQuote().length() < 220)
                    loveQuotesRepository.save(response);
            }
        }
    }

    @Override
    public void deleteLoveQuotesById(String id) {
        loveQuotesRepository.deleteById(id);
    }
}
