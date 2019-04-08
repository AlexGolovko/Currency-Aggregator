# Currency-Aggregator
Currency Aggregator Services



/CurrencyAggregator/upload  --  link for upload your XML  JSON and CVS files by POST request.<br>
/CurrencyAggregator/rates   --  link for GETing your currency rates by currency name and by buy or sell and using sorting or not.<br>
/CurrencyAggregator/rates?bank=bankName&currency=currencyName&operType=operationType -- use PUT for update currency by bank.<br>
/CurrencyAggregator/rates?bank=bankName -- use for DELETE all offers for bank.<br>
/CurrencyAggregator/report  --  for GETTING report with best offers from all banks.<br>
