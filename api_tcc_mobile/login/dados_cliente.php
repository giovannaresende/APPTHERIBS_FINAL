<?php

if($rs['foto'] == null){

  $objeto = array(
        "resultado" => "login_cliente_ok",
				"id_cliente" => $rs["id_cliente"],
				"nome_cliente" => $rs["nome_cliente"],
				"sobrenome_cliente" => $rs["sobrenome_cliente"],
        "nome_usuario" => $rs["nome_usuario"],
        "senha_usuario" => $rs["senha_usuario"],
        "foto" => "http://www.leadsndeals.com/images/bill.png");

	echo json_encode($objeto);

} else{

  $objeto = array(
        "resultado" => "login_cliente_ok",
				"id_cliente" => $rs["id_cliente"],
				"nome_cliente" => $rs["nome_cliente"],
				"sobrenome_cliente" => $rs["sobrenome_cliente"],
        "nome_usuario" => $rs["nome_usuario"],
        "senha_usuario" => $rs["senha_usuario"],
        "foto" => $rs["foto"]);

	echo json_encode($objeto);
}

?>
