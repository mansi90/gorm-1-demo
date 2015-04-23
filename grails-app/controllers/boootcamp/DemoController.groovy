package boootcamp

import bootcamp.Author
import bootcamp.Book

class DemoController {

    def index() {
        render "findById(1) >> " + Book.findById(1)
        render "<br/>" + "get(1) >> " + Book.get(1)
    }

    def basics() {
        render "Book List >> " + Book.list()
        render "<br/><br/>Book List with Pagination >> " + Book.list(offset: 10, max: 20)
        render "<br/><br/>Book List >> Sorted by Name >> " + Book.list(sort: "name", order: "asc")
    }

    def basics2() {
        render "getAll([23, 93, 81]) >> " + Book.getAll([23, 93, 81])
        render "<br/><br/>getAll(1..4) >> " + Book.getAll(1..4)
    }

    def demoAddTo() {
        Author author = Author.get(1)

        render "<br/><br/>Before save : " + author.books.size() + "<br/>" + author.books

        author.addToBooks(new Book(name: "Grails"))
        author.save()

        render "<br/><br/>After save : " + author.books.size() + "<br/>" + author.books
    }

    def demoRemoveFrom() {
        Author author = Author.get(1)

        render "<br/><br/>Before save ${Book.count()}-- ${author.books.size()}<br/>" + author.books

        Book book = author.books[0]

        render "Removing Book ${book}"
        if (book) {
            author.removeFromBooks(book)
            author.save(flush: true, failOnError: true)
        }

        render "<br/><br/>After save ${Book.count()}-- ${author.books.size()}<br/>" + author.books
    }

    def demoValidate() {
        Book book = new Book(name: "GORM-1 Demo", date: null)
        render "Book Validated  ? " + book.validate()
        if (!book.validate())
            render "<br/>Errors >>" + book.errors
    }

    def demoFindMethods() {
        render "findByName() >> " + Book.findByName("Grails")
        render "<br/>findAllByName() >> " + Book.findAllByName("Grails")

        render "<br/><br/>findByNameLike() >> " + Book.findByNameLike("Book%")
        render "<br/>findAllByNameLike() >> " + Book.findAllByNameLike("Book%")

        Date firstDate = new Date() - 10, secondDate = new Date()
        render "<br/><br/>findAllByDateBetween() >> " + Book.findAllByDateBetween(firstDate, secondDate)

        render "<br/><br/>findAllByDateGreaterThan() >> " + Book.findAllByDateGreaterThan(firstDate)*.date.collect {
            it.toGMTString()
        }

        render "<br/><br/>findAllByNameLikeOrDateLessThan() >> " + Book.findAllByNameLikeOrDateLessThan("Grails", firstDate)*.id
    }

    def demoFindOrCreate(String name) {
        render Book.findOrCreateByName(name)
        render("<br/>Done")
    }

    def demoFindOrSave(String name) {
        render Book.findOrSaveByName(name)
        render("<br/>Done")
    }

    def demoComparators() {
        render "NotEqual >>> " + Book.findAllByNameNotEqual("Grails")
        render "<br/><br/>IsNotNull >>> " + Book.findAllByNameIsNotNull()
    }

    def demoCountAndCountBy() {
        render "countByName >> " + Book.countByName("Grails")

        Date firstDate = new Date() - 10, secondDate = new Date()
        render "<br/<br/>countByDateBetween >> " + Book.countByDateBetween(firstDate, secondDate)

        render "<br/<br/>count() >> " + Book.count()
    }

    def demoWhereQueries() {
        render "findWhere  >> "+Book.findWhere(name: "Grails")
        render "<br/<br/>findAllWhere  >> "+Book.findAllWhere(name: "Grails")

        render "<br/<br/>find  >> "+Book.find("from Book where name='Grails'")
        render "<br/<br/>findAll  >> "+Book.findAll("from Book where name='Grails'")
    }
}
