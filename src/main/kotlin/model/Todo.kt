package model

import model.base.BaseModel
import util.DatabaseUtil

object Todo: BaseModel {
    private val find: MutableList<String> = mutableListOf()

    // Function find obtains a row in the database that corresponds to the given id
    override fun find(id: String): List<String> {
        find.clear()
        val statement = DatabaseUtil.connect()?.prepareStatement("SELECT id, title, start_date, due_date, progress FROM main_todo WHERE id = ?")
        statement?.setString(1, id)
        val resultSet = statement?.executeQuery()

        while(resultSet?.next() == true) {
            find.add(resultSet.getString(1))
            find.add(resultSet.getString(2))
            find.add(resultSet.getString(3))
            find.add(resultSet.getString(4))
            find.add(resultSet.getString(5))
        }
        resultSet?.close()
        statement?.close()
        return find
    }

    // Function find obtains all rows in the database
    override fun findAll(id: String): List<List<String>> {
        val output = ArrayList<String>()
        output.clear()
        val statement = DatabaseUtil.connect()?.prepareStatement("SELECT id, title, start_date, due_date, progress FROM main_todo")
        val resultSet = statement?.executeQuery()

        while(resultSet?.next() == true) {
            output.add(resultSet.getString(1))
            output.add(resultSet.getString(2))
            output.add(resultSet.getString(3))
            output.add(resultSet.getString(4))
            output.add(resultSet.getString(5))
        }
        resultSet?.close()
        statement?.close()
        return output.chunked(5)
    }

    // Function insert inserts data to a table in the database
    override fun insert(id: String, title: String, startDate: String, dueDate: String) {
        val statement = DatabaseUtil.connect()?.prepareStatement("INSERT INTO main_todo (title, start_date, due_date, progress) VALUES (?, ?, ?, ?)")
        statement?.setString(1, title)
        statement?.setString(2, startDate)
        statement?.setString(3, dueDate)
        statement?.setString(4, "0")
        statement?.executeUpdate()
    }

    // Function delete removes a row from a table
    override fun delete(id: String) {
        val statement1 = DatabaseUtil.connect()?.prepareStatement("DELETE FROM sub_todo WHERE main_id = ?")
        val statement2 = DatabaseUtil.connect()?.prepareStatement("DELETE FROM main_todo WHERE id = ?")
        statement1?.setString(1, id)
        statement2?.setString(1, id)
        statement1?.executeUpdate()
        statement2?.executeUpdate()
        statement1?.close()
        statement2?.close()
    }

    // Function updateField updates a single field in a row
    override fun updateField(id: String, field: String, value: String) {
        val statement = DatabaseUtil.connect()?.prepareStatement("UPDATE main_todo SET $field = ? WHERE id = ?")
        statement?.setString(1, value)
        statement?.setString(2, id)
        statement?.executeUpdate()
        statement?.close()
    }

    // Function update updates all fields in a row
    override fun update(id: String, title: String, startDate: String, dueDate: String) {
        val statement = DatabaseUtil.connect()?.prepareStatement("UPDATE main_todo SET title = ?, start_date = ?, due_date = ?  WHERE id = ?")
        statement?.setString(1, title)
        statement?.setString(2, startDate)
        statement?.setString(3, dueDate)
        statement?.setString(4, id)
        statement?.executeUpdate()
        statement?.close()
    }

}