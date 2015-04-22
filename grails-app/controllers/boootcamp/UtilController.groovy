package boootcamp

import bootcamp.Author
import bootcamp.Book

class UtilController {

    def list(Integer offset, Integer max) {
        List bookList = Book.list(max: max, offset: offset)
        // List bookList = Book.list()
        render(bookList.id)
    }

    def getDemo() {
        render Book.get(1)
        render "<br/>"
        render Book.getAll([1, 2, 3])
    }

    def validate() {
        Book book = new Book()
        book.validate()
        book.errors.allErrors.each {
            render it
            render "<br/>"
            render "<br/>"
        }
    }

    def addTo() {
        Author author = Author.get(1)
        render "<br/><br/>Before save : "+author.books.size()+"<br/><br/> "
        author.books.each {
            render "<br/>${it}"
        }
        author.addToBooks(new Book(name: "Grails"))
        author.save()
        render "<br/><br/>After save : "+author.books.size()+"<br/><br/> "
        author.books.each {
            render "<br/>${it}"
        }
    }

    def removeFrom() {
        Author author = Author.get(1)
        render "<br/><br/>Before save ${Book.count()}-- ${author.books.size()}<br/><br/>"
        author.books.each {
            render "<br/>${it}"
        }
        Book book = author.books[0]
        render "Removing Book ${book}"
        if (book) {
            author.removeFromBooks(book)
            author.save(flush: true, failOnError: true)
        }
        render "<br/><br/>After save ${Book.count()}-- ${author.books.size()}<br/><br/>"
        author.books.each {
            render "<br/>${it}"
        }
    }

    def findBy(String name) {
        render Author.findByName(name)+"<br/>"
        render(Book.findAllByNameInList(["Book-1", "Book-2"]))
    }

    def findOrSave() {
        render Book.findOrCreateByName("Grails")
        render("<br/>Done")
    }

    def pagination(String name) {
        Book.findAllByNameLike("${name}%").each {
            render "${it} <br/><br/>"
        }
        render "------------Paginated data---------------<br/> <br/>"
        Book.findAllByNameLike("${name}%", [max: 5, offset: 2]).each {
            render "${it} <br/><br/>"

        }
    }

    def hql() {
        render Book.find("from Book where name=:name", [name: 'The Stand'])
    }
}
