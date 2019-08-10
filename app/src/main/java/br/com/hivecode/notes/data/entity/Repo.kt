package br.com.hivecode.notes.data.entity

data class Repo(val name : String,
           val id : Long,
           var private : Boolean,
           val full_name : String) {

    override fun equals(other: Any?): Boolean {
        other as Repo
        return this.id == other.id
    }

}
