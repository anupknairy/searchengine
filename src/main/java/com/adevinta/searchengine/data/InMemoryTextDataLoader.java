package com.adevinta.searchengine.data;

import com.adevinta.searchengine.Constants;
import com.adevinta.searchengine.display.DisplayFactory;
import com.adevinta.searchengine.display.DisplayType;
import com.adevinta.searchengine.display.IDisplay;

import java.io.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class InMemoryTextDataLoader implements IDataLoader {

    private IndexedTextData indexedTextData;
    private IDisplay display = DisplayFactory.getDisplay(DisplayType.CONSOLE);

    @Override
    public synchronized void loadData(String directory) {

        indexedTextData = IndexedTextData.getInstance();

        File folder = new File(directory);
        if(!folder.exists())
        {
            display.printError("Invalid directory location : "+directory);
            System.exit(0);
        }

        readTxtFiles(folder);

        int numOfFilesRead = indexedTextData.getFilesCount();
        if(numOfFilesRead == 0)
        {
            display.printError("Empty Directory.. Exiting");
            System.exit(0);
        }

        display.printMessage(numOfFilesRead + " Files read in directory "+directory);

    }

    private void readTxtFiles(File folder) {
        for(final File file : folder.listFiles())
        {
            if(file.isFile() && file.getName().endsWith(Constants.TXT_EXTN))
            {
                readFile(file);
            } else if(file.isDirectory())
            {
                //recursively call other directories
                readTxtFiles(file);
            }
        }
    }

    private void readFile(File file) {

        try(BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            Set<String> words = new HashSet<>();
            String contentLine;
            while((contentLine = br.readLine()) != null)
            {
                Arrays.stream(contentLine.split(Constants.SPACE_DELIMETER))
                        .forEach(word->words.add(
                                word.toLowerCase()
                                        .replaceAll(Constants.WORD_FORMAT_PATTERN,"")));
            }
            indexedTextData.getData().put(file.getName(),words);
            indexedTextData.setFilesCount(indexedTextData.getFilesCount()+1);

        } catch (FileNotFoundException e) {
            display.printError(e.getMessage());
        } catch (IOException e) {
            display.printError(e.getMessage());
        }

    }
}
