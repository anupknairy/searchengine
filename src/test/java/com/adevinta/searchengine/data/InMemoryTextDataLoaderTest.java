package com.adevinta.searchengine.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class InMemoryTextDataLoaderTest {

    Map<String, Set<String>> data;

    @Before
    public void setUp() throws Exception {
        String directory = "src/test/resources/testdata";
        InMemoryTextDataLoader dataLoader = new InMemoryTextDataLoader();
        dataLoader.loadData(directory);
        data = IndexedTextData.getInstance().getData();
    }

    @After
    public void tearDown() throws Exception {

        IndexedTextData.getInstance().setFilesCount(0);
        IndexedTextData.getInstance().setData(new HashMap<>());
    }


    @Test
    public void testNumberOfFilesLoaded() {

        assertEquals(3,data.size());

    }

    @Test
    public void testLoadedNumberOfWordsInTxt1() {

        assertEquals(8,data.get("txt1.txt").size());
    }

}