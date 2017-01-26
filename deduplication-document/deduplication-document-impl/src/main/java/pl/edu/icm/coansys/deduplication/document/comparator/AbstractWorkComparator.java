/*
 * This file is part of CoAnSys project.
 * Copyright (c) 2012-2015 ICM-UW
 * 
 * CoAnSys is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * CoAnSys is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with CoAnSys. If not, see <http://www.gnu.org/licenses/>.
 */
package pl.edu.icm.coansys.deduplication.document.comparator;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pl.edu.icm.coansys.commons.java.DocumentWrapperUtils;
import pl.edu.icm.coansys.commons.java.StringTools;
import pl.edu.icm.coansys.deduplication.document.voter.SimilarityVoter;
import pl.edu.icm.coansys.deduplication.document.voter.Vote;
import pl.edu.icm.coansys.models.DocumentProtos;

/**
 *
 * @author Łukasz Dumiszewski
 * @author Artur Czeczko
 *
 */
public abstract class AbstractWorkComparator implements WorkComparator {

    private static Logger logger = LoggerFactory.getLogger(AbstractWorkComparator.class);
    private List<SimilarityVoter> similarityVoters;

    /**
     * Tells whether the given documents are duplicates.
     * @param doc1
     * @param doc2
     * @param debugInfo
     * @return 
     */
    @Override
    public boolean isDuplicate(DocumentProtos.DocumentMetadata doc1, DocumentProtos.DocumentMetadata doc2, StringBuilder debugInfo) {

        List<Float> probabilities = new ArrayList<>();
        List<Float> weights = new ArrayList<>();

        String ids = doc1.getKey() + ", " + doc2.getKey();
        StringBuilder logBuilder = new StringBuilder();

        //StringBuilder debugOutputBuilder = new StringBuilder();
        storeDebugInfo(debugInfo, compactTitle(doc1), ", ", compactTitle(doc2));

        if (similarityVoters != null) {
            for (SimilarityVoter voter : similarityVoters) {
                storeDebugInfo(debugInfo, "#", voter.getClass().getSimpleName());
                Vote vote = voter.vote(doc1, doc2);
                Vote.VoteStatus status = vote.getStatus();
                storeDebugInfo(debugInfo, ":", status.name());

                switch (status) {
                    case EQUALS:
                        logger.info("Documents " + ids + " considered as duplicates because of result EQUALS of voter "
                                + voter.getClass().getName());
                        return true;
                    case NOT_EQUALS:
                        return false;
                    case ABSTAIN:
                        continue;
                    case PROBABILITY:
                        logBuilder.append(" -- voter ").append(voter.getClass().getName())
                                .append(" returned probability ").append(vote.getProbability())
                                .append(", weight ").append(voter.getWeight()).append('\n');
                        probabilities.add(vote.getProbability());
                        weights.add(voter.getWeight());
                        storeDebugInfo(debugInfo, "-", vote.getProbability());
                }
            }
        }

        boolean result = calculateResult(probabilities, weights, debugInfo);

        if (result) {
            logger.info(ids + " considered as duplicates because:\n" + logBuilder.toString());
        }

        return result;
    }

    protected static void storeDebugInfo(StringBuilder builderToStore, Object... infos) {
        if (builderToStore != null) {
            for (Object info : infos) {
                builderToStore.append(info);
            }
        }
    }

    private static String compactTitle(DocumentProtos.DocumentMetadata doc) {
        String docKey = DocumentWrapperUtils.getMainTitle(doc);
        return StringTools.normalize(docKey);
    }

    protected abstract boolean calculateResult(List<Float> probabilites, List<Float> weights, StringBuilder logBuilder);

    //******************** SETTERS ********************
    public void setSimilarityVoters(List<SimilarityVoter> similarityVoters) {
        this.similarityVoters = similarityVoters;
    }
}
