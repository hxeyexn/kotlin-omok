package omok.model.stone

import omok.model.board.Board
import omok.model.position.Col
import omok.model.position.Position
import omok.model.position.Row
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class BlackStoneTest {
    @BeforeEach
    fun setUp() {
        repeat(Board.BOARD_SIZE) { row ->
            repeat(Board.BOARD_SIZE) { col ->
                Board.board[row][col] = Stone.NONE
            }
        }
    }

    @Test
    fun `이미 돌이 놓인 자리에 돌을 놓을 경우 예외가 발생한다`() {
        val stone = BlackStone()
        val position = Position(Row('A'), Col.from(1))
        stone.putStone(position)
        assertThatThrownBy { stone.putStone(position) }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("이미 놓여진 자리입니다.\n")
    }

    @Test
    fun `유효한 자리에 돌을 놓을 경우 예외가 발생하지 않는다`() {
        val stone = BlackStone()
        val position = Position(Row('A'), Col.from(2))
        assertDoesNotThrow { stone.putStone(position) }
    }
}
