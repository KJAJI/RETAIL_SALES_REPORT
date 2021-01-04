package Retail_Sales

import org.apache.spark.sql.SparkSession


object Retail_Report {

  def main(args:Array[String]): Unit = {

    //<editor-fold desc="Create Spark Session">

    val spark = SparkSession.builder
                            .appName("Retail ETL Application")
                            .master("local[*]")
                            .getOrCreate

    //</editor-fold>

    //<editor-fold desc="Input Paths">

    val retailPath = args(0)
    val outputPath = args(2)

    //</editor-fold>

    //<editor-fold desc = "Create DataFrames">

        val retailDF = spark.read.format("csv")
                            .option("header", "true")
                            .load(retailPath)

           retailDF.createOrReplaceTempView("retail")

    //</editor-fold>

    //<editor-fold desc="Select the Columns">

    val reportDF = spark.sql("select CONCAT(customer_first_name,' ', customer_last_name) AS CUSTOMER_NAME,ORDER_DATE,CATEGORY_NAME AS CATEGORY,product_name,product_price, '' AS NET_AMOUNT,'' AS TAX_VALUE,ORDER_STATUS,order_item_subtotal,order_item_product_price from retail")
    reportDF.printSchema()
    reportDF.show()

    //</editor-fold>

    //<editor-fold desc="Write Output File">

    reportDF.coalesce(1).write
                .option("header","true")
                .mode("overwrite")
                .csv(outputPath)

    //</editor-fold>

  }

}