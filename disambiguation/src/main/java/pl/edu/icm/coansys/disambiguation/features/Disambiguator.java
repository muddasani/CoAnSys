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

package pl.edu.icm.coansys.disambiguation.features;

import java.util.List;

import org.slf4j.LoggerFactory;

/**
 * A heuristic for assessing whether two objects, described by two lists of feature values,
 * are similar or not.    
 * 
 * @author pdendek
 * @version 1.0
 * @since 2012-08-07
 */
public class Disambiguator {
	
    private static final org.slf4j.Logger logger 
		= LoggerFactory.getLogger( Disambiguator.class );
	
	/**
	 * 
	 * @param f1 list of feature values associated with the first owner
	 * @param f2 list of feature values associated with the second owner
	 * @return value reflecting affinity between owners. 
	 * In the basic implementation this value is a size of an intersection between feature values' lists 
	 */
	public double calculateAffinity( List<Object> f1, List<Object> f2 ) {
		double sum = f1.size() + f2.size();
		f1.retainAll(f2);
		double intersection = f1.size();
		if ( sum <= 0 ) {
			logger.warn( "Negative or zero value of lists sum." );
			return 0;
		}
		return intersection / sum;
	}
	
	/**
	 * 
	 * @return {@link Disambiguator} id.
	 */
	public String getName() {
		return Disambiguator.class.getSimpleName();
	}
}
