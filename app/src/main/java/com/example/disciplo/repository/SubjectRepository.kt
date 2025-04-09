import com.example.disciplo.models.Subject

object SubjectRepository {
    private val subjects = mutableListOf("Desenvolvimento Web", "Práticas de Sistema", "Banco de Dados", "Desenvolvimento Mobile")

    fun getAll(): List<String> = subjects

    fun add(subject: String) {
        if (subject.isNotBlank() && subject !in subjects) {
            subjects.add(subject)
        }
    }

    fun remove(subject: String) {
        subjects.remove(subject)
    }

    fun update(old: String, new: String) {
        val index = subjects.indexOf(old)
        if (index != -1 && new.isNotBlank()) {
            subjects[index] = new
        }
    }
}