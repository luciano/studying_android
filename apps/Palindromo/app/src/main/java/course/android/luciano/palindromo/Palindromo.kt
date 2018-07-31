package course.android.luciano.palindromo

// parametro local de construtor
// se tivesse um val conteudo, poderia ser usado em toda a classe
// conteudo não pode ser nulo ou tem que definir ele como explicito
// mas coloca o null explicito por colocar um ?
class Palindromo constructor( conteudo: String) {
    // val -> imutavel, equivalente ao final do java
    // var -> mutavel
    // explicitou com o ? que pode receber valor nulo
    val conteudo : String = conteudo
    // sobrescrevendo um get
        get(){
            return field.toLowerCase()
            // field é campo de apoio
            // é usado pra costumizar get e set
        }
    // tem de forma implicita o get e set
    // val so tem setter
    // var tem setter e getter

    // criando funcao
    // String é data class que objetivo é conter dados
    fun ehPalindromo(): Boolean {
        return conteudo == conteudo.reversed()
    }

    // por padrao é public
    // get e set é public
}