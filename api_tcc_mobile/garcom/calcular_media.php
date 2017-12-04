<?php

  require_once('../conexao_db.php');

  if(isset($_GET['id_funcionario'])){

    $id_funcionario = $_GET['id_funcionario'];

    $select = "call media_avaliacoes($id_funcionario);";

    $exec = mysql_query($select);

    $media = mysql_fetch_array($exec);

    $objeto = array("media" => $media['media']);

    if($media['media'] == null){

      $objeto = array("resultado" => "sem_nota");

      echo json_encode($objeto);
    }else{

      echo json_encode($objeto);
    }

  }else{

    $objeto = array("resultado" => "acesso_negado");

    echo json_encode($objeto);
  }


?>
