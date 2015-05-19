

Solution:
I used crawler4j library to crawl the site. To be able to run this:
1. create /data folder
2. get the code and run AppTest -   mvn test -Dtest=AppTest
3. it will created a text file /data/output/visit.csv to include all productname, color, and price and /data/output/fetch.csv for all fetched urls for status code.
4. it will take some time because I set 500 ms politeness delay policy
5. maximum pages to fetch are set to 500 - just to make sure client won't block my IP. in real case, this limit can be removed. 
6. I commented out all categories except 1 (new_arrival) for testing purpose. see test case... 

I think this is a good start and I should be able to extend this. To be able to extend this:

1. I would create a initial catalog-URL table for each client website instead of hardcoded in unit test under testApp2()
2. I would add result product data into database instead of File.
3. I would create a cron job for each client.
4. I would add more error handling and monitoring tools
5. I would update regular expression to exclude all urls that I won't need to be fetched.



