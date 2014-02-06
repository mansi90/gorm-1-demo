import bootcamp.Author
import bootcamp.Book

class BootStrap {

    def init = { servletContext ->
        Author author = new Author(name: "Ankur")
        5.times {
            author.addToBooks(new Book(name:"Book"))
        }
        author.save()
    }

    def destroy = {
    }
}
