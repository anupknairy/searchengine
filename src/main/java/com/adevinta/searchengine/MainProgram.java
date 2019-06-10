package com.adevinta.searchengine;

import com.adevinta.searchengine.data.DataLoaderFactory;
import com.adevinta.searchengine.data.DataLoaderType;
import com.adevinta.searchengine.data.IDataLoader;
import com.adevinta.searchengine.display.DisplayType;
import com.adevinta.searchengine.search.ISearchResult;
import com.adevinta.searchengine.search.SearchEngine;
import com.adevinta.searchengine.search.SearchEngineImpl;

import java.util.Scanner;

public class MainProgram {

    public static void main(String args[])
    {

        String directory;

        try (Scanner sc = new Scanner(System.in))
        {
            if(args.length == 0)
            {
                System.out.println("Directory location is not given in argument..!\nPlease enter in console");
                directory = sc.nextLine();

            } else {
                directory = args[0];
            }

            IDataLoader dataLoader = DataLoaderFactory.getDataLoader(DataLoaderType.TEXT);
            dataLoader.loadData(directory);


            while (true) {
                System.out.print("search> ");
                final String line = sc.nextLine();
                SearchEngine se = new SearchEngineImpl();
                ISearchResult result = se.doSearch(line);
                result.displaySearchResult(DisplayType.CONSOLE);
            }
        }

    }
}
