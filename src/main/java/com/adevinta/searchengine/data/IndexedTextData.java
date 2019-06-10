package com.adevinta.searchengine.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IndexedTextData {


    private  Map<String, Set<String>> data;
    private int filesCount;
    private static IndexedTextData indexedTextData = new IndexedTextData();

    public static IndexedTextData getInstance()
    {
        return indexedTextData;
    }

    private IndexedTextData() {

        data = new HashMap<>();
    }

    public Map<String, Set<String>> getData() {
        return data;
    }

    public int getFilesCount() {
        return filesCount;
    }

    public void setFilesCount(int filesCount) {
        this.filesCount = filesCount;
    }

    public void setData(HashMap<String, Set<String>> data) {
        this.data = data;
    }
}

