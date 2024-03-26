package omok.model.rule

import X_C
import Y_10
import Y_11
import Y_12
import Y_13
import Y_14
import Y_15
import omok.model.board.Board
import omok.model.position.Position
import omok.model.stone.BlackStone
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ExceedFiveCheckerTest {
    @BeforeEach
    fun setUp() {
        Board.reset()
    }

    @Test
    fun `장목이라면 true를 반환한다`() {
        val blackStone = BlackStone()
        blackStone.putStone(Position(X_C, Y_10))
        blackStone.putStone(Position(X_C, Y_11))
        blackStone.putStone(Position(X_C, Y_12))
        blackStone.putStone(Position(X_C, Y_14))
        blackStone.putStone(Position(X_C, Y_15))

        val actual = ExceedFiveChecker.isMoreThanFive(Position(X_C, Y_13))
        Assertions.assertThat(actual).isEqualTo(true)
    }
}
