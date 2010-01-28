package edu.bgu.ir2009.auxiliary;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * User: Henry Abravanel 310739693 henrya@bgu.ac.il
 * Date: 26/12/2009
 * Time: 15:59:15
 */
public class PostingFileUtils {
    private static final Logger logger = Logger.getLogger(PostingFileUtils.class);

    public static InMemoryIndex saveIndex(Map<String, TermData> index, Map<String, Map<String, Double>> documentsVectors, Configuration config) throws IOException {
        logger.info("Saving index to indexFile: " + config.getIndexFileName() + " Reference indexFile: " + config.getIndexReferenceFileName());
        File indexFile = new File(config.getIndexFileName());
        File indexRefFile = new File(config.getIndexReferenceFileName());
        InMemoryIndex res = new InMemoryIndex(config, false);
        FileWriter indexWriter = new FileWriter(indexFile);
        FileWriter indexRefWriter = new FileWriter(indexRefFile);
        int saved = 0;
        int totalToSave = index.size() + documentsVectors.size();
        long pos = 0;
        for (TermData td : index.values()) {
            String termSerialized = td.getSavedString();
            indexWriter.write(termSerialized + '\n');
            res.addTerm(td.getTerm(), pos, termSerialized.length());
            indexRefWriter.write(td.getTerm() + ":" + pos + ":" + termSerialized.length() + "\n");
            pos += termSerialized.length() + 1;
            saved++;
            UpFacade.getInstance().addIndexSavingEvent(saved, totalToSave);
        }
        indexWriter.write('\n');
        indexRefWriter.write('\n');
        pos++;
        for (String docNo : documentsVectors.keySet()) {
            StringBuilder builder = new StringBuilder();
            builder.append(docNo).append(':');
            Map<String, Double> docVector = documentsVectors.get(docNo);
            for (String term : docVector.keySet()) {
                builder.append(term).append('=').append(docVector.get(term)).append(',');
            }
            String serializedDocVector = builder.toString();
            indexWriter.write(serializedDocVector + '\n');
            res.addDocVector(docNo, pos, serializedDocVector.length());
            indexRefWriter.write(docNo + ":" + pos + ":" + serializedDocVector.length() + "\n");
            pos += serializedDocVector.length() + 1;
            saved++;
            UpFacade.getInstance().addIndexSavingEvent(saved, totalToSave);
        }
        indexWriter.close();
        indexRefWriter.close();
        logger.info("Finished saving index...");
        return res;
    }

    public static InMemoryIndex loadInMemoryIndex(Configuration config) throws IOException {
        InMemoryIndex res = new InMemoryIndex(config, true);
        res.load();
        return res;
    }

    public static InMemoryDocs loadInMemoryDocs(Configuration config) throws IOException {
        InMemoryDocs res = new InMemoryDocs(config);
        res.load();
        return res;
    }
}