package com.github.gypsyjr777.service.api.google;

import com.github.gypsyjr777.entity.author.Author;
import com.github.gypsyjr777.entity.book.Book;
import com.github.gypsyjr777.model.google.Item;
import com.github.gypsyjr777.model.google.Root;
import com.github.gypsyjr777.model.google.SaleInfo;
import com.github.gypsyjr777.model.google.VolumeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleBooksService {
    private RestTemplate restTemplate;
    @Value("${application.api.google}")
    private String apiKey;

    private String BASE_URL = "https://www.googleapis.com/books/v1/volumes";

    @Autowired
    public GoogleBooksService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Book> getPageOfGoogleBookApiSearchResult (String searchWord, Integer limit, Integer offset) {
        String requestUrl = BASE_URL +
                "?key=" + apiKey +
                "&q=" + searchWord +
                "&startIndex" + offset +
                "&maxResults" + limit;

        Root root = restTemplate.getForEntity(requestUrl, Root.class).getBody();
        ArrayList<Book> list = new ArrayList<>();

        if (root != null) {
            for (Item item: root.getItems()) {
                Book book = new Book();
                if (item.getVolumeInfo() != null) {
                    VolumeInfo volumeInfo = item.getVolumeInfo();
                    book.setAuthor(new Author(volumeInfo.getAuthors()));
                    book.setTitle(volumeInfo.getTitle());
                    book.setImage(volumeInfo.getImageLinks().getThumbnail());
                }
                if (item.getSaleInfo() != null && item.getSaleInfo().getRetailPrice() != null) {
                    SaleInfo saleInfo = item.getSaleInfo();
                    book.setPrice(saleInfo.getRetailPrice().getAmount());
                    book.setPriceOld(saleInfo.getListPrice().getAmount());
                    list.add(book);
                }
            }
        }

        return list;
    }
}
