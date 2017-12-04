<?php
	require_once('../conexao_db.php');

	$sql = "select * from tbl_produto";

	$select = mysql_query($sql);

	$array = array();

	while($rs=mysql_fetch_array($select)){
		$objeto = array(
			 "id_prato" => $rs["id_produto"],
			 "tipo_produto" => $rs["id_tipo_produto"],
			 "nome_prato" => $rs["nome_produto"],
			 "foto_prato" => $rs["foto_produto"],
			 "preco_prato" => $rs["preco"],
             "descricao_prato" => $rs["descricao_produto"]
		);
				$array[] = $objeto;
	}


	$json = json_encode($array);
	echo($json);

?>
