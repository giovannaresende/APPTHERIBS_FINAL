<?php

  require_once('../conexao_db.php');

  if(isset($_GET['num_mesa']) && $_GET['num_mesa']!=""){

    $num_mesa = $_GET['num_mesa'];

    $gerar_codigo = "select floor(rand()*(999-10)) as codigo;";

    $exec = mysql_fetch_array(mysql_query($gerar_codigo));

    $codigo = $exec['codigo'];

    $objeto = array("resultado" => $num_mesa.$codigo);
    echo json_encode($objeto);

  }else{

    $objeto = array("resultado" => "erro");
    echo json_encode($objeto);
  }

?>
