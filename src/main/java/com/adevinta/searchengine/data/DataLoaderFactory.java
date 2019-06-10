package com.adevinta.searchengine.data;

/**
 * Factory that returns different implementation of data loaders
 * based on give type.
 */
public class DataLoaderFactory {

    public static IDataLoader getDataLoader(DataLoaderType type)
    {
        switch (type)
        {
            case TEXT:
                default:
                    return new InMemoryTextDataLoader();
        }
    }
}
