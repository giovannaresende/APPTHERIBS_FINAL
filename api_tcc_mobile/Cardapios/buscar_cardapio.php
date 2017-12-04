<?php
	require_once('../conexao_db.php');
	class Cardapio{
		public $id_cardapio;
		public $nome_cardapio;
		public $pratos;
	}
	class Pratos{
		public $id_prato;
		public $nome_prato;
		public $preco_prato;
		public $foto_prato;
        public $descricao_prato;
	}


	$lst = array();

	if(isset($_GET['id_filial'])){

		$id_filial = $_GET['id_filial'];

		$sql_filial="select * from tbl_cardapio where id_unidade=".$id_filial;
		$slt = mysql_query($sql_filial);

		while($rs_cardapio = mysql_fetch_array($slt)){

			$cardapio = new Cardapio();
			$cardapio->id_cardapio= $rs_cardapio["id_cardapio"];
			$cardapio->nome_cardapio=utf8_encode($rs_cardapio["nome_cardapio"]);
			$cardapio->pratos = array();

			$sql_pratos="
			select produto.id_produto, produto.nome_produto, produto.preco, produto.descricao_produto,
			intermediaria.id_cardapio, cardapio.nome_cardapio, tipo.nome_tipo_produto,
			produto.foto_produto
			from tbl_produto as produto
			inner join tbl_cardapio_produto as intermediaria
			on produto.id_produto = intermediaria.id_produto
			inner join tbl_cardapio as cardapio
			on cardapio.id_cardapio = intermediaria.id_cardapio
			inner join tbl_tipo_produto as tipo
		  on produto.id_tipo_produto = tipo.id_tipo_produto
			where cardapio.id_cardapio=".$cardapio->id_cardapio;


			$slt_pratos = mysql_query($sql_pratos);

			while($rs_prato = mysql_fetch_array($slt_pratos)){

				$prato = new Pratos;
				$prato->id_prato=$rs_prato['id_produto'];
				$prato->nome_prato=utf8_encode($rs_prato['nome_produto']);
				$prato->preco_prato=$rs_prato['preco'];
				$prato->foto_prato=$rs_prato['foto_produto'];
				$prato->tipo_produto=$rs_prato['nome_tipo_produto'];
				$prato->descricao_prato=$rs_prato['descricao_produto'];

				$cardapio->pratos[] = $prato;


			}
			$lst[] = $cardapio;
		}
		echo json_encode($lst);
	}
?>
