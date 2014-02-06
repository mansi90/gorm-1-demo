package boootcamp

import bootcamp.Author
import bootcamp.Book

class UtilController {

    def list() {
//        List books = Book.list(max:2, offset: 1)
        List bookList = Book.list()
        render(bookList)
    }

    def getDemo() {
        println Book.get(1)
        println Book.getAll([1, 2, 3])
        render("Done")
    }

    def validate() {
        Book book = new Book()
        book.validate()
        book.errors.allErrors.each {
            println it
        }
        render("Done")
    }

    def addTo() {
        Author author = new Author(name: "Ankur")

        author.addToBooks(new Book(name: "Grails"))
        render(author.save())
    }

    def removeFrom() {
        Author author = Author.get(1)

        println Book.list()
        Book book = Book.get(1)
        author.removeFromBooks(book)
        author.save(flush: true, failOnError: true)
        render(Book.list())
    }

    def findBy(){
        Author.findByName("Ankur")
        render(Book.findAllByNameInList(["Book-1", "Book-2"]))
    }

    def findOrSave(){
        println Book.findOrCreateByName("Grails")
        Book.findOrSaveByNameAndAuthor("Groovy", Author.read(1))
        render ("Done")
    }

    def pagination(){
        render(Book.findAllByNameLike("Book%", [max:5, offset:2]))
    }

    def hql(){
        Book.find("from Book where  name=:name title=:title",[name:'The Stand', title:"some value"])
    }

}
