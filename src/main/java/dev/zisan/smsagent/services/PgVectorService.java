package dev.zisan.smsagent.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PgVectorService {
    private final VectorStore vectorStore;

    public void addToDB(Resource resumePdf) {

        FilterExpressionBuilder b = new FilterExpressionBuilder();
        Filter.Expression expression = b.eq("file_name", resumePdf.getFilename()).build();
        vectorStore.delete(expression);

        var pdfReader = new PagePdfDocumentReader(resumePdf);
        TextSplitter textSplitter = new TokenTextSplitter();
        vectorStore.accept(textSplitter.apply(pdfReader.get()));
        log.info("Vector store loaded with resume data.");
    }

    public void deleteResumeFromDB(String filename) {
        FilterExpressionBuilder b = new FilterExpressionBuilder();
        Filter.Expression expression = b.eq("file_name", filename).build();
        vectorStore.delete(expression);
        log.info("Resume data deleted from vector store.");
    }


}
