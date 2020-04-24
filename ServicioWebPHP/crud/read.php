<?php
    include '../basededatos/bd.php';
    
    $consulta = "SELECT * FROM libros;";
    $resultado = mysqli_query($conexion, $consulta);
    $json = array();
    if(!$resultado){
        $json['libros'][] = array(
            'error' => 'consulta fallida'
        );
        die("Error: ".mysqli_error($conexion));
    }else{
        while($valores = mysqli_fetch_array($resultado)){
            $json['libros'][] = array(
                'codigo' => $valores['codigo'],
                'titulo' => $valores['titulo'],
                'autor' => $valores['autor'],
                'editorial' => $valores['editorial'],
                'precio' => $valores['precio']
            );
        }   
    }
    mysqli_close($conexion);
    $json = json_encode($json);
    echo $json;

?>