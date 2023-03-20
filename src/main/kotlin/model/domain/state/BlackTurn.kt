package model.domain.state

import model.domain.rule.OmokForbiddenRuleAdapter
import model.domain.rule.OmokLongForbidden
import model.domain.tools.Board
import model.domain.tools.Location
import model.domain.tools.Stone
import model.domain.tools.Stone.BLACK

class BlackTurn(board: Board) : Turn(board) {
    override val stone: Stone = BLACK
    override fun isForbidden(location: Location) =
        OmokForbiddenRuleAdapter(board, stone).isForbidden(location) ||
            OmokLongForbidden.isForbidden(board, location, stone)
}
