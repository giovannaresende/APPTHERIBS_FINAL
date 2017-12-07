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

      case 'verificar':

        $select = "select * from verificar_pedido_aberto where id_mesa = ".$_GET['id_mesa'].";";
        $exec = mysql_query($select);
        if(mysql_num_rows($exec) > 0){

          $objeto = array("resultado" => "ok");

          echo json_encode($objeto);
        }else{

          $objeto = array("resultado" =>"erro");
          echo json_encode($objeto);
        }
      break;
    }

  }

?>
