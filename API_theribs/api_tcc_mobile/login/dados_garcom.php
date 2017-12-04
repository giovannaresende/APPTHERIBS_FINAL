<?php

if($rs['foto'] == null){

  $objeto = array(
        "resultado" => "login_garcom_ok",
        "id_funcionario" => $rs["id_funcionario"],
        "id_unidade" => $rs["id_unidade"],
        "id_endereco" => $rs["id_endereco"],
        "nome_funcionario" => $rs["nome_funcionario"],
        "sobrenome_funcionario" => $rs["sobrenome_funcionario"],
        "dt_nasc" => $rs["dt_nasc"],
        "nome_usuario" => $rs["nome_usuario"],
        "senha_usuario" => $rs["senha_usuario"],
        "media_avaliacao" => $rs["media_avaliacao"],
        "foto" => "http://www.leadsndeals.com/images/bill.png");

  echo json_encode($objeto);
  
} else{

  $objeto = array(
        "resultado" => "login_garcom_ok",
        "id_funcionario" => $rs["id_funcionario"],
        "id_unidade" => $rs["id_unidade"],
        "id_endereco" => $rs["id_endereco"],
        "nome_funcionario" => $rs["nome_funcionario"],
        "cpf" => $rs["cpf"],
        "nome_usuario" => $rs["nome_usuario"],
        "sobrenome_funcionario" => $rs["sobrenome_funcionario"],
        "dt_nasc" => $rs["dt_nasc"],
        "nome_usuario" => $rs["nome_usuario"],
        "senha_usuario" => $rs["senha_usuario"],
        "media_avaliacao" => $rs["media_avaliacao"],
        "foto" => $rs["foto"]);

  echo json_encode($objeto);
}

?>
