package com.example.todo.data


object TodoDB {
    const val DB_NAME = "todo"
    const val DB_VERSION = 2

    const val createTableTodo = "CREATE TABLE IF NOT EXISTS ${TableTodo.TABLE_NAME} (" +
                                "${TableTodo.ID} INTEGER PRIMARY KEY, " +
                                "${TableTodo.NAME} TEXT, " +
                                "${TableTodo.CREATE_DATE} TEXT, " +
                                "${TableTodo.MODIFY_DATE} TEXT )"

    const val createTableTask = "CREATE TABLE IF NOT EXISTS ${TableTask.TABLE_NAME} (" +
                                "${TableTask.ID} INTEGER PRIMARY KEY, " +
                                "${TableTask.NAME} TEXT, " +
                                "${TableTask.DONE} INTEGER default 0 check( ${TableTask.DONE} in (0, 1) ), " +
                                "${TableTask.ID_TODO} INTEGER, " +
                                "FOREIGN KEY(${TableTask.ID_TODO}) REFERENCES ${TableTodo.TABLE_NAME}(${TableTodo.ID}) ON UPDATE CASCADE ON DELETE CASCADE )"


    object TableTodo {
        const val TABLE_NAME = "todo"
        const val ID = "id"
        const val NAME = "name"
        const val CREATE_DATE = "create_date"
        const val MODIFY_DATE = "modify_date"
    }

    object TableTask {
        const val TABLE_NAME = "tak"
        const val ID = "id"
        const val NAME = "name"
        const val DONE = "done"
        const val ID_TODO = "id_todo"
    }
}