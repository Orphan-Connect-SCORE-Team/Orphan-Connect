var braintree = require("braintree");
var express = require("express");
var app = express();

const http = require('http');

const hostname = '127.0.0.1';
const port = 3000;

app.get('/', (req, res) => res.send('Hello World!'));

app.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});

var gateway = braintree.connect({
    environment:  braintree.Environment.Sandbox,
    merchantId:   '',
    publicKey:    '',
    privateKey:   ''
});

gateway.clientToken.generate(//{
  //customerId: aCustomerId
//}, 
	function (err, response) {
  		var clientToken = response.clientToken
});
app.get("/client_token", function (req, res) {
  gateway.clientToken.generate({}, function (err, response) {
    res.send(response.clientToken);
  });
  console.log("token given");
});