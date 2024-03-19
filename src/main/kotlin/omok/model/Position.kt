package omok.model

data class Position(val row: Int, val col: Int) {
    init {
        require(row in positionRange && col in positionRange) { invalidPositionMessage(row, col) }
    }

    companion object {
        private const val MIN_RANGE = 0
        private const val MAX_RANGE = 14
        private val positionRange = MIN_RANGE..MAX_RANGE

        private fun invalidPositionMessage(
            row: Int,
            col: Int,
        ) = "유효하지 않은 위치입니다. 현재 입력 값: $row, $col"
    }
}
