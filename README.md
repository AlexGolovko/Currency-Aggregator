# Currency-Aggregator
Currency Aggregator Services



/CurrencyAggregator/upload  --  link for upload your XML  JSON and CVS files by POST request.
/CurrencyAggregator/rates   --  link for GETing your currency rates by currency name and by buy or sell and using sorting or not.
    example:
      /CurrencyAggregator/rates?currency=USD&sorting=false&operType=BUY
      /CurrencyAggregator/rates?currency=USD&operType=BUY
/CurrencyAggregator/rates?bank=bankName&currency=currencyName&operType=operationType -- use PUT for update currency by bank
/CurrencyAggregator/rates?bank=bankName -- use for DELETE all offers for bank
/CurrencyAggregator/report  --  for GETTING report with best offers from all banks
