package bootcamp

class Author {

    String name

    static constraints = {
    }

    static hasMany = [books: Book]

    def beforeValidate() {
        println "Into before validate"
    }

    def beforeInsert() {
        println "Into before insert"
    }

    def afterInsert() {
        println "Into after insert"
    }

    def afterUpdate() {
        println "Into after update"
    }

    String toString(){
        name
    }
}
