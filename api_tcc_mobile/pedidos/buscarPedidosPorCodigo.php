<?php
  require_once('../conexao_db.php');
  
  if(isset($_GET['cod'])){
    $cod = $_GET['cod'];
    $sql = "select * from tbl_pedido where status_pedido = 1 and codigo_atendimento=".$cod;
    $select = mysql_query($sql);
    $array = array();
	while($rs=mysql_fetch_array($select)){
		$objeto = array(
		 "resultado" => "ok",
         "id_pedido" => $rs["id_pedido"],
        "status_pedido" => $rs["status_pedido"],
        "codigo_atendimento" => $rs["codigo_atendimento"],
        "data_pedido" => $rs["data_pedido"],
        "id_mesa" => $rs["id_mesa"]
    );  
      $array[] = $objeto;
  }
    $json = json_encode($array);
	echo($json);
}
?>
