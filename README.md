"# crawler" 
***************************Solution approach**********************************************

******I have solved the Crawler problem by implementing below logic:*******************

1. Crawler program reads URL to crawl from "WebUrl" file.
2. Program reads all the links and image source for base/parent URL (from the file) and then it reads l
   inks and image source of sub links/child links to base URL (from the file).
4. Crawling information is stored in "List<LinkDetails>" object which is written to file under project folder \CrawlerSolution.
5. I am utilising "jsoup"library to ready the elements (i.e img) and iterating  elements based on attributes (i.e src)
   
---------------------------------------------------------------
************** How to Run**********
   1. The package is written using Eclipse IDE with Java 8, please update the build path to use Java 8.
   2. When the user runs the program from Explorer class which has main method and the solution is written to CrawlerSolution.txt file.
-----------------------------------------------------------------
************Scope for further enhancements*********************** 
Definitely, there is more to do, but due to time constraints, I have provided the Basic version. 
1. More improvements to the solution will be, first to add the Logger and remove System prints.
2. More improvements in Exception handling
3. Re-factor the code to avoid duplication.
4. Improve on test in order to increase the robustness of the solution
5. Provide solution in sophisticated format i.e CSV file.
6. More detailed Java doc 
********************************************* THE END ****************************************


 

