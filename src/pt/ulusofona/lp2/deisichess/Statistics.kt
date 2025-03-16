package pt.ulusofona.lp2.deisichess


import kotlin.collections.ArrayList


fun getStatsCalculator(stat: StatType): ((GameManager) -> ArrayList<String>)? {
    val gameManager = GameManager()
    when (stat) {
        StatType.TOP_5_CAPTURAS -> return :: getTop5Capturas
        StatType.TOP_5_PONTOS -> return :: getTop5Pontos
        StatType.PECAS_MAIS_5_CAPTURAS -> return :: getPecas5Capturas
        StatType.PECAS_MAIS_BARALHADAS -> return :: getPecasBaralhadas
        StatType.TIPOS_CAPTURADOS -> return :: getTiposCapturados
    } //recebe gameManeger e retorna lista de strings
}

fun getTop5Capturas(gameManager : GameManager) : ArrayList<String> {
    val todasAsPecas = gameManager.tabuleiro.getAllPecas().toList()
    //adcionar uma arrayList string

    val top5CapturasStrings = todasAsPecas
        .sortedByDescending { (_, peca) -> peca.getNrCapturas() }
        .take(5)
        .map {(_, peca) -> (peca.getAlcunha() + " (" + peca.equipaNome + ") fez "+ peca.getNrCapturas() +" capturas")}
        .toCollection(ArrayList())

    return top5CapturasStrings
}


//TOP5PONTOS
fun getTop5Pontos(gameManager : GameManager) : ArrayList<String> {
    val todasAsPecas = gameManager.tabuleiro.getAllPecas().toList()
    //adcionar uma arrayList string

    val top5CapturasPontosString = todasAsPecas
        .filter {(_, peca) -> peca.getNrCapturas() > 0}
        .sortedWith(compareByDescending<Pair<*, Peca>> { (_, peca) -> peca.getPontosDeCaptura() }
            .thenBy { (_, peca) -> peca.getAlcunha() })
        .take(5)
        .map {(_, peca) -> (peca.getAlcunha() + " (" + peca.equipaNome + ") tem "+ peca.getPontosDeCaptura() +" pontos")}
        .toCollection(ArrayList())

    return top5CapturasPontosString
}

fun getPecas5Capturas(gameManager : GameManager) : ArrayList<String> {
    val todasAsPecas = gameManager.tabuleiro.getAllPecas().toList()

    val getPecas5CapturasString =todasAsPecas
        .filter{(_, peca) -> peca.getNrCapturas() > 5}
        .map {(_, peca) -> peca.equipaNome + ":"+ peca.getAlcunha() + ":" + peca.getNrCapturas()}
        .toCollection(ArrayList())

    return getPecas5CapturasString;

}

fun getPecasBaralhadas(gameManager : GameManager) : ArrayList<String> {
    val todasAsPecas = gameManager.tabuleiro.getAllPecas().toList()

    val getPecasBaralhadasString =todasAsPecas
        .filter{(_, peca) -> peca.getNrJogadasInvalidas() > 0}
        .sortedByDescending { (_, peca) ->
            peca.getNrJogadasInvalidas().toDouble()/ peca.getNrJogadasValidas() + peca.getNrJogadasInvalidas() //racio de jogadas invalidas pelas totais
        }
        .take(3)
        .map {(_, peca) -> "" +peca.getEquipa() + ":" + peca.getAlcunha() + ":" + peca.getNrJogadasInvalidas() + ":" + peca.getNrJogadasValidas()}
        .toCollection(ArrayList())
    return getPecasBaralhadasString
}

fun getTiposCapturados(gameManager : GameManager) : ArrayList<String> {
    val todasAsPecas = gameManager.tabuleiro.getAllPecas().toList()

    val getTiposCapturadosString =todasAsPecas
        .filter{(_, peca) -> peca.verificaEstadoPeca()}
        .distinctBy{(_, peca) -> peca.nomeTipo()}
        .sortedBy { (_, peca) -> peca.nomeTipo() }
        .map {(_, peca) -> peca.nomeTipo()}
        .toCollection(ArrayList())
    return getTiposCapturadosString

}

