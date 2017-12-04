<?php

  require_once("../conexao_db.php");

  if(isset($_GET['nome_funcionario'])){

    $nome = $_GET['nome_funcionario'];
    $id_funcionario = $_GET['id_funcionario'];

    $update = "update tbl_funcionarios set nome_funcionario = '$nome' where id_funcionario = $id_funcionario;";

    if(mysql_query($update)){
        echo($id_funcionario);
    }else{

      echo($id_funcionario);
    }

  }

?>
