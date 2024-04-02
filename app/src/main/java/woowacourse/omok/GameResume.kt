package woowacourse.omok

import android.widget.ImageView
import omok.model.position.Column
import omok.model.position.Position
import omok.model.position.Row
import omok.model.stone.BlackStone
import omok.model.stone.StoneType
import omok.model.stone.WhiteStone
import woowacourse.omok.adapter.OmokBoardAdapter
import woowacourse.omok.db.OmokEntry
import woowacourse.omok.db.OmokEntryDao

object GameResume {
    fun restoreProgressGameData(
        positions: List<ImageView>,
        dao: OmokEntryDao,
    ): StoneType {
        val entries = dao.findAll()
        if (entries.isEmpty()) return StoneType.NONE

        val omokGameData =
            entries.associateBy {
                OmokBoardAdapter.convertPositionToIndex(
                    Position(Row(it.row), Column(it.column)),
                )
            }

        restoreBoard(entries)
        positions.forEachIndexed { index, view ->
            val stoneDrawable = getCurrentStoneDrawable(omokGameData, index)
            placeCurrentStone(stoneDrawable, view)
        }
        return StoneType.from(entries.last().stoneType)
    }

    private fun restoreBoard(omokEntries: List<OmokEntry>) {
        omokEntries.forEach {
            val position = Position(Row(it.row), Column(it.column))
            when (it.stoneType) {
                StoneType.BLACK_STONE.type -> BlackStone().putStone(position)
                StoneType.WHITE_STONE.type -> WhiteStone().putStone(position)
            }
        }
    }

    private fun getCurrentStoneDrawable(
        omokGameData: Map<Int, OmokEntry>,
        index: Int,
    ): Int? {
        val stoneType = omokGameData[index]?.stoneType
        val stoneDrawable =
            StoneType.entries.firstOrNull { it.type == stoneType }?.let {
                OmokBoardAdapter.convertStoneTypeToDrawable(it)
            }
        return stoneDrawable
    }

    private fun placeCurrentStone(
        stoneDrawable: Int?,
        view: ImageView,
    ) {
        stoneDrawable?.let {
            view.setImageResource(it)
        }
    }
}
