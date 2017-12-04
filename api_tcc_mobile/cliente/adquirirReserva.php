<?php

require_once('../conexao_db.php');

if(isset($_GET['id_unidade'])){

  $controller_reservas = new ControllerReserva();
  $controller_reservas->SolicitarReserva();
}

class ControllerReserva{

  public function SolicitarReserva(){

    //Resgatando valores via POST
    $id_unidade=$_GET['id_unidade'];
    $id_periodo=$_GET['id_periodo'];
    $id_cliente=$_GET['id_cliente'];
    $qtd_pessoas=$_GET['txt_qtd_pessoas'];
    $data_agendada=$_GET['dt_reserva'];
    //Montando e instanciando a Reserva
    $reserva_novo = new Reserva();
    $reserva_novo->id_unidade=$id_unidade;
    $reserva_novo->id_periodo=$id_periodo;
    $reserva_novo->id_cliente=$id_cliente;
    $reserva_novo->qtd_pessoas=$qtd_pessoas;
    $reserva_novo->id_status_reserva = 1;
    $reserva_novo->data_agendada=$data_agendada;
    $reserva_novo->id_nota=0;
    $verificaMesa = $reserva_novo->VerificarReserva($reserva_novo);
    if($verificaMesa != null){
      $id_mesa = $verificaMesa;
      $reserva_novo->id_mesa=$id_mesa;
      $reserva_novo->InsertReserva($reserva_novo);
    }elseif($verificaMesa == false){

      $objeto = array("resultado" => "mesas_indisponiveis");
      echo json_encode($objeto);
    }
  }

}

class Mesa{

  public $id_mesa;
  public $data_agendada;
  public $id_unidade;
  public $nome_unidade;
}

class ReservaDetalhes extends Mesa{

    public $periodo;
    public $nome_status;
    public $cliente;
    public $contato;
    public $mesa;
    public $pessoas;

}


class Reserva extends ReservaDetalhes{

        public $id_reserva;
        public $id_periodo;
        public $id_cliente;
        public $qtd_pessoas;
        public $data_requisicao;
        public $id_status_reserva;
        public $id_tipo_comentario;

      public function VerificarReserva($dados_reserva){

          $sql = "select id_mesa, nome_mesa from tbl_mesa where id_mesa not in (
                  	select id_mesa
                      from tbl_mesa_reserva as mr
                      inner join tbl_reserva as r
                      on r.id_reserva = mr.id_reserva
                      inner join tbl_periodo as p
                      on p.id_periodo = r.id_periodo
                    where dt_agendada = '$dados_reserva->data_agendada'
                      and r.id_periodo = $dados_reserva->id_periodo)
                  and id_unidade = $dados_reserva->id_unidade
                  and qtd_cadeiras >= $dados_reserva->qtd_pessoas;";

          $select = mysql_query($sql);

          if($rs = mysql_fetch_array($select)){
              $id_mesa = $rs['id_mesa'];
              return $id_mesa;
          }else{
              return false;
          }
      }

      public function InsertReserva($dados_reserva){

          $sql = "select now() as data_requisicao;";
          $select = mysql_query($sql);

          if($rs=mysql_fetch_array($select)){

              $data_requisicao = $rs['data_requisicao'];

              $valores = (" $dados_reserva->id_periodo,
                            $dados_reserva->id_cliente,
                            $dados_reserva->qtd_pessoas,
                            '$data_requisicao',
                            $dados_reserva->id_status_reserva,
                            $dados_reserva->id_nota");

              $sql = "insert into tbl_reserva(id_periodo,
                                              id_cliente,
                                              qtd_pessoas,
                                              data_requisicao,
                                              id_status_reserva,
                                              id_nota)
                      values ($valores);";

              if(mysql_query($sql)){

                $sql = "select last_insert_id() as id_reserva;";
                $select = mysql_query($sql);

                if($rs = mysql_fetch_array($select)){

                    $id_reserva = $rs['id_reserva'];

                    $valores = (" $dados_reserva->id_mesa,
                                  $id_reserva,
                                  '$dados_reserva->data_agendada'");

                    $sql = "insert into tbl_mesa_reserva(id_mesa,
                                                        id_reserva,
                                                        dt_agendada)
                            values($valores);";
                            
                    if(mysql_query($sql)){
                      $objeto = array("resultado" => "sucesso");
                      echo json_encode($objeto);
                    }else{
                      $objeto = array("resultado" => "erro");
                      echo json_encode($objeto);
                    }
                }
              }
          }

      }

    }

?>
