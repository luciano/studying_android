package course.android.luciano.palindromo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

// thiengo.com.br/kotlin-android-entendendo-e-primeiro-projeto

// em Kotlin so tem heranca simples igual no java
// primeiro coloca a classe que herda depois as interfaces

// se quiser trabalhar com nulo coloca o ?
class MainActivity :
        AppCompatActivity(),
        View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // por causa do kotlin-android-extensions
        // nao precisa do findViewById
        bt_verificar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        /*
        val palindromo = Palindromo( et_palindromo.text.toString())
        var resposta : String

        // if do Kotlin parecido com operador ternario
        resposta = if (palindromo.ehPalindromo())
                "${palindromo.conteudo} é um palindromo"
            else
                "${palindromo.conteudo} NÃO é um palindromo"

        tv_resposta.text = resposta
        */

        // nao e necessario usar a Palindromo pq fez a extensao da classe String
        tv_resposta.text = et_palindromo.text.toString().ehPalindromo()
    }
}

//    caracteristica do Kotlin, extender classe
//    colocar a funcao ehPalindromo na classe String
//  pode ficar fora do escopo de classe

fun String.ehPalindromo() : String {
    return if (this == this.reversed())
            "${this.toLowerCase()} é um palindromo"
        else
            "${this.toLowerCase()} NÃO é um palindromo"
}
