<?php

$erro_conexao = false;


//$conexao = mysql_connect('127.0.0.1','root','bcd127'); //casa Marcos
//$conexao = mysql_connect('10.107.134.33','root','bcd127'); //terça
//$conexao = mysql_connect('10.107.134.11','root','bcd127');//quarta
//$conexao = mysql_connect('10.107.134.52','root','bcd127');//quarta
//$conexao = mysql_connect('localhost','root','bcd127');//segunda e quinta
$conexao = mysql_connect('192.168.1.1','theribsws','wstheribs');

//$conexao = mysql_connect('localhost','root','bcd127');

// Verifica se a conexão deu certo e informa ao usuário se não estiver ocorrido com sucesso
if(mysql_select_db('dbtheribs_ws')){
  //echo('sucesso');
}else{
  $erro_conexao = true;
}

?>
