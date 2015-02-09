package gorm1

class ApplicationFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {
                println "Params : ${params}"
            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
