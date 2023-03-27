package omok.domain.judgment

import omok.domain.board.Column
import omok.domain.board.Position
import omok.domain.board.Row
import omok.domain.player.Stone
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class OverFiveRefereeTest {
    @Test
    fun `장목이면 금지된 수이다`() {
        val board = OVER_FIVE_BOARD
        val referee = OverFiveReferee(Stone.BLACK)
        val position = Position(Column.H, Row.EIGHT)

        board[position] = Stone.BLACK

        Assertions.assertThat(referee.isForbiddenPlacement(board, position)).isTrue
    }
}
