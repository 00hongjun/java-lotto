package lotto.service;

import lotto.domain.Money;
import lotto.domain.item.LottoTicket;
import lotto.domain.item.LottoTickets;
import lotto.domain.stragegy.LottoGenerator;
import lotto.view.LottoDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGame {

    private static final double LOTTO_PRICE = 1000;

    private Money money;
    private int playGameCount;
    private LottoTickets lottoTickets;

    public LottoGame(Money money) {
        this.money = money;
        this.playGameCount = money.getHowManyBuyItem(new Money(LOTTO_PRICE));
    }

    public LottoGame(Money money, LottoTickets lottoTickets) {
        this.money = money;
        this.lottoTickets = lottoTickets;
    }

    private LottoTicket buyOneLottoTicket(List<Integer> numbers) {
        playGameCount--;
        return new LottoTicket(numbers);
    }

    private void buyAllLottoTicket() {
        List<LottoTicket> lottos = new ArrayList<>();
        while (playGameCount > 0) {
            List<Integer> numbers = LottoGenerator.lottoNumberGenerator();
            lottos.add(buyOneLottoTicket(numbers));
        }
        this.lottoTickets = new LottoTickets(Collections.unmodifiableList(lottos));
    }

    public LottoDto play() {
        buyAllLottoTicket();
        LottoDto dto = new LottoDto();

        dto.setTickets(this.lottoTickets.getTickets());

        return dto;
    }

    public LottoDto findWinGame(List<Integer> luckyNumber) {
        LottoDto dto = new LottoDto();
        dto.setMatch3GameCount(this.lottoTickets.getLuckyNumberMatch3Count(luckyNumber));
        dto.setMatch4GameCount(this.lottoTickets.getLuckyNumberMatch4Count(luckyNumber));
        dto.setMatch5GameCount(this.lottoTickets.getLuckyNumberMatch5Count(luckyNumber));
        dto.setMatch6GameCount(this.lottoTickets.getLuckyNumberMatch6Count(luckyNumber));
        return dto;
    }

    private Money getAllEarningPrize(List<Integer> luckyNumber) {
        return this.lottoTickets.getAllEarningPrize(luckyNumber);
    }

    public LottoDto getEarningRate(List<Integer> luckyNumber) {
        LottoDto dto = new LottoDto();
        Money prize = getAllEarningPrize(luckyNumber);
        int howManyBuyItem = money.getHowManyBuyItem(new Money(LOTTO_PRICE));
        Money buyAmount = new Money(LOTTO_PRICE).multiply(howManyBuyItem);

        double rate = Math.floor(prize.divide(buyAmount).getMoney() * 100) / 100;

        dto.setEarningRate(rate);
        return dto;
    }
}
