package bootcamp

class Book {

    String name

    static constraints = {
    }

    static belongsTo = [author: Author]
}
