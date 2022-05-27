package model.base

interface BaseModel {

    fun findAll(id: String = "1"): List<List<String>>
    fun find(id: String): List<String>
    fun insert(id: String = "", title: String = "", startDate: String = "", dueDate: String = "")
    fun updateField(id: String = "", field: String, value: String = "")
    fun update(id: String = "", title: String = "", startDate: String = "", dueDate: String = "")
    fun delete(id: String)


}