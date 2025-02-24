# 기능 요구 사항
1. step2
    * 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
    * 로또 1장의 가격은 1000원이다.
    * 1장당 6개의 중복되지 않은 번호를 가진다
    * 2등을 위해 추가 번호를 하나 더 추첨한다.
1. step3
    * 2등을 위해 추가 번호를 하나 더 추첨한다.
    * 당첨 통계에 2등도 추가해야 한다.
1. step4
    * 현재 로또 생성기는 자동 생성 기능만 제공한다. 사용자가 수동으로 추첨 번호를 입력할 수 있도록 해야 한다.
    * 입력한 금액, 자동 생성 숫자, 수동 생성 번호를 입력하도록 해야 한다.    

# 프로그리밍 요구사항
1. step2
    * 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
        * 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
        * UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.
    * indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
        * 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
        * 힌트: indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메소드)를 분리하면 된다.
    * 함수(또는 메소드)의 길이가 15라인을 넘어가지 않도록 구현한다.
    * 함수(또는 메소드)가 한 가지 일만 잘 하도록 구현한다.
    * 모든 로직에 단위 테스트를 구현한다. 단, UI(System.out, System.in) 로직은 제외
        * 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
        * UI 로직을 InputView, ResultView와 같은 클래스를 추가해 분리한다.
    * 자바 코드 컨벤션을 지키면서 프로그래밍한다.
        * 참고문서: https://google.github.io/styleguide/javaguide.html 또는 https://myeonguni.tistory.com/1596
    * else 예약어를 쓰지 않는다.
        * 힌트: if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.
        * else를 쓰지 말라고 하니 switch/case로 구현하는 경우가 있는데 switch/case도 허용하지 않는다.
    * java enum을 적용해 프로그래밍을 구현한다.
1. step3
    * 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
    * java enum을 적용해 프로그래밍을 구현한다.
    * 규칙 8: 일급 콜렉션을 쓴다.
    * indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
    * 함수(또는 메소드)의 길이가 15라인을 넘어가지 않도록 구현한다.
    * 자바 코드 컨벤션을 지키면서 프로그래밍한다.
    * else 예약어를 쓰지 않는다.
1. step4
    * 규칙 3: 모든 원시값과 문자열을 포장한다.
    * 규칙 5: 줄여쓰지 않는다(축약 금지).
    * 예외 처리를 통해 에러가 발생하지 않도록 한다.

# 기능 목록 및 commit 로그 요구사항
* 기능을 구현하기 전에 README.md 파일에 구현할 기능 목록을 정리해 추가한다.
* git의 commit 단위는 앞 단계에서 README.md 파일에 정리한 기능 목록 단위로 추가한다.


---

### 로또 번호 (LottoNo)
* 1 ~ 45사이의 번호를 가진다

### 로또 서비스 (LottoGame)
* 구매 가능한 최대의 로또를 구매 한다.
* 당첨 로또를 확인한다.
* 수익률을 확인한다.

### 로또 티켓 (LottoTicket)
* 6개의 양의 정수를 가진다
* 각각의 정수는 1 ~ 45 사이의 크기를 가진다.
* 각각의 번호는 중복 불가능 하다.
* 몇개의 번호가 매칭 되었는지 반환한다.
* 해당 로또가 몇등인지 판단한다

### 로또 티켓 (LottoTickets)
* 3 ~ 6개의 당첨된 로또 개수를 구한다.
* 총 당첨 금액을 구한다.

### 당첨 티켓 (WinLottoTicket)
* 6개의 번호와 보너스 번호가 중복되선 안된다.

### 번호 생성기 (LottoGenerator)
* 0 ~ 45 사이의 랜덤한 자연수 6개를 구한다.

### 현금 (Money)
* 양의 정수 값을 가진다
* 덧셈 기능
* 곱셈 기능
* 나눗셈 기능
* 구매 가능한 물건의 수 계산

### 로또enum (LottoPrize)
* 상금을 저장 한다.
* 당첨된 상금액을 계산한다.

### 로또 게임 진행 (LottoGame)
* 구매 가능한 최대 로또 개수만큼 로또를 생성
* 당첨된 로또를 당첨번호 개수별로 반환한다.
* 수익률을 계산한다.

