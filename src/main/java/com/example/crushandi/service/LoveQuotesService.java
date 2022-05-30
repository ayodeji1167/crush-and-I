package com.example.crushandi.service;

import com.example.crushandi.entity.LoveQuotes;

import java.net.URISyntaxException;
import java.util.List;

public interface LoveQuotesService {


    List<LoveQuotes> getAllLoveQuotes();

    LoveQuotes getQuoteById(String id);

    void addLoveQuotesFromApi() throws URISyntaxException;

    void deleteLoveQuotesById(String id);


}
