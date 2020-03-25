package data
import com.example.spinerproject.Model


class Tables {
    abstract class Models{
        companion object{
            val table = "TAREAS"
            val id= "ID"
            val title = "TITLE"
            val description = "DESCRIPTION"
            val date= "DATE"
            val time= "TIME"
            var model:MutableList<Model> =ArrayList()
        }

    }
}