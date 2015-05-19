

Solution:

I used crawler4j library to crawl the site. To be able to run this:
1. create /data folder
2. get the code and run the test -   mvn test -Dtest=RunCrawlerTest
3. it will created a text file /data/output/visit.csv to include all productname, color, and price and /data/output/fetch.csv for all fetched urls with status code.
4. it will take some time because I set 500 ms politeness delay policy
5. maximum pages to fetch are set to 500 - just to make sure client won't block my IP. in real case, this limit can be removed. 
6. I commented out all categories except new_arrival for testing purpose. see test case... 
7. I set the dept for crawling to 1.
8. I set the numberOfCrawlers to 10.
9. all settings are defined in base class that can we changed in extended class.


For each new client:
1. create a new package
2. define the Crawler class to parse the data for the new client.
3. define RunCrawler which extends the base class to run Crawler for the new client.
4. Crawler config attributes can be updated in extended class before starting the crawler.

 
To be able to extend this:

1. I would create a initial catalog-URL table for each client website instead of hardcoded in unit test
2. I would add the product data into database instead of File.
3. I would create a cron job for each client.
4. I would add more error handling and monitoring tools
5. I would update regular expression to exclude all urls that I won't need to be fetched for efficiency.



