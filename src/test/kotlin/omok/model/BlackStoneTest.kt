package omok.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

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
        val position = Position(0, 0)
        stone.putStone(position)
        assertThatThrownBy { stone.putStone(position) }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("이미 놓여진 자리입니다.\n")
    }

    @Test
    fun `유효한 자리에 돌을 놓을 경우 예외가 발생하지 않는다`() {
        val stone = BlackStone()
        val position = Position(1, 2)
        assertDoesNotThrow { stone.putStone(position) }
    }

    @MethodSource("오목 여부 판단 테스트 데이터 - 성공")
    @ParameterizedTest
    fun `오목 여부를 판단한다 - 오목일 때`(stonePositions: List<Position>) {
        // given
        val blackStone = BlackStone()

        stonePositions.forEach {
            blackStone.putStone(it)
        }

        val lastPosition = Position(4, 4)
        // when
        val actual = blackStone.findOmok(lastPosition)
        val expected = true

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @MethodSource("오목 여부 판단 테스트 데이터 - 실패")
    @ParameterizedTest
    fun `오목 여부를 판단한다 - 오목이 아닐 때`(stonePositions: List<Position>) {
        // given
        val blackStone = BlackStone()
        val whiteStone = WhiteStone()

        stonePositions.forEach {
            blackStone.putStone(it)
        }
        whiteStone.putStone(Position(12, 12))

        val lastPosition = Position(11, 11)
        // when
        val actual = blackStone.findOmok(lastPosition)
        val expected = false

        // then
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `오목 여부 판단 테스트 데이터 - 성공`() =
            listOf(
                Arguments.of(listOf(Position(1, 1), Position(2, 2), Position(3, 3), Position(4, 4), Position(5, 5))),
            )

        @JvmStatic
        fun `오목 여부 판단 테스트 데이터 - 실패`() =
            listOf(
                Arguments.of(listOf(Position(10, 10), Position(11, 11), Position(13, 13), Position(14, 14))),
            )
    }
}
