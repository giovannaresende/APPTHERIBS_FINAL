<?php
  require_once('../conexao_db.php');

  $sql = "SELECT * FROM selecionar_endereco_unidade;";

	$select = mysql_query($sql);

	$array = array();

	while($rs=mysql_fetch_array($select)){
		$objeto = array(
                 "id_unidade" => $rs["id_unidade"],
			    "nome_unidade" => $rs["nome_unidade"],
				"img_perfil" => $rs["img_perfil"],
				"logradouro" => $rs["logradouro"],
					"numero" => $rs["numero"]
		);

		$array[] = $objeto;
	}
	$json = json_encode($array);
	echo($json);
?>
