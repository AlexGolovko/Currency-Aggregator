# Currency-Aggregator
Currency Aggregator Services



<b>/CurrencyAggregator/upload</b>  --  link for upload your XML  JSON and CVS files by POST request.<br>
<b>/CurrencyAggregator/rates</b>   --  link for GETing your currency rates by currency name and by buy or sell and using sorting or not.<br>
<b>/CurrencyAggregator/rates?bank=bankName&currency=currencyName&operType=operationType</b> -- use PUT for update currency by bank.<br>
<b>/CurrencyAggregator/rates?bank=bankName</b> -- use for DELETE all offers for bank.<br>
<b>/CurrencyAggregator/report</b>  --  for GETTING report with best offers from all banks.<br>
