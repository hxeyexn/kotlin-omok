package omok.model.external.rule

import omok.model.external.rule.type.Foul
import omok.model.external.rule.type.Violation
import omok.model.external.rule.wrapper.point.Point

class WhiteRenjuRule(
    boardWidth: Int = DEFAULT_BOARD_WIDTH,
    boardHeight: Int = DEFAULT_BOARD_HEIGHT,
) : OmokRule(boardWidth, boardHeight) {
    override fun checkDoubleFoul(
        blackPoints: List<Point>,
        whitePoints: List<Point>,
        startPoint: Point,
        foul: Foul,
    ): Violation = Violation.NONE

    override fun checkOverline(
        stonesPoints: List<Point>,
        startPoint: Point,
    ): Violation = Violation.NONE

    companion object {
        private const val DEFAULT_BOARD_WIDTH = 15
        private const val DEFAULT_BOARD_HEIGHT = 15
    }
}
