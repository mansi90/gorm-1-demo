package bootcamp

class Author {

    String name

    static constraints = {
    }

    static hasMany = [books: Book]
}
