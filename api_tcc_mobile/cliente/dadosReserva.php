<?php

  require_once('../conexao_db.php');

  $modo = $_GET['modo'];

  switch ($modo) {
    case 'unidade':

    $selectUnidades = "select * from tbl_unidades;";
    $execUni = mysql_query($selectUnidades);

    if(mysql_num_rows($execUni) > 0){

      $retorno = array();
      while($preencher = mysql_fetch_array($execUni)){

        $objeto = array("nome_unidade" => $preencher["nome_unidade"],
                        "id_unidade" => $preencher["id_unidade"]);
          $retorno[] = $objeto;

      }
      echo json_encode($retorno);

    }

    break;

    case 'periodo':

    $selectPeriodo = "select * from tbl_periodo;";
    $execPeriodo = mysql_query($selectPeriodo);

    if(mysql_num_rows($execPeriodo) > 0){

      $retorno = array();
      while($preencher = mysql_fetch_array($execPeriodo)){

        $objeto = array("nome_periodo" => $preencher["nome_periodo"],
                        "id_periodo" => $preencher["id_periodo"]);
          $retorno[] = $objeto;

      }
      echo json_encode($retorno);

    }

    break;

  }

?>
