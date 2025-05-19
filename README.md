# eMark-internship-project
This project was developed during a two-month internship at E-Mark, the national operator of Armenia's product traceability system. It focuses on automating the trimming of DataMatrix codes based on the logic defined by each product group. The tool was successfully integrated into the company’s mobile application, where it simplifies the aggregation process during product scanning.
Project Purpose
The main objective of this project is to:
Automate the cutting of DataMatrix codes according to trimming rules linked to each product_group_id.
Reduce human error in the aggregation process.
Prepare DataMatrix codes for automatic inclusion in the traceability system.
Assist businesses by removing the need for technical manual code editing.
The project is composed of three Java programs:

PostgreSQLToCSV.java
Extracts general product and code metadata from the PostgreSQL database into a .csv file.

ExportCutCodes.java
Exports the full and trimmed codes to help analyze and compare code formats across product groups.

datamatrix.java
A trimming algorithm that:

Accepts a list of full DataMatrix codes and their associated product group IDs.

Applies trimming logic based on group-specific rules.

Outputs the cleaned codes for use in aggregation workflows.

