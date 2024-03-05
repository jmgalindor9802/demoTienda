package com.example.demotienda

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConsultasMySQL : AppCompatActivity() {

    private lateinit var edServidor: EditText
    private lateinit var edPuerto: EditText
    private lateinit var edUsuario: EditText
    private lateinit var edPassword: EditText
    private val baseDatos = "Tienda"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultas_my_sql)

        edServidor = findViewById(R.id.edServidor)
        edPuerto = findViewById(R.id.edPuerto)
        edUsuario = findViewById(R.id.edUsuario)
        edPassword = findViewById(R.id.edPassword)

        // Llamamos al método para conectar cuando sea necesario, por ejemplo, al presionar un botón
        // Puedes llamar a este método en respuesta a algún evento del usuario, como un clic en un botón.
        conectarMySQL()
    }

    private fun conectarMySQL() {
        try {
            // Obtenemos los valores de los EditText
            var estadoConexion= false;
            val servidor = edServidor.text.toString()
            val puerto = edPuerto.text.toString()
            val usuario = edUsuario.text.toString()
            val password = edPassword.text.toString()

            // Cargamos el driver del conector JDBC
            Class.forName("com.mysql.jdbc.Driver").newInstance()

            // Construimos la URL de conexión con los datos ingresados por el usuario
            val urlMySQL = "jdbc:mysql://$servidor:$puerto/"

            // Establecemos la conexión con el Servidor MySQL indicándole como parámetros la URL construida,
            // la Base de Datos a la que vamos a conectarnos, y el usuario y contraseña de acceso al servidor
            val conexionMySQL: Connection = DriverManager.getConnection("$urlMySQL$baseDatos", usuario, password)

            // Comprobamos que la conexión se ha establecido
            if (!conexionMySQL.isClosed) {
                // Aquí puedes hacer algo con la conexión exitosa
                estadoConexion = true
                Toast.makeText(this, "Conexión Establecida", Toast.LENGTH_LONG).show()
            }
        } catch (ex: Exception) {
            // Manejamos las excepciones
            Toast.makeText(this, "Error al comprobar las credenciales: ${ex.message}", Toast.LENGTH_LONG).show()
        }
    }
}
