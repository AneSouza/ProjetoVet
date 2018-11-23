<!DOCTYPE html>
<html>
   <head>
       <meta charset="UTF-8">
       <title></title>
   </head>
   <body>
       <?PHP
       $emailEnviar = "12161003848@muz.ifsuldeminas.edu.br";
       $nome = htmlspecialchars($_POST['nome']);
       $telefone = htmlspecialchars($_POST['foneid']);
       $email = htmlspecialchars($_POST['emailid']);
       $assunto = htmlspecialchars($_POST['assunto']);
       $mensagem = htmlspecialchars($_POST['msg']);
       $message = "Nome: $nome <br/>Telefone: $telefone<br/>E-mail: $email<br/>Mensagem: $mensagem";
       $header = "MIME-Version: 1.0\n";
       $header .= "Content-type: text/html; charset=iso-8859-1\n";
       $header .= "From: $email\n";
       mail($emailEnviar, $assunto, $message, $header);
       header("Location: index.php");
       ?>
   </body>
</html>