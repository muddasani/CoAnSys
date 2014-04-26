--
-- This file is part of CoAnSys project.
-- Copyright (c) 2012-2013 ICM-UW
-- 
-- CoAnSys is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as published by
-- the Free Software Foundation, either version 3 of the License, or
-- (at your option) any later version.

-- CoAnSys is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with CoAnSys. If not, see <http://www.gnu.org/licenses/>.
--

%default parallel 10
%default tmpCompressionCodec gz
%default mapredChildJavaOpts -Xmx8000m

%default jars '*.jar'
%default commonJarsPath '../../../../document-similarity-workflow/target/oozie-wf/lib/$jars'

REGISTER '$commonJarsPath'

SET default_parallel $parallel
SET mapred.child.java.opts $mapredChildJavaOpts
SET pig.tmpfilecompression true
SET pig.tmpfilecompression.codec $tmpCompressionCodec
%DEFAULT ds_scheduler default
SET mapred.fairscheduler.pool $ds_scheduler
--SET pig.noSplitCombination true;
IMPORT 'macros.pig';

-------------------------------------------------------
-- business code section
-------------------------------------------------------
%default inputPathProto 'protos'
%default inputPathDocSimMajor 'docsim-major'
%default inputPathDocSimMinor 'docsim-minor'
%default outputPathRecalc 'output'
%default finalOutputPath 'outputF'

-- read data from docsim
A = load '$inputPathDocSimMajor' as (k1:chararray,k2:chararray,sim:double);
-- separate pairs into two groups
-- (a) a group to be enhanced [sim >= 0.999]
-- (a) a group which will remain untouched [sim < 0.999]
SPLIT A INTO
	A1 if sim >= 0.999,
	A11 if sim < 0.999;
store A11 into '$outputPathRecalc/pairs-with-sim-lower-then-threshold';

-- save pairs which docsim should be recalculated
A2 = foreach A1 generate k1,k2;
store A2 into '$outputPathRecalc/pairs-to-process';

-- get keys of documents which will be recalculated
A2x = load '$outputPathRecalc/pairs-to-process' as (k1:chararray,k2:chararray);
B1 = foreach A2x generate k1 as kx;
B2 = foreach A2x generate k2 as kx;
B3 = union B1,B2;
distinct B3;
store B3 into '$outputPathRecalc/document-keys-to-process';

-- find appropriated metadata for the selected keys
B3x = load '$outputPathRecalc/documents-keys-to-process' as (kx:chararray);
C = LOAD '$inputPathProto' USING pl.edu.icm.coansys.commons.pig.udf.
	RichSequenceFileLoader('org.apache.hadoop.io.Text','org.apache.hadoop.io.BytesWritable') 
	as (k:chararray, v:bytearray); 
D = join B3x by kx left inner, C by k;
D1 = foreach D generate kx as k, v; 
store D into '$outputPathRecalc/documents-to-process' USING pl.edu.icm.coansys.commons.pig.udf.
	RichSequenceFileLoader('org.apache.hadoop.io.Text','org.apache.hadoop.io.BytesWritable');
