package com.adevinta.searchengine.search;

import com.adevinta.searchengine.Constants;
import com.adevinta.searchengine.display.DisplayFactory;
import com.adevinta.searchengine.display.DisplayType;
import com.adevinta.searchengine.display.IDisplay;

import java.util.PriorityQueue;
import java.util.Queue;

public class SearchResultImpl implements ISearchResult{

    private Queue<FileSearchResult> searchResults;

    public SearchResultImpl()
    {
        searchResults = new PriorityQueue<>();
    }

    public void updateResult(String fileName, Integer resPercentage)
    {
        searchResults.add(new FileSearchResult(fileName,resPercentage));

        //remove when the result doesn't make it in top 10
        if(searchResults.size() > Constants.TOP_RESULT_LIMIT)
            searchResults.poll();
    }

    public void displaySearchResult(DisplayType displayType) {

        IDisplay display = DisplayFactory.getDisplay(displayType);
        if(searchResults.isEmpty())
        {
            display.printMessage("No matches found");
        }
        while(!searchResults.isEmpty())
        {
            display.printMessage(searchResults.poll().toString());
        }
    }

    @Override
    public Queue<FileSearchResult> getSerachResults() {
        return searchResults;
    }

    class FileSearchResult implements Comparable<FileSearchResult>{

        String fileName;
        Integer resPercentage;

        public FileSearchResult(String fileName, Integer resPercentage) {
            this.fileName = fileName;
            this.resPercentage = resPercentage;
        }


        @Override
        public int compareTo(FileSearchResult o) {
            return o.resPercentage.compareTo(this.resPercentage);
        }

        @Override
        public String toString() {
            return  fileName + ":" + resPercentage + "%";
        }
    }

}
