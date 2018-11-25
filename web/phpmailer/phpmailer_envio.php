<?php

require_once ("PHPMailerAutoload.php");


$mail = new PHPMailer();

$mail->IsSMTP();
$mail->SMTPOptions = array(
'ssl'=> array(
'verify_peer'=>false,
'verify_peer_name'=>false,
'allow_self_signed'=>true
)
);
//debug p/ inf erros
$mail->SMTPDebug=2;

//host
$mail->Host = "smtp.gmail.com";

//tipo de protecao
$mail->SMTPSecure = "tls";

//porta
$mail-> Port = 587;

//autenticacao
$mail->SMTPAuth = true;

//detalhes da conta de email
$mail->Username='anelize.souza.286@gmail.com';
$mail->Password='a@8z*g5k!';

//detalhes do email
$mail->setFrom ('anelize.souza.286@gmail.com','Site');
$mail->addAddress('anelize.souza.286@gmail.com');
$mail->Subject = 'Cartão Virtual';
$mail->Body = 'Meu Cartão Virtual. Próxima vacina para seu animal: 45 dias.';

//msg de envio ou erro
if($mail->Send())
echo "Dados enviados com sucesso!";
else 
    echo "Erro inesperado!";

?>