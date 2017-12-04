<?php

  require_once('../conexao_db.php');

  if(isset($_GET['id_funcionario'])){ // If para verificar se o usuário está tentando usar o script fora do APP

    $id_funcionario = $_GET['id_funcionario'];

    $select = "select * from selecionar_avaliacoes where id_funcionario = $id_funcionario order by dt_avaliacao asc limit 3;";

    $exec = mysql_query($select);

    if(mysql_num_rows($exec) > 0){ // If para verificar se o banco retornou algo

      $retorno = array();
      while($preencher = mysql_fetch_array($exec)){ // Estrutura de repetição para preencher o array

          $objeto = array("nome_cliente" => $preencher['nome_cliente'],
                          "valor_nota_garcom" => $preencher['valor_nota_garcom']);

          $retorno[] = $objeto;
      }

      echo json_encode($retorno);
    }else{
      $objeto = array("resultado" => "sem_avaliacoes");
      echo json_encode($objeto);
    }

  }else{

    $objeto = array("resultado" => "acesso_negado");
    echo json_encode($objeto);
  }

?>
