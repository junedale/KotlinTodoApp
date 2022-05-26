package util

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.Properties

object DatabaseUtil {
    private var connection: Connection? = null
    private var username = "root"
    private var password = ""

    fun connect() {
        val props = Properties()
        props["user"] = username
        props["passwrod"] = password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tododb", props)
        } catch (e: SQLException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }


    fun closeConnection() {
        connection?.close()!!
    }
}