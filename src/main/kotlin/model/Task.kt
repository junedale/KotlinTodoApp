package model

import model.base.BaseModel
import util.DatabaseUtil
import java.awt.Component

object Task : BaseModel {
    var ongoing = 0

    // Function find obtains a row in the database that corresponds to the given id
    override fun findAll(id: String): List<List<String>> {
        val output = ArrayList<String>()
        output.clear()
        val statement = DatabaseUtil.connect()
            ?.prepareStatement("SELECT id, main_id, title, status FROM sub_todo WHERE main_id = ?")
        statement?.setString(1, id)
        val resultSet = statement?.executeQuery()

        while (resultSet?.next() == true) {
            output.add(resultSet.getString(1))
            output.add(resultSet.getString(2))
            output.add(resultSet.getString(3))
            output.add(resultSet.getString(4))
        }
        statement?.close()
        return output.chunked(4)
    }

    // Function insert inserts data to a table in the database
    override fun insert(id: String, title: String, startDate: String, dueDate: String) {
        val statement =
            DatabaseUtil.connect()?.prepareStatement("INSERT INTO sub_todo (main_id, title, status) VALUES (?, ?, ?)")
        statement?.setString(1, id)
        statement?.setString(2, title)
        statement?.setString(3, "0")
        statement?.executeUpdate()
        statement?.close()
    }

    // Function delete removes a row from a table
    override fun delete(id: String) {
        val statement = DatabaseUtil.connect()?.prepareStatement("DELETE FROM sub_todo WHERE id = ?")
        statement?.setString(1, id)
        statement?.executeUpdate()
        statement?.close()
    }

    // Function updateField updates a single field in a row
    override fun updateField(id: String, field: String, value: String) {
        val statement = DatabaseUtil.connect()?.prepareStatement("UPDATE sub_todo SET $field = ? WHERE id = ?")
        statement?.setString(1, value)
        statement?.setString(2, id)
        statement?.executeUpdate()
        statement?.close()
    }

    // Not implemented
    override fun update(id: String, title: String, startDate: String, dueDate: String) { }

    // Function compute progress computes the progress of a project or to-do
    fun computeProgress(id: String, cardList: ArrayList<Component>): Int {
        val task  = cardList.size
        val statement = DatabaseUtil.connect()?.prepareStatement("SELECT COUNT(id) FROM sub_todo WHERE main_id = ? AND status = ?")
        statement?.setString(1, id)
        statement?.setString(2, "1")
        val resultSet = statement?.executeQuery()
        resultSet?.next()
        val completed = resultSet?.getInt(1)!!
        ongoing = task - completed
        if (completed == 0)
            return 0
        val progress: Double = completed.toDouble() / task * 100
        return progress.toInt()
    }

    // Not implemented
    override fun find(id: String): List<String> { return emptyList() }
}