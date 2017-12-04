<?php

  require_once('../conexao_db.php');

  if(isset($_GET['modo'])){

    $modo = $_GET['modo'];

    switch ($modo) {
      case 'carregar_pratos':

        $select = "select tbl_produto.nome_produto from tbl_produto;";

        $exec = mysql_query($select);

        $retorno = array();

        while($rs=mysql_fetch_array($exec)){

          $objeto = array("nome_produto" => $rs["nome_produto"],
                          "quantidade" => 0);
            $retorno[] = $objeto;
        }

        echo json_encode($retorno);

      break;
    }

  }

?>
