<?php

  require_once('../conexao_db.php');

  if(isset($_GET['id_funcionario'])){

    $id_funcionario = $_GET['id_funcionario'];

    $select = "select * from selecionar_mesa where id_funcionario = $id_funcionario;";

    $exec = mysql_query($select);

    $retorno = array();
    while($preencher = mysql_fetch_array($exec)){

      $objeto = array("nome_mesa" => $preencher['nome_mesa'],
                      "id_mesa" => $preencher['id_mesa']);

      $retorno[] = $objeto;

    }

    echo json_encode($retorno);

  }

?>
