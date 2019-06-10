package com.adevinta.searchengine.search;

import com.adevinta.searchengine.Constants;
import com.adevinta.searchengine.data.IndexedTextData;

import java.util.*;

public class SearchEngineImpl implements SearchEngine {

    ISearchResult result;

    @Override
    public ISearchResult doSearch(String input) {

        result = new SearchResultImpl();
        List<String> inputWordList=  getFormattedInputWordList(input);

        IndexedTextData indexedTextData = IndexedTextData.getInstance();

        for(Map.Entry<String,Set<String>> fileData : indexedTextData.getData().entrySet())
        {
            int wordCount = 0;
            for(String word : inputWordList)
            {
                if(fileData.getValue().contains(word))
                {
                    wordCount++;
                }
            }

            int resPercentage = calculateHitPercentage(wordCount,inputWordList.size());
            if(resPercentage == 0)
                continue;
            result.updateResult(fileData.getKey(),resPercentage);
        }

        return result;

    }

    public int calculateHitPercentage(int wordCount, int inputWordListSize) {

       return (100 * wordCount)/inputWordListSize;
    }

    public List<String> getFormattedInputWordList(String input) {

        List<String> inputWordList = new ArrayList<>();
        Arrays.stream(input.split(" ")).
                forEach(word->inputWordList.add(
                        word.toLowerCase().replaceAll(Constants.WORD_FORMAT_PATTERN,"")));
        return inputWordList;
    }


}
