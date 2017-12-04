<?php
  require_once('../conexao_db.php');
  if(isset($_GET['cod'])){

    $cod = $_GET['cod'];

    if($cod == null){

      $objeto = array("resultado" => "erro");
      echo json_encode($objeto);
    }else{

      $sql = "select * from pratos_pedidos where codigo_atendimento =".$cod.";";
      $select = mysql_query($sql);

      if(mysql_num_rows($select)){

        $retorno = array();

        while($rs=mysql_fetch_array($select)){

          $objeto = array("nome_produto" => $rs["nome_produto"],
                          "preco" => $rs["preco"]);
            $retorno[] = $objeto;
        }

        echo json_encode($retorno);

      }else{

        $objeto = array("resultado" => "erro");
        echo json_encode($objeto);

      }
    }

    if(isset($_GET['modo'])=="valor_total"){

      $select = "select * from total_conta where codigo_atendimento = $cod;";
      $exec = mysql_query($select);

      $rs = mysql_fetch_array($exec);

      $objeto = array("valor_total" => $rs["total"]);

      echo json_encode($objeto);

    }

}
?>
