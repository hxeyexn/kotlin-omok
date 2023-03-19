# kotlin-omok

## 기능 목록

- [x] 흑과 백이 번갈아가며 게임을 진행한다.
    - [x] 이미 돌이 놓여있지 않은지 확인한다.
    - [x] 돌을 놓는다.
- [x] 가로나 세로, 대각선으로 다섯 개의 연속된 돌이 있는지 확인한다.
- [x] 연속된다면 직전에 돌을 놓은 색이 승리한다.
- [x] 흑만 3-3과 4-4, 장목이 금지된다.
    - [x] 단, 금지된 수를 놓으면서 동시에 5도 만들어지는 경우에는 흑 승리로 인정된다.
        - [x] 3-3이 금지된다.
            - [x] 4-3은 금수가 아니지만 4-3-3은 금수이다.
        - [x] 4-4가 금지된다.
            - [x] 4-4-3은 금수이다.
        - [x] 장목이 금지된다.