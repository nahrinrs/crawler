Context

Our Customer Success team helps grow the Attune business grow by getting as many customers as possible onto the platform, helping
each customer leverage Attune as broadly as possible within their website or app customer experience, ensuring the integration
remains robust post-integration (with analytics being a primary indicator of health), and providing analytics back to the
customer to make the return on investment (ROI) clear.

One challenge with a platform-as-a-service offering like Attune�s is that there is a moderate integration effort to get up and
running and get initial results.  One possible antidote to the time-to-first-success challenge is to use more readily available
customer data to provide a preliminary estimate of potential ROI pre-integration.

The Challenge

Build a crawler that we can point to Bonobos.com to automatically discover each product collection and product on the web site.
For this exercise, you may focus on the product collections on category pages only.  The output from your crawler should be a
list of product collections, with metadata including what the collection represents the page it�s located on, and the products in
the collection.  Also generate a master list of all products discovered in the collections scraped including a list of scrape-
able product attributes (name, prices, cuts, etc.) from each product's details page.

Bonus points for making the scraper flexible and/or configurable to be easily adaptable to other ecommerce sites.

Just like in real life, use your best judgement of solve the above and ask questions wherever clarification on the requirements
or potential approach are desired.  Also, feel free to use any resources you can find to get the job done better or more quickly.

Artifacts to be returned:

1.     A short description of how you approached the problem including design tradeoffs and future improvements. 
2.     Working code with instructions on how to run it. 
3.     A test suite that runs the scraper and evaluates the output for any bugs that can be revealed by examining the output. 
4.     Anything else you think would be valuable.






Solution:
I used crawler4j library to crawl the site. To be able to run this:
1. create /data folder
2. get the code and run AppTest
3. it will created a text file visit.csv to all productname, color, and price
4. it will take some time because I set 1 second politeness delay policy
5. maximum pages to fetch are set to 200
6. 
