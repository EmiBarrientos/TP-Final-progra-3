<?php
session_start();

$controllers = [
    
  
    
        
    
    'AuthController' => [
        'login' => [
            'description' => 'Autenticación - Login de usuario',
            'url' => '/auth/login',
            'method' => 'POST',
            'params' => ['username', 'password']
        ],
         'register' => [
            'description' => 'Registro de nuevo usuario',
        'url' => '/auth/register',
        'method' => 'POST',
        'params' => [
            'username',
            'password',
            'dni',
            'nombre',
            'apellido',
            'telefono',
            'email',
            'rol' 
        ]
        ]
    ],

   
    
    
    
    'UsuarioController' => [
        'getAllUsuarios' => [
            'description' => 'Obtiene todos los usuarios',
            'url' => '/api/usuarios',
            'method' => 'GET',
            'params' => []
        ],
        'getUsuarioById' => [
            'description' => 'Obtiene un usuario por su ID',
            'url' => '/api/usuarios/{id}',
            'method' => 'GET',
            'params' => ['id']
        ],


   'findByEmail' => [
    'description' => 'Buscar usuario por email',
    'url' => '/api/usuarios/email/{email}',
    'method' => 'GET',
    'params' => ['email']
],

'findByDni' => [
    'description' => 'Buscar usuario por DNI',
    'url' => '/api/usuarios/dni/{dni}',
    'method' => 'GET',
    'params' => ['dni']
],





        'createUsuario' => [
        'description' => 'Crea un nuevo usuario a partir de un DTO',
        'url' => '/api/usuarios',
            'method' => 'POST',
            'params' => ['dni', 'nombre', 'apellido', 'username', 'password', 'telefono', 'email', 'rol', 'calle', 'numero', 'ciudad', 'provincia','codigoPostal', 'pais']
        ],


        'updateUsuario' => [
    'description' => 'Actualiza un usuario existente',
    'url' => '/api/usuarios/{id}',
    'method' => 'PUT',
    'params' => [
        'id', 'dni', 'nombre', 'apellido', 'username', 'password', 'telefono', 'email',
        'rol', 'calle', 'numero', 'ciudad', 'provincia', 'codigoPostal', 'pais'
    ],
    'nullable' => true
],

        'deleteUsuario' => [
            'description' => 'Elimina un usuario por su ID',
            'url' => '/api/usuarios/{id}',
            'method' => 'DELETE',
            'params' => ['id']
        ]
    ],



    'EmpleadoController' => [
        'getAllEmpleados' => [
            'description' => 'Obtiene todos los empleados',
            'url' => '/api/empleados',
            'method' => 'GET',
            'params' => []
        ],
        'getEmpleadoById' => [
            'description' => 'Obtiene un empleado por su ID',
            'url' => '/api/empleados/{id}',
            'method' => 'GET',
            'params' => ['id']
        ],
        'createEmpleado' => [
            'description' => 'Crea un nuevo empleado',
            'url' => '/api/empleados',
            'method' => 'POST',
            'params' => ['usuarioId', 'horasTrabajadas', 'estado']
        ],
        'updateEmpleado' => [
            'description' => 'Actualiza un empleado existente',
            'url' => '/api/empleados/{id}',
            'method' => 'PUT',
            'params' => ['id', 'usuarioId', 'horasTrabajadas', 'estado']
        ],
        'deleteEmpleado' => [
            'description' => 'Elimina un empleado por su ID',
            'url' => '/api/empleados/{id}',
            'method' => 'DELETE',
            'params' => ['id']
        ]
    ],

    'PasajeroController' => [
        'getAllPasajeros' => [
            'description' => 'Obtiene todos los pasajeros',
            'url' => '/api/pasajeros',
            'method' => 'GET',
            'params' => []
        ],
        'getPasajeroById' => [
            'description' => 'Obtiene un pasajero por su ID',
            'url' => '/api/pasajeros/{id}',
            'method' => 'GET',
            'params' => ['id']
        ],



            'createPasajero' => [
        'description' => 'Crea un nuevo pasajero (requiere ID del usuario ya creado)',
        'url' => '/api/pasajeros',
        'method' => 'POST',
            'params' => ['usuarioId', 'estado']
        ],



        'updatePasajero' => [
            'description' => 'Actualiza un pasajero existente',
            'url' => '/api/pasajeros/{id}',
            'method' => 'PUT',
            'params' => ['id', 'usuarioId', 'estado']
        ],
        'deletePasajero' => [
            'description' => 'Elimina un pasajero por su ID',
            'url' => '/api/pasajeros/{id}',
            'method' => 'DELETE',
            'params' => ['id']
        ]
    ],

    'HabitacionController' => [
        'getAllHabitaciones' => [
            'description' => 'Obtiene todas las habitaciones',
            'url' => '/api/habitaciones',
            'method' => 'GET',
            'params' => []
        ],
        'getHabitacionById' => [
            'description' => 'Obtiene una habitación por su ID',
            'url' => '/api/habitaciones/{id}',
            'method' => 'GET',
            'params' => ['id']
        ],
        'getHabitacionByNumero' => [
            'description' => 'Obtiene una habitación por su número',
            'url' => '/api/habitaciones/numero/{numero}',
            'method' => 'GET',
            'params' => ['numero']
        ],
        'getHabitacionesByEstado' => [
            'description' => 'Obtiene habitaciones por estado',
            'url' => '/api/habitaciones/estado/{estado}',
            'method' => 'GET',
            'params' => ['estado']
        ],
        'getHabitacionesByTipo' => [
            'description' => 'Obtiene habitaciones por tipo',
            'url' => '/api/habitaciones/tipo/{tipo}',
            'method' => 'GET',
            'params' => ['tipo']
        ],
        'createHabitacion' => [
            'description' => 'Crea una nueva habitación',
            'url' => '/api/habitaciones',
            'method' => 'POST',
'params' => [
    'numeroHabitacion', 
    'capacidad', 
    'tipoHabitacion', 
    'estado',
    'wifi',
    'tvCable',
    'aireAcondicionado',
    'desayuno',
    'cajaFuerte',
    'pileta',
    'hidromasaje'
]
        ],
        'updateHabitacion' => [
            'description' => 'Actualiza una habitación existente',
            'url' => '/api/habitaciones/{id}',
            'method' => 'PUT',
            'params' => [
                'id',
    'numeroHabitacion', 
    'capacidad', 
    'tipoHabitacion', 
    'estado',
    'wifi',
    'tvCable',
    'aireAcondicionado',
    'desayuno',
    'cajaFuerte',
    'pileta',
    'hidromasaje'
            ]
        ],
        
        'deleteHabitacion' => [
            'description' => 'Elimina una habitación por su ID',
            'url' => '/api/habitaciones/{id}',
            'method' => 'DELETE',
            'params' => ['id']
        ]
    ],


    'CostoHabitacionController' => [
        'getAllCostosHabitacion' => [
            'description' => 'Obtiene todos los costos de habitación',
            'url' => '/api/costos-habitacion',
            'method' => 'GET',
            'params' => []
        ],
        'getCostoHabitacionById' => [
            'description' => 'Obtiene un costo de habitación por su ID',
            'url' => '/api/costos-habitacion/{id}',
            'method' => 'GET',
            'params' => ['id']
        ],
        'getCostoByTipoHabitacion' => [
            'description' => 'Obtiene costo por tipo de habitación',
            'url' => '/api/costos-habitacion/tipo/{tipo}',
            'method' => 'GET',
            'params' => ['tipo']
        ],
        'createCostoHabitacion' => [
            'description' => 'Crea un nuevo costo de habitación',
            'url' => '/api/costos-habitacion',
            'method' => 'POST',
            'params' => ['tipoHabitacion', 'costo']
        ],
        'updateCostoHabitacion' => [
            'description' => 'Actualiza un costo de habitación existente',
            'url' => '/api/costos-habitacion/{id}',
            'method' => 'PUT',
            'params' => ['id', 'tipoHabitacion', 'costo']
        ],
        'deleteCostoHabitacion' => [
            'description' => 'Elimina un costo de habitación por su ID',
            'url' => '/api/costos-habitacion/{id}',
            'method' => 'DELETE',
            'params' => ['id']
        ]
    ],
    'ServicioController' => [
        'getAllServicios' => [
            'description' => 'Obtiene todos los servicios',
            'url' => '/api/Costo_Servicio',
            'method' => 'GET',
            'params' => []
        ],
        'getServicioById' => [
            'description' => 'Obtiene un servicio por su ID',
            'url' => '/api/Costo_Servicio/{id}',
            'method' => 'GET',
            'params' => ['id']
        ],
        'getServicioByNombre' => [
            'description' => 'Obtiene un servicio por su nombre',
            'url' => '/api/Costo_Servicio/nombre/{nombre}',
            'method' => 'GET',
            'params' => ['nombre']
        ],
        'createServicio' => [
            'description' => 'Crea un nuevo servicio',
            'url' => '/api/Costo_Servicio',
            'method' => 'POST',
            'params' => ['nombre', 'costo']
        ],
        'updateServicio' => [
            'description' => 'Actualiza un servicio existente',
            'url' => '/api/Costo_Servicio/{id}',
            'method' => 'PUT',
            'params' => ['id', 'nombre', 'costo']
        ],
        'deleteServicio' => [
            'description' => 'Elimina un servicio por su ID',
            'url' => '/api/Costo_Servicio/{id}',
            'method' => 'DELETE',
            'params' => ['id']
        ]
    ],
    'ReservaController' => [
        'getAllReservas' => [
            'description' => 'Obtiene todas las reservas',
            'url' => '/api/reservas',
            'method' => 'GET',
            'params' => []
        ],
        'getReservaById' => [
            'description' => 'Obtiene una reserva por su ID',
            'url' => '/api/reservas/{id}',
            'method' => 'GET',
            'params' => ['id']
        ],
        'getReservasBetweenDates' => [
            'description' => 'Obtiene reservas entre fechas',
            'url' => '/api/reservas/fechas',
            'method' => 'GET',
            'params' => ['inicio', 'fin']
        ],
        'getReservasByPasajero' => [
            'description' => 'Obtiene reservas por pasajero',
            'url' => '/api/reservas/pasajero/{pasajeroId}',
            'method' => 'GET',
            'params' => ['pasajeroId']
        ],
        'getReservasByHabitacion' => [
            'description' => 'Obtiene reservas por habitación',
            'url' => '/api/reservas/habitacion/{habitacionId}',
            'method' => 'GET',
            'params' => ['habitacionId']
        ],
        'getReservasByEstado' => [
            'description' => 'Obtiene reservas por estado',
            'url' => '/api/reservas/estado/{estado}',
            'method' => 'GET',
            'params' => ['estado']
        ],
        'createReserva' => [
            'description' => 'Crea una nueva reserva',
            'url' => '/api/reservas',
            'method' => 'POST',
            'params' => [        'pasajeroId',
        'habitacionId',
        'fechaInicio',
        'fechaFin',
        'estado',
        'observaciones',
        'cantidadPasajeros',
        'masajes',
        'spa',
        'pileta',
        'cena',
        'almuerzo',
        'desayuno',
        'facial',
        'manicura',
        'lavanderia'
]
        ],
        'updateReserva' => [
            'description' => 'Actualiza una reserva existente',
            'url' => '/api/reservas/{id}',
            'method' => 'PUT',
            'params' => ['id',            'pasajeroId',
        'habitacionId',
        'fechaInicio',
        'fechaFin',
        'estado',
        'observaciones',
        'cantidadPasajeros',
        'masajes',
        'spa',
        'pileta',
        'cena',
        'almuerzo',
        'desayuno',
        'facial',
        'manicura',
        'lavanderia'
]
        ],
        

        'obtenerHabitacionesReservadas' => [
    'description' => 'Obtiene las IDs de habitaciones reservadas entre dos fechas',
    'url' => '/api/reservas/habitaciones-reservadas?inicio={inicio}&fin={fin}',
    'method' => 'GET',
    'params' => ['inicio', 'fin']
],

        'deleteReserva' => [
            'description' => 'Elimina una reserva por su ID',
            'url' => '/api/reservas/{id}',
            'method' => 'DELETE',
            'params' => ['id']
        ]
    
        ],



        'EmpleadoAccionReservaController' => [
    'getAllEmpleados' => [
        'description' => 'Obtiene todas las acciones de empleados sobre reservas',
        'url' => '/api/empleadoAccionReserva',
        'method' => 'GET',
        'params' => []
    ],
    'getEmpleadoById' => [
        'description' => 'Obtiene una acción de empleado por su ID',
        'url' => '/api/empleadoAccionReserva/{id}',
        'method' => 'GET',
        'params' => ['id']
    ],
    'createEmpleado' => [
        'description' => 'Crea una nueva acción de empleado sobre una reserva',
        'url' => '/api/empleadoAccionReserva',
        'method' => 'POST',
        'params' => ['estado', 'idEmpleado', 'idReserva']
    ],
    'updateEmpleado' => [
        'description' => 'Actualiza una acción de empleado sobre una reserva existente',
        'url' => '/api/empleadoAccionReserva/{id}',
        'method' => 'PUT',
        'params' => ['id', 'estado', 'idEmpleado', 'idReserva']
    ],
    'deleteEmpleado' => [
        'description' => 'Elimina una acción de empleado por su ID',
        'url' => '/api/empleadoAccionReserva/{id}',
        'method' => 'DELETE',
        'params' => ['id']
    ]
],




   'UtilController' => [
    'createPasajero' => [
        'description' => 'Crea un nuevo pasajero junto con su usuario',
        'url' => '/util/crear-pasajero',
        'method' => 'POST',
        'params' => ['dni', 'nombre', 'apellido', 'telefono', 'email', 'username', 'password', 'calle', 'numero', 'ciudad', 'provincia', 'codigoPostal', 'pais']
    ],
    'createEmpleado' => [
        'description' => 'Crea un nuevo empleado junto con su usuario',
        'url' => '/util/crear-empleado',
        'method' => 'POST',
        'params' => ['dni', 'nombre', 'apellido', 'telefono', 'email', 'username', 'password', 'calle', 'numero', 'ciudad', 'provincia', 'codigoPostal', 'pais']
    ],
    'obtenerHabitacionesDisponibles' => [
        'description' => 'Devuelve habitaciones disponibles entre dos fechas',
        'url' => '/util/disponibles',
        'method' => 'GET',
        'params' => ['fechaInicio', 'fechaFin']
    ],
    'obtenerHabitacionesDisponiblesPorTipoyFecha' => [
        'description' => 'Devuelve habitaciones disponibles entre dos fechas y de un tipo específico',
        'url' => '/util/disponiblesfiltro',
        'method' => 'GET',
        'params' => ['fechaInicio', 'fechaFin', 'tipo']
    ],
    'getCostoHabitacionByNumero' => [
        'description' => 'Calcula el costo total de una habitación por su número',
        'url' => '/util/costo/{numero}',
        'method' => 'GET',
        'params' => ['numero']
    ],
    'asignarEmpleadoAReserva' => [
    'description' => 'Asigna un empleado a una reserva y actualiza su estado',
    'url' => '/util/{reservaId}/asignar-empleado/{empleadoId}?accionEstado={accionEstado}',
    'method' => 'POST',
    'params' => ['reservaId', 'empleadoId', 'accionEstado']
],
    'asignarPasajeroAReserva' => [
        'description' => 'Asigna un pasajero a una reserva existente',
        'url' => '/util/{reservaId}/asignar-pasajero/{pasajeroId}',
        'method' => 'POST',
        'params' => ['reservaId', 'pasajeroId']
    ]
    ],

'ServAdicionalController' => [
    'listarTodos' => [
        'description' => 'Obtiene todos los servicios adicionales',
        'url' => '/api/ServAdicional',
        'method' => 'GET',
        'params' => []
    ],
    'obtenerPorId' => [
        'description' => 'Obtiene un servicio adicional por su ID',
        'url' => '/api/ServAdicional/{id}',
        'method' => 'GET',
        'params' => ['id']
    ],
    'crear' => [
        'description' => 'Crea un nuevo servicio adicional',
        'url' => '/api/ServAdicional',
        'method' => 'POST',
        'params' => ['nombre', 'descripcion']
    ],
    'actualizar' => [
        'description' => 'Actualiza un servicio adicional existente',
        'url' => '/api/ServAdicional/{id}',
        'method' => 'PUT',
        'params' => ['id', 'nombre', 'descripcion']
    ],
    'eliminar' => [
        'description' => 'Elimina un servicio adicional por su ID',
        'url' => '/api/ServAdicional/{id}',
        'method' => 'DELETE',
        'params' => ['id']
    ]
    ],
    'CostoServAdicionalController' => [
    'listarTodos' => [
        'description' => 'Lista todos los costos de servicios adicionales',
        'url' => '/api/costo-serv-adicionales',
        'method' => 'GET',
        'params' => []
    ],
    'obtenerPorId' => [
        'description' => 'Obtiene un costo adicional por su ID',
        'url' => '/api/costo-serv-adicionales/{id}',
        'method' => 'GET',
        'params' => ['id']
    ],
    'crear' => [
        'description' => 'Crea un nuevo costo de servicio adicional',
        'url' => '/api/costo-serv-adicionales',
        'method' => 'POST',
        'params' => ['id (opcional)', 'nombre', 'precio']
    ],
    'actualizar' => [
        'description' => 'Actualiza un costo de servicio adicional existente',
        'url' => '/api/costo-serv-adicionales/{id}',
        'method' => 'PUT',
        'params' => ['id', 'nombre', 'precio']
    ],
    'eliminar' => [
        'description' => 'Elimina un costo de servicio adicional por ID',
        'url' => '/api/costo-serv-adicionales/{id}',
        'method' => 'DELETE',
        'params' => ['id']
    ]
    ],

    'FacturaController' => [
    'previsualizarFactura' => [
        'description' => 'Genera una factura en memoria sin guardarla',
        'url' => '/api/facturas/previsualizar/{reservaId}',
        'method' => 'GET',
        'params' => ['reservaId']
    ],
    'generarFactura' => [
        'description' => 'Genera y guarda una factura en base de datos',
        'url' => '/api/facturas/generar/{reservaId}',
        'method' => 'POST',
        'params' => ['reservaId']
    ]
]

    // ... (todos tus demás controladores y métodos permanecen igual)
    // Mantén todo el array $controllers como lo tenías originalmente
];

// Manejo del logout
if (isset($_POST['logout'])) {
    unset($_SESSION['jwt_token']);
    session_destroy();
    header("Location: ".$_SERVER['PHP_SELF']);
    exit();
}

// Variables
$selectedController = $_POST['controller'] ?? null;
$selectedMethod = $_POST['method'] ?? null;
$response = null;
$requestData = null;

// Distinguir entre actualización y ejecución
$isUpdateRequest = isset($_POST['update']);
$isExecuteRequest = isset($_POST['execute']);

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if ($isUpdateRequest) {
        // Solo actualizar las selecciones sin hacer petición
        $selectedController = $_POST['controller'] ?? null;
        $selectedMethod = $_POST['method'] ?? null;
        
        // Preservar los parámetros existentes
        $preservedParams = [];
        if ($selectedController && $selectedMethod) {
            foreach ($controllers[$selectedController][$selectedMethod]['params'] as $param) {
                if (isset($_POST[$param])) {
                    $preservedParams[$param] = $_POST[$param];
                }
            }
        }
        $_POST = array_merge($_POST, $preservedParams);
    } elseif ($isExecuteRequest) {
        // Lógica de ejecución de petición
        $baseUrl = 'http://localhost:8080';
        $urlTemplate = $controllers[$selectedController][$selectedMethod]['url'];
        $url = $baseUrl . $urlTemplate;

        $params = [];
        foreach ($controllers[$selectedController][$selectedMethod]['params'] as $param) {
            if (isset($_POST[$param])) {
                $value = trim($_POST[$param]);
                $params[$param] = $value === '' ? null : $value;
            }
        }

        $method = $controllers[$selectedController][$selectedMethod]['method'];

        // Reemplazar {param} en la URL
        foreach ($params as $param => $value) {
            if (strpos($url, '{' . $param . '}') !== false) {
                $url = str_replace('{' . $param . '}', $value, $url);
                unset($params[$param]);
            }
        }

        $ch = curl_init();

        if ($method === 'GET' && !empty($params)) {
            $url .= '?' . http_build_query($params);
        }

        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

        $headers = ['Content-Type: application/json'];

        if ($method === 'POST' || $method === 'PUT') {
            $jsonData = json_encode($params);
            $requestData = $params;
            curl_setopt($ch, CURLOPT_CUSTOMREQUEST, $method);
            curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonData);
            $headers[] = 'Content-Length: ' . strlen($jsonData);
        } elseif ($method === 'DELETE') {
            curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'DELETE');
        }

        // Agregar header Authorization si no es login
        if (isset($_SESSION['jwt_token']) && !($selectedController === 'AuthController' && $selectedMethod === 'login')) {
            $headers[] = 'Authorization: Bearer ' . $_SESSION['jwt_token'];
        }

        curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

        $rawResponse = curl_exec($ch);
        $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
        curl_close($ch);

        $decodedResponse = json_decode($rawResponse, true);

        // Guardar token JWT después de login
        if ($selectedController === 'AuthController' && $selectedMethod === 'login' && isset($decodedResponse['token'])) {
            $_SESSION['jwt_token'] = $decodedResponse['token'];
        }

        $response = [
            'status' => $httpCode,
            'response' => $decodedResponse,
            'request' => $requestData ?? null,
            'json_sent' => $jsonData ?? null,
            'url' => $url,
            'method' => $method
        ];
    }
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Probador de API Hotel</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 800px; margin: 0 auto; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        select, input { width: 100%; padding: 8px; box-sizing: border-box; }
        button { padding: 10px 15px; background-color: #4CAF50; color: white; border: none; cursor: pointer; }
        button:hover { background-color: #45a049; }
        .response { margin-top: 20px; padding: 15px; border: 1px solid #ddd; background-color: #f9f9f9; }
        .request-info { margin-top: 20px; padding: 15px; border: 1px solid #ddd; background-color: #f0f7ff; }
        .status-ok { color: green; font-weight: bold; }
        .status-fail { color: red; font-weight: bold; }
        .button-group { display: flex; gap: 10px; margin-top: 15px; }
    </style>
</head>
<body>
<div class="container">
    <h1>Probador de API Hotel</h1>

    <?php if (isset($_SESSION['jwt_token'])): ?>
        <div class="auth-info">
            <p class="status-ok">🟢 Autenticado con JWT</p>
            <?php 
            // Decodificar el token JWT para mostrar información
            $tokenParts = explode('.', $_SESSION['jwt_token']);
            if (count($tokenParts) === 3) {
                $payload = base64_decode($tokenParts[1]);
                $userData = json_decode($payload, true);
                if ($userData && isset($userData['username'])) {
                    echo '<p>Usuario: ' . htmlspecialchars($userData['username']) . '</p>';
                    if (isset($userData['exp'])) {
                        $expiryDate = date('Y-m-d H:i:s', $userData['exp']);
                        echo '<p>Token expira: ' . $expiryDate . '</p>';
                    }
                }
            }
            ?>
            <form method="post">
                <input type="hidden" name="logout" value="1">
                <button type="submit">Cerrar sesión</button>
            </form>
        </div>
    <?php else: ?>
        <p class="status-fail">🔴 No autenticado</p>
        <p>Debes iniciar sesión primero usando AuthController -> login</p>
    <?php endif; ?>

    <form method="post">
        <div class="form-group">
            <label for="controller">Controlador:</label>
            <select id="controller" name="controller">
                <option value="">Seleccione un controlador</option>
                <?php foreach ($controllers as $controllerName => $methods): ?>
                    <option value="<?= $controllerName ?>" <?= $selectedController === $controllerName ? 'selected' : '' ?>>
                        <?= $controllerName ?>
                    </option>
                <?php endforeach; ?>
            </select>
        </div>

        <?php if ($selectedController): ?>
            <div class="form-group">
                <label for="method">Método:</label>
                <select id="method" name="method">
                    <option value="">Seleccione un método</option>
                    <?php foreach ($controllers[$selectedController] as $methodName => $methodInfo): ?>
                        <option value="<?= $methodName ?>" <?= $selectedMethod === $methodName ? 'selected' : '' ?>>
                            <?= $methodName ?> - <?= $methodInfo['description'] ?>
                        </option>
                    <?php endforeach; ?>
                </select>
            </div>
        <?php endif; ?>

        <div class="button-group">
            <button type="submit" name="update">Actualizar parámetros</button>
        </div>

        <?php if ($selectedController && $selectedMethod): ?>
            <div class="param-fields">
                <h3>Parámetros:</h3>
                <?php foreach ($controllers[$selectedController][$selectedMethod]['params'] as $param): ?>
                    <div class="form-group">
                        <label for="<?= $param ?>"><?= $param ?>:</label>
                        <input type="text" id="<?= $param ?>" name="<?= $param ?>" 
                               value="<?= htmlspecialchars($_POST[$param] ?? '') ?>">
                    </div>
                <?php endforeach; ?>
                <button type="submit" name="execute">Ejecutar petición</button>
            </div>
        <?php endif; ?>
    </form>

    <?php if ($response): ?>
        <div class="request-info">
            <h3>Información de la solicitud:</h3>
            <p><strong>URL:</strong> <?= htmlspecialchars($response['url']) ?></p>
            <p><strong>Método:</strong> <?= htmlspecialchars($response['method']) ?></p>
            <?php if ($response['request']): ?>
                <p><strong>Datos enviados:</strong></p>
                <pre><?= htmlspecialchars(json_encode($response['request'], JSON_PRETTY_PRINT)) ?></pre>
            <?php endif; ?>
            <?php if ($response['json_sent']): ?>
                <p><strong>JSON enviado:</strong></p>
                <pre><?= htmlspecialchars($response['json_sent']) ?></pre>
            <?php endif; ?>
        </div>

        <div class="response">
            <h3>Respuesta:</h3>
            <p><strong>Estado HTTP:</strong> <?= $response['status'] ?></p>
            <pre><?= htmlspecialchars(json_encode($response['response'], JSON_PRETTY_PRINT)) ?></pre>
        </div>
    <?php endif; ?>
</div>
</body>
</html>