package com.adevinta.searchengine.search;

import com.adevinta.searchengine.data.InMemoryTextDataLoader;
import com.adevinta.searchengine.data.IndexedTextData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class SearchEngineImplTest {

    SearchEngineImpl searchEngine ;
    Map<String, Set<String>> data;

    @Before
    public void setUp() throws Exception {
        searchEngine =  new SearchEngineImpl();
        String directory = "src/test/resources/testdata";
        InMemoryTextDataLoader dataLoader = new InMemoryTextDataLoader();
        dataLoader.loadData(directory);
        data = IndexedTextData.getInstance().getData();
    }

    @After
    public void tearDown() throws Exception {
        searchEngine = null;
        IndexedTextData.getInstance().setFilesCount(0);
        IndexedTextData.getInstance().setData(new HashMap<>());
    }

    @Test
    public void calculateHitPercentage() {
        int inputWordCount = 10;
        int wordsFoundInAFile = 2;

        assertEquals(20,searchEngine.calculateHitPercentage(wordsFoundInAFile,inputWordCount));
    }

    @Test
    public void getFormattedInputWordList() {
        String input = "Hello (all) Welcome.";
        List<String> formattedInput = searchEngine.getFormattedInputWordList(input);

        assertEquals(3, formattedInput.size());
        assertEquals("all", formattedInput.get(1));
        assertEquals("welcome", formattedInput.get(2));
    }

    @Test
    public void testDoSearch()
    {
        String input1 = "Yaay Hello Friends how are";
        ISearchResult result = searchEngine.doSearch(input1);

        //Only two files has this input partially
        assertEquals(2,result.getSerachResults().size());

        SearchResultImpl.FileSearchResult firstRes = result.getSerachResults().poll();
        assertEquals(80,(int)firstRes.resPercentage);
        assertEquals("txt1.txt",firstRes.fileName);


        SearchResultImpl.FileSearchResult secondRes = result.getSerachResults().poll();
        assertEquals(20,(int)secondRes.resPercentage);
        assertEquals("txt3.txt",secondRes.fileName);

        String input2 = "There's No Result";
        result = searchEngine.doSearch(input2);

        //No results
        assertEquals(0,result.getSerachResults().size());

    }
}