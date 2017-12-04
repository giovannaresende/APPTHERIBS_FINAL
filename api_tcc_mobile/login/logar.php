<?php

  require_once('../conexao_db.php');

  if($erro_conexao == true){

    echo("Falha_com_db!");

  }else{

    if(isset($_POST['usuario']) && isset($_POST['senha'])){

      $usuario = $_POST['usuario'];
      $senha = $_POST['senha'];

      $selecionar_cliente = "select * from logar_cliente where nome_usuario = '$usuario' and senha_usuario = '$senha';";

      $selecionar_garcom = "select * from logar_garcom where nome_usuario = '$usuario' and senha_usuario = '$senha';";
      
      if($rs = mysql_fetch_array(mysql_query($selecionar_cliente))){

        include('dados_cliente.php');

      }else if($rs = mysql_fetch_array(mysql_query($selecionar_garcom))){

        include('dados_garcom.php');

      }else{

        $objeto = array("resultado" => "usuario_nao_encontrado");

        echo json_encode($objeto);

      }

    }else{

      $objeto = array("resultado" => "acesso_negado");

      echo json_encode($objeto);
    }

  }


?>
