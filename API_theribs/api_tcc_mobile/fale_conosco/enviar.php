<?php

  require_once('../conexao_db.php');

  if($erro_conexao == true){

    echo("Falha_com_db!");

  }else{

    if(isset($_POST['modo'])=="enviar"){
		
		$nome = $_POST['nome'];
		$telefone = $_POST['telefone'];
		$email = $_POST['email'];
		$comentario = $_POST['comentario'];
		$classificacao = 1;
		
		$valores = "'".$nome."','".$telefone."','".$email."',".$classificacao.",'".$comentario."'";
		$insert = "insert into tbl_entre_contato(nome, telefone, email, id_classificacao, comentario) values(".$valores.");";
		
		if(mysql_query($insert)){
			echo("sucesso");
		}else{
			echo("falha");
		}

    }else{
      echo("ACESSO NEGADO!");
    }

  }


?>
