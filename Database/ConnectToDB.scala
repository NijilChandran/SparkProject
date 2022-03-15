
/**
 * Connect to a public Postgres DB from Spark-shell
 * https://rnacentral.org/help/public-database
 * wget https://jdbc.postgresql.org/download/postgresql-42.2.6.jar
+----+-------+----+-------------+---------+-------+-------------------+---------+--------------------+-------+-----+---------+
|dbid|created|last|          upi|version_i|deleted|          timestamp|userstamp|                  ac|version|taxid|       id|
+----+-------+----+-------------+---------+-------+-------------------+---------+--------------------+-------+-----+---------+
|   5|     53|  98|URS00000B15DA|        1|      Y|2015-05-16 16:43:53|   RNACEN|OTTHUMT00000106564.1|      1| 9606|196799539|
|   5|     53|  98|URS00000A54A6|        1|      Y|2015-05-16 16:43:53|   RNACEN|OTTHUMT00000416802.1|      1| 9606|196793709|
+----+-------+----+-------------+---------+-------+-------------------+---------+--------------------+-------+-----+---------+
 * */

:require N:\\Nijil_scala\\postgresql-42.2.6.jar

val url = "jdbc:postgresql://hh-pgsql-public.ebi.ac.uk:5432/pfmegrnargs"
import java.util.Properties
val connectionProperties = new Properties()
connectionProperties.put("user", "reader")
connectionProperties.put("password", "NWDMCE5xdipIjRrp")
connectionProperties.setProperty("Driver", "org.postgresql.Driver")

val query1 = "(SELECT * FROM xref WHERE ac IN ('OTTHUMT00000106564.1', 'OTTHUMT00000416802.1')) as q1"
val query1df = spark.read.jdbc(url, query1, connectionProperties)
query1df.show(false)

//Create a result that can have special character '|'
val query2 = "(SELECT upi,CONCAT_WS(' | ',userstamp,upi,dbid,created) as pipe_array,id FROM xref WHERE ac IN ('OTTHUMT00000106564.1', 'OTTHUMT00000416802.1')) as q1"
val query2df = spark.read.jdbc(url, query2, connectionProperties)
query2df.show(false)

//Replace pipe delimiter as #
val query2df_replaced = query2df.withColumn("pipe_array",regexp_replace($"pipe_array", "\\|", "#"))
query2df_replaced.write.option("header",true).format("csv").save("N:\\Nijil_scala\\query2df_replaced_csv")

// Columns to uppercase in final csv file
query2df.select(query2df.columns.map(x => col(x).as(x.toUpperCase)): _*).show(false)
import org.apache.spark.sql.functions._
var query2df_upper = query2df_replaced.select(query2df_replaced.columns.map(x => col(x).as(x.toUpperCase)): _*)
query2df_upper.write.option("header",true).format("csv").save("N:\\Nijil_scala\\query2df_upper_csv")

