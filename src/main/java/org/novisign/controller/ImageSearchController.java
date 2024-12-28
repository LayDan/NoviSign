package org.novisign.controller;

import org.novisign.controller.result.SearchResult;
import org.novisign.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageSearchController {

    private final SearchService service;

    public ImageSearchController(SearchService service) {
        this.service = service;
    }

    @GetMapping("/images/search")
    public SearchResult search(@RequestParam(value = "url", required = false) String url,
                               @RequestParam(value = "duration", required = false) Double duration) {

        return service.search(url, duration);
    }
}
