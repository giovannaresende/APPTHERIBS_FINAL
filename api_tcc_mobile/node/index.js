var app = require('express')();
var http = require('http').createServer(app);
//criando variavel de socket
var io = require('socket.io').listen(http);

var mysql = require('mysql');

var conexao = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "bcd127",
  database: "dbtheribs_ws"
});

app.get('/' , function(req, res){
		res.send("node tcc");
});


app.get('/chamar_garcom', function(req, res){

	//variaveis responsaveis por armazenar dados passado pelo usuario
	var _codigo = req.query.codigo;

	//emitindo para todos que estiverem socket
	io.sockets.emit(_codigo, "Há uma mesa te chamando...");
  res.send(_codigo);
});

//escutando ação connection
io.sockets.on('connection', function(cliente){

	console.log("Usuario conectado");

});

http.listen(8888, function(){

	console.log("servidor rodando na porta 8888");

});
