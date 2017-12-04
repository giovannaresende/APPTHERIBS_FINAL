<?php

  require_once('../conexao_db.php');

  if(isset($_POST['modo'])){

    $modo = $_POST['modo'];

    switch ($modo) {
      case 'iniciar_pedido':

        if(isset($_POST['codigo'])){

          $codigo = $_POST['codigo'];
          $id_mesa = $_POST['id_mesa'];

          $insert = "insert into tbl_pedido(id_mesa, data_pedido, status_pedido, valor_total, codigo_atendimento) values($id_mesa, now(), 1, '0', '$codigo');";

          if(mysql_query($insert)){
            $resultado = array("resultado" => "iniciado");
          }else{
            $resultado = array("resultado" => "erro");
          }

          echo json_encode($resultado);

        }

      break;

    }

  }else if(isset($_GET['modo'])){

    $modo = $_GET['modo'];

    switch ($modo) {

      case 'pedidos_prontos':

        if(isset($_GET['id_funcionario'])){

            $id_funcionario = $_GET['id_funcionario'];

            $select = "select * from selecionar_pedidos_prontos where id_funcionario = $id_funcionario;";

            $exec = mysql_query($select);

            if(mysql_num_rows($exec) > 0){

              $retorno= array();
              while($preencher = mysql_fetch_array($exec)){

                $objeto = array("resultado" => "ok",
                                "nome_produto" => $preencher['nome_produto'],
                                "nome_mesa" => $preencher['nome_mesa']);

                $retorno[] = $objeto;
              }
              echo json_encode($retorno);

            }else{
              $objeto = array("resultado" => "sem_pratos_prontos");
              echo json_encode($objeto);
            }

        }
      break;

    }
  }

?>
