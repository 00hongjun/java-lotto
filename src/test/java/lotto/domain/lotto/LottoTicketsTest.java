package lotto.domain.lotto;

import enums.Rank;
import lotto.domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoTicketsTest {

    private final List<LottoNumber> numbers =
            new ArrayList<>(Arrays.asList(
                    new LottoNumber(1),
                    new LottoNumber(2),
                    new LottoNumber(3),
                    new LottoNumber(4),
                    new LottoNumber(5),
                    new LottoNumber(6)));
    WinLottoTicket winTicket = new WinLottoTicket(numbers, new LottoNumber(10));

    List<LottoTicket> ticketList = new ArrayList<>(Arrays.asList(
            new LottoTicket(numbers),
            new LottoTicket(numbers),
            new LottoTicket(Arrays.asList(
                    new LottoNumber(1),
                    new LottoNumber(2),
                    new LottoNumber(3),
                    new LottoNumber(4),
                    new LottoNumber(5),
                    new LottoNumber(10))),
            new LottoTicket(Arrays.asList(
                    new LottoNumber(1),
                    new LottoNumber(2),
                    new LottoNumber(3),
                    new LottoNumber(4),
                    new LottoNumber(5),
                    new LottoNumber(11))),
            new LottoTicket(Arrays.asList(
                    new LottoNumber(1),
                    new LottoNumber(2),
                    new LottoNumber(3),
                    new LottoNumber(4),
                    new LottoNumber(5),
                    new LottoNumber(12))),
            new LottoTicket(Arrays.asList(
                    new LottoNumber(1),
                    new LottoNumber(2),
                    new LottoNumber(3),
                    new LottoNumber(4),
                    new LottoNumber(5),
                    new LottoNumber(13))),
            new LottoTicket(Arrays.asList(
                    new LottoNumber(1),
                    new LottoNumber(2),
                    new LottoNumber(3),
                    new LottoNumber(20),
                    new LottoNumber(30),
                    new LottoNumber(45)))
    ));

    @DisplayName("생성자 테스트")
    @Test
    public void constructor_success() throws Exception {
        //given
        LottoTickets lottoTickets = new LottoTickets(ticketList);
    }

    @DisplayName("당첨 번호 일치 하는 로또 개수 비교")
    @Test
    public void getXXXLottoCount_success() throws Exception {
        //given
        LottoTickets lottoTickets = new LottoTickets(ticketList);

        //then
        assertAll(
                () -> assertThat(lottoTickets.findWinLottoCountFromRank(Rank.FIRST, winTicket))
                        .isEqualTo(2),
                () -> assertThat(lottoTickets.findWinLottoCountFromRank(Rank.SECOND, winTicket))
                        .isEqualTo(1),
                () -> assertThat(lottoTickets.findWinLottoCountFromRank(Rank.THIRD, winTicket))
                        .isEqualTo(3),
                () -> assertThat(lottoTickets.findWinLottoCountFromRank(Rank.FOURTH, winTicket))
                        .isEqualTo(0)
        );
    }

    @DisplayName("총 상금 금액 계산")
    @Test
    public void getAllEarningPrize_success() throws Exception {
        //given
        List<LottoTicket> ticket = new ArrayList<>(
                Arrays.asList(
                        new LottoTicket(Arrays.asList(
                                new LottoNumber(1),
                                new LottoNumber(2),
                                new LottoNumber(3),
                                new LottoNumber(4),
                                new LottoNumber(5),
                                new LottoNumber(12))),
                        new LottoTicket(Arrays.asList(
                                new LottoNumber(1),
                                new LottoNumber(2),
                                new LottoNumber(3),
                                new LottoNumber(11),
                                new LottoNumber(12),
                                new LottoNumber(13))),
                        new LottoTicket(Arrays.asList(
                                new LottoNumber(1),
                                new LottoNumber(2),
                                new LottoNumber(3),
                                new LottoNumber(20),
                                new LottoNumber(30),
                                new LottoNumber(45))))
        );
        LottoTickets lottoTickets = new LottoTickets(ticket);

        //when
        Money prize = lottoTickets.getAllEarningPrize(winTicket);

        //then
        assertAll(
                () -> assertThat(prize.getMoney()).isEqualTo(1_510_000)
        );
    }

    @DisplayName("로또 티켓 수량 비교")
    @Test
    public void getTickets_success() throws Exception {
        //given
        List<LottoTicket> ticket = new ArrayList<>(
                Arrays.asList(new LottoTicket(numbers), new LottoTicket(numbers), new LottoTicket(numbers)));
        LottoTickets lottoTickets = new LottoTickets(ticket);

        //then
        assertThat(lottoTickets.getTickets().size()).isEqualTo(3);
    }

    @DisplayName("객체 복사 테스트")
    @Test
    public void clone_success() throws Exception {
        //given
        LottoTickets lottoTickets = new LottoTickets(ticketList);

        //when
        LottoTickets clone = (LottoTickets) lottoTickets.clone();

        //then
        assertThat(lottoTickets == clone).isFalse();
    }

    @DisplayName("주어진 수 만큼 랜덤 로또를 만들어 낸다")
    @Test
    public void lottoTicketGenerate_success() throws Exception {
        //given
        LottoTickets tickets = LottoTickets.from(3);

        //then
        assertThat(tickets.size()).isEqualTo(3);
    }

    @DisplayName("수동으로 입력한 번로를 이용하여 로또 티켓 생성")
    @Test
    public void generatePassivityLotto_sucess() throws Exception {
        //given
        LottoNumbers lottoNumbers1 = LottoNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumbers lottoNumbers2 = LottoNumbers.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<LottoNumbers> numbers =
                new ArrayList<>(Arrays.asList(lottoNumbers1, lottoNumbers2));

        //when
        LottoTickets tickets = LottoTickets.from(numbers);


        //then
        assertThat(tickets.size()).isEqualTo(2);
    }
}
