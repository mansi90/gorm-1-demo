import bootcamp.Author
import bootcamp.Book

class BootStrap {

    def init = { servletContext ->
        Author author = new Author(name: "Ankur")
        50.times {
            author.addToBooks(new Book(name: "Book-${it}"))
        }
        author.save()
        List<Author> authors = createAuthors()
        createBooks(authors)
    }


    def destroy = {
    }

    List<Author> createAuthors() {
        List<Author> authors = []
        2.times {
            Author author = new Author(name: "author-${it}")
            if (author.save()) {
                authors.add(author)
            } else {
                author.errors.allErrors.each { error ->
                    println error
                }
            }
        }
        return authors
    }

    List<Book> createBooks(List<Author> authors) {
        List<Book> books = []
        authors.each { Author author ->
            5.times {
                Book book = new Book(name: "Book-${it}", author: author)
                if (book.save()) {
                    books.add(book)
                } else {
                    book.errors.allErrors.each { error ->
                        println error
                    }
                }
            }

        }
        return books
    }
}
