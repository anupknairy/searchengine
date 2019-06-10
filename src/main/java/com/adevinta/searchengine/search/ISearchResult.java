package com.adevinta.searchengine.search;

import com.adevinta.searchengine.display.DisplayType;

import java.util.Queue;

public interface ISearchResult {

    void updateResult(String fileName, Integer resPercentage);

    void displaySearchResult(DisplayType displayType);

    Queue<SearchResultImpl.FileSearchResult> getSerachResults();
}
