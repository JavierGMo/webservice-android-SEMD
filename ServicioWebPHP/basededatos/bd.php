<?php
    $hostname = 'localhost';
    $database = 'libros_p8';
    $username = 'root';
    $password = '';
    $conexion = new mysqli($hostname, $username, $password, $database);
    $errorBD = true;
    if($conexion->connect_errno){
        $errorBD = true;
    }else{
        $errorBD = false;
    }
?>