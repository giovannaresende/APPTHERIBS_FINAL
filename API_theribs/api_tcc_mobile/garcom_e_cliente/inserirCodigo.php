<?php
  require_once('../conexao_db.php');
  if(isset($_GET['cod'])){

    $cod = $_GET['cod'];
    $sql = "select * from tbl_pedido where status_pedido = 1 and codigo_atendimento=".$cod.";";
    $select = mysql_query($sql);

    if(mysql_num_rows($select)){

      while($rs=mysql_fetch_array($select)){

        $objeto = array("codigo_atendimento" => $rs["codigo_atendimento"]);
      }

    	echo json_encode($objeto);

    }else{

      $objeto = array("resultado" => "erro");
      echo json_encode($objeto);

    }


}
?>
