<?php




$GetPost = filter_input_array(INPUT_POST,FILTER_DEFAULT);

var_dump($GetPost);



$Erro = true;

$Nome = $GetPost['name'];

$Email = $GetPost['email'];


var_dump($Nome);

include_once '/PHPMailer/class.SMTP.php';
include_once '/PHPMailer/class.PHPMAILER.php';


$Mailer = new PHPMailer;


$Mailer->CharSet = "utf8";

$Mailer->SMTPDebug = 3;

$Mailer->IsSMTP();

$Mailer->Host = "serverl.rapidcloud.com.br";

$Mailer->SMTPAuth = true;

$Mailer->Username = "12161003848@muz.ifsuldeminas.edu.br";

$Mailer->Password = "123";

$Mailer->SMTPSecure = "tls";

$Mailer->Port = 587;

$Mailer->FromName = "($Nome)";

$Mailer->From = "12161003848@muz.ifsuldeminas.edu.br";

$Mailer->AddAddress ("12161003848@muz.ifsuldeminas.edu.br");

$Mailer->IsHTML(true);
$Mailer->Subject = "Novo cartão de Vacina - ($Nome)".date("H:i")."-".date("d/m/Y");

$Mailer->Body = "E-mail enviado por ($Nome)";



if($Mailer->Send()){
    
$Erro = false;

}
var_dump($Erro);