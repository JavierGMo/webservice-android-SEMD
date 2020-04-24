<?php
    include '../basededatos/bd.php';
    print_r($_POST);
    if(!empty($_POST)){
        $codigo = isset($_POST['codigo'])?$_POST['codigo']:'null';
        $titulo = isset($_POST['titulo'])?$_POST['titulo']:'null';
        $autor = isset($_POST['autor'])?$_POST['autor']:'null';
        $editorial = isset($_POST['editorial'])?$_POST['editorial']:'null';
        $precio = isset($_POST['precio'])?$_POST['precio']:'null';
        echo 'Codigo: '.$codigo.'<br>';
        var_dump($_POST);
        $consulta = "UPDATE libros SET codigo='$codigo', titulo='$titulo', autor='$autor', editorial='$editorial', precio='$precio' WHERE codigo='$codigo';";
        mysqli_query($conexion, $consulta) or die(mysqli_error($conexion));
    }
    mysqli_close($conexion);

?>