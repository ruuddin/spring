package hello;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class WordCountApp {
	
	private static String SPACE = " ";
	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.err.println("Usage: JavaWordCount <file>");
			System.exit(1);
		}
		JavaSparkContext ctx = null;
		try {
			SparkConf sparkConf = new SparkConf().setAppName("JavaWordCount");
			ctx = new JavaSparkContext(sparkConf);
			//Read file into memory
			JavaRDD<String> lines = ctx.textFile(args[0], 1);

			/**
			 * Convert lines to words
			 * 1. flatMap - convert each line to words and flatten the output of each line processing.
			 */
			JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator());
			
			//map word to tuple with count 1. tuple = word, count
			JavaPairRDD<String, Integer> ones = words.mapToPair(word -> new Tuple2<>(word, 1));
			//sum the count 
			JavaPairRDD<String, Integer> counts = ones.reduceByKey((Integer i1, Integer i2) -> i1 + i2);

			List<Tuple2<String, Integer>> output = counts.collect();
			for (Tuple2<?, ?> tuple : output) {
				System.out.println(tuple._1() + ": " + tuple._2());
			}
			
		} finally {
			if(ctx != null)
				ctx.stop();
		}
	}
}
