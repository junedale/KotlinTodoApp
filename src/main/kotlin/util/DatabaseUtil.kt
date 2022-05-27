package util

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.Properties

object DatabaseUtil {
    private var connection: Connection? = null
    private var username = "root"
    private var password = ""

    // Function connect is a static method used to connect to the database
    @JvmStatic
    fun connect(): Connection? {
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
        return connection
    }

    // Function closeConnection terminates the connection to database
    @JvmStatic
    fun closeConnection() {
        connect()?.close()
    }
}