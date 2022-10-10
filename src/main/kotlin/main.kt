import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main() {
    /**
     * コルーチン部分
     * GlobalScope: CoroutineScopeの実装クラス
     */
    GlobalScope.launch {
        delay(1000L)
        println("Takuma")
    }

    /**
     * メインスレッド部分
     * コルーチンが終わるまで待つために、スレッドスリープを1.1秒入れている
     */
    println("my name is")
    Thread.sleep(1100L)

    /**
     * コルーチンスコープビルダー
     * スコープ内のコルーチン処理が全て終わるまで終了しないようにする
     */
    runBlocking {
        // launch{} 内の処理が別スレッドで非同期化される
        launch {
            delay(1000L)
            println("Takuma")
        }

        println("my name is")
    }

    runBlocking {
        launch {
            printName()
        }
        println("my name is")
    }

    /**
     * async/await（並列処理）
     * async: 非同期処理を行う
     * await: 非同期処理の結果を待つ
     */
    runBlocking {
        val result = async {
            delay(2000L)
            var sum = 0
            for (i in 1..10) {
                sum += i
            }
            sum
        }
        println("計算中...")
        println("計算結果: ${result.await()}")
    }

    runBlocking {
        val num1 = async {
            delay(1000L)
            2 * 10
        }

        val num2 = async {
            delay(2000L)
            2 + 4
        }

        println("num1 + num2 = ${num1.await() + num2.await()}")
    }
}

/**
 * サスペンド関数
 * delay()など、コルーチンの処理を一時中断する関数を呼び出すような関数では「suspend」をつける
 * あくまで中断できる関数という意味。中断することができる関数の方がわかりやすいかも。
 * 関数の中でdelay()などを読んでいなければ、実際には中断されないため。
 */
suspend fun printName() {
    delay(1000L)
    println("Takuma")
}


