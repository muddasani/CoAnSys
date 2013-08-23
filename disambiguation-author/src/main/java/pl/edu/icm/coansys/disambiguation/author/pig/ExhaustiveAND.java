/*
 * This file is part of CoAnSys project.
 * Copyright (c) 2012-2013 ICM-UW
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
package pl.edu.icm.coansys.disambiguation.author.pig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.DataBag;
import org.apache.pig.data.DefaultDataBag;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;
import org.slf4j.LoggerFactory;

import pl.edu.icm.coansys.commons.java.StackTraceExtractor;
import pl.edu.icm.coansys.disambiguation.author.benchmark.TimerSyso;
import pl.edu.icm.coansys.disambiguation.author.features.disambiguators.DisambiguatorFactory;

import pl.edu.icm.coansys.disambiguation.clustering.strategies.ClusteringStrategy;
import pl.edu.icm.coansys.disambiguation.clustering.strategies.CompleteLinkageHACStrategy_StateOfTheArt;
import pl.edu.icm.coansys.disambiguation.features.Disambiguator;
import pl.edu.icm.coansys.disambiguation.features.FeatureInfo;
import pl.edu.icm.coansys.disambiguation.idgenerators.IdGenerator;
import pl.edu.icm.coansys.disambiguation.idgenerators.UuIdGenerator;

public class ExhaustiveAND extends EvalFunc<DataBag> {

    private float threshold;
    private static final float NOT_CALCULATED = Float.NEGATIVE_INFINITY;
    private PigDisambiguator[] features;
    private FeatureInfo[] featureInfos;
    private float sim[][];
    private int N;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ExhaustiveAND.class);
    //benchmark 
    private boolean isStatistics = false;
    private TimerSyso timer = new TimerSyso();
    private int calculatedSimCounter; //using for statistics generated by timer
    private int timerPlayId = 0;
    private int finalClusterNumber = 0;
    private boolean gotSim = false;

    public ExhaustiveAND(String threshold, String featureDescription, String printStatistics) {
        this.threshold = Float.parseFloat(threshold);
        this.isStatistics = Boolean.parseBoolean(printStatistics);

        List<FeatureInfo> FIwithEmpties = FeatureInfo.parseFeatureInfoString(featureDescription);
        List<FeatureInfo> FIFinall = new LinkedList<FeatureInfo>();
        List<PigDisambiguator> FeaturesFinall = new LinkedList<PigDisambiguator>();

        DisambiguatorFactory ff = new DisambiguatorFactory();
        Disambiguator d;

        //separate features which are fully described and able to use
        for (FeatureInfo fi : FIwithEmpties) {
            if (fi.getDisambiguatorName().equals("")) {
                continue;
            }
            if (fi.getFeatureExtractorName().equals("")) {
                continue;
            }
            d = ff.create(fi);
            if (d == null) {
                continue;
            }
            FIFinall.add(fi);
            FeaturesFinall.add(new PigDisambiguator(d));
        }
        
		this.featureInfos = FIFinall.toArray( new FeatureInfo[ FIFinall.size() ] );
        this.features = 
        		FeaturesFinall.toArray( new PigDisambiguator[ FIFinall.size() ] );
	}
	
	/**
	 * @param Tuple with: 
	 * bag: {(contribId:chararray, sname:chararray or int, metadata:map[{(chararray or int)}])}, 
	 * <optional similarities bag: {(contrib_index_A:int, contrib_index_B:int, sim_value:float)} >
	 * @see org.apache.pig.EvalFunc#exec(org.apache.pig.data.Tuple)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public DataBag exec( Tuple input ) throws IOException {

		if ( input == null || input.size() == 0 ) return null;
		try{
			
			DataBag contribs = (DataBag) input.get(0);  
			
			if ( contribs == null || contribs.size() == 0 ) return null;
			
			//start benchmark
			if ( isStatistics ) {
				timer.play();
				calculatedSimCounter = 0;
				finalClusterNumber = 0;
				timerPlayId++;
			}
			
			Iterator<Tuple> it = contribs.iterator();	
			N = (int) contribs.size();

			List< Map<String,Object> > contribsT = new ArrayList< Map<String,Object> > ();
			String[] contribsId = new String[N];
			
			int k = 0;
			while ( it.hasNext() ) { 
				Tuple t = it.next();
				contribsId[ k++ ] = (String) t.get(0); //getting contrib id from tuple
				contribsT.add( (Map<String, Object>) t.get(2) ); //getting map with features
			}

			clearSimInit();
			
			//if we got sim values to init
			if ( input.size() == 2 ) {
				//benchamrk
				gotSim = true;
				//taking bag with calculated similarities
				DataBag similarities = (DataBag) input.get(1);  
				it = similarities.iterator();	
				//iterating through bag, dropping bag to Tuple array
				while ( it.hasNext() ) { 
					Tuple t = it.next();
					calculatedSimCounter++;
					
					int idX = (Integer) t.get(0);
					int idY = (Integer) t.get(1);
					float simValue = (Float) t.get(2);

					try {
						sim[ idX ][ idY ] = simValue;

					} catch ( java.lang.ArrayIndexOutOfBoundsException e ) {

						String m = "Out of bounds during sim init by values from input: " 
								+ "idX: " + idX + ", idY: " + idY + ", sim.length: " 
								+ sim.length + ", contrib number: " + contribsT.size();

						if ( sim.length > idX )
							m += ", sim[idX].length: " + sim[idX].length;

						m += "\n" + "During processing tuple: " + t.toString();
						
						logger.error(m, e);
						logger.info("Leaving all sim values for record");
						
						clearSimInit();
					}
				}
			}
			
			// fill sim matrix
			calculateAffinity ( contribsT );
			
			// clusterAssociations[ index_kontrybutora ] = associated cluster id
			ClusteringStrategy strategy = new CompleteLinkageHACStrategy_StateOfTheArt();
			//ClusteringStrategy strategy = new SingleLinkageHACStrategy_OnlyMax();
	        int[] clusterAssociations = strategy.clusterize( sim );
	        
	        int[][] clusters = splitIntoClusters( clusterAssociations );
	        
			//creating records for each contrib: contrib key, author UUID
			DataBag ret = createResultingTuples( clusters, contribsId );
			

	        //this action will     A D D    S O M E    I N F O R M A T I O N    T O    T I M E R    M O N I T
			if ( isStatistics ) {
				//stopping timer for current play (not thread)
				/* STATISTICS DESCRIPTION:
				 * ## this algorithm name, 
				 * ## is some sim from aproximate, 
				 * ## exhaustive execution id,
				 * ## number of contribs, 
				 * ## clusters number after exhaustive,
				 * ## calculated sim values number IN APROXIMATE which we got here
				 * ## -
				 * ## -
				 * ## time [s]
				 */
				timer.stop( "exh", gotSim, timerPlayId, N, finalClusterNumber, calculatedSimCounter, "", "" );
			}
			
	        return ret;
		
		}catch(Exception e){
			// Throwing an exception would cause the task to fail.
			logger.error("Caught exception processing input row:\n" + StackTraceExtractor.getStackTrace(e));
			return null;
		}
	}
	
	private void clearSimInit() {
		sim = new float[ N ][];
		for ( int i = 1; i < N; i++ ) {
			sim[i] = new float[i];
			for ( int j = 0; j < i; j++ )
				sim[i][j] = NOT_CALCULATED;
		}
	}
	
	private void calculateAffinity( List< Map<String,Object> > contribsT ){

		// N^2 / 2 * features number - already calculated sim values
		for ( int i = 1; i < contribsT.size(); i++ ) {
			for ( int j = 0; j < i; j++ ) {

				//if sim value is already calculated, we do not need to calculate one more time
				if( sim[i][j] != NOT_CALCULATED ) continue;
				sim[i][j] = threshold;

				for ( int d = 0; d < features.length; d++ ){
					//Taking features from each keys (name of extractor = feature name)
					//In contribsT.get(i) there is map we need.
					//From this map (collection of i'th contributor's features)
					//we take Bag with value of given feature.
					//Here we have sure that following Object = DateBag.
					Object oA = contribsT.get(i).get( featureInfos[d].getFeatureExtractorName() );
					Object oB = contribsT.get(j).get( featureInfos[d].getFeatureExtractorName() );
					
					if ( oA == null || oB == null ){
						continue;
					}
					if ( featureInfos[d].getMaxValue() == 0 ){
						continue;
					}
					
					double partial = features[d].calculateAffinity( oA, oB );
					partial = partial / featureInfos[d].getMaxValue() 
							* featureInfos[d].getWeight();
					
					sim[i][j] += partial;
				}
			}
		}
	}

	protected int[][] splitIntoClusters( int[] clusterAssociation ) {
		// cluster[ cluster id ] =  array with contributors' simIds 		
		int[][] clusters = new int[N][];
		int[] index = new int[N];
		int[] clusterSize = new int[N];
		assert( clusterAssociation.length == N );
		
		// preparing clusters' sizes
        for ( int i = 0; i < N; i++ ) {
        	clusterSize[ clusterAssociation[i] ]++;
        }
        //reserving memory
        for (int i = 0; i < N; i++) {
            if (clusterSize[i] > 0) {
                clusters[i] = new int[clusterSize[i]];
            } else {
                clusters[i] = null;
            }

            //benchmark
            finalClusterNumber += (clusterSize[i] == 0) ? 0 : 1;

            index[i] = 0;
        }
        //filling clusters
        int id;
        for (int i = 0; i < N; i++) {
            id = clusterAssociation[ i];
            clusters[ id][ index[id]] = i;
            index[ id]++;
        }

        return clusters;
    }

    protected DataBag createResultingTuples(int[][] clusters, String[] authorIds) {
        IdGenerator idgenerator = new UuIdGenerator();

        DataBag ret = new DefaultDataBag();
        DataBag contribs;
        List<String> contribKeys;

        // o( N )
        // iterating through clusters
        for (int i = 0; i < clusters.length; i++) {
            //skipping empty clusters
            if (clusters[i] == null || clusters[i].length == 0) {
                continue;
            }

            contribKeys = new ArrayList<String>();
            contribs = new DefaultDataBag();

            for (int id : clusters[i]) {
                contribKeys.add(authorIds[ id]);
                contribs.add(TupleFactory.getInstance().newTuple(authorIds[ id]));
            }

            String clusterId = idgenerator.genetareId(contribKeys);

            Object[] to = new Object[]{clusterId, contribs};
            ret.add(TupleFactory.getInstance().newTuple(Arrays.asList(to)));
        }

        return ret;
    }
}
