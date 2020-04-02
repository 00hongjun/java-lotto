package lotto.controller;

import lotto.domain.Money;
import lotto.domain.item.LottoGame;
import lotto.domain.item.LottoNumber;
import lotto.domain.item.LottoNumbers;
import lotto.domain.item.WinLottoTicket;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.LottoDto;
import lotto.view.ResultView;
import util.StringUtil;

import java.util.Collections;
import java.util.List;

public class LottoApplication {

    public static void main(String[] args) {
        String inputMoney = InputView.inputBuyAmount();
        Money money = new Money(StringUtil.parseStringToInt(inputMoney));

        int buyCount = StringUtil.parseStringToInt(InputView.inputBuyPassivityCount());
        List<String> buyPassivityCount = InputView.inputBuyPassivityLottoNumber(buyCount);

        LottoService lottoService = new LottoService();
        int availableBuyCount = lottoService.getAvailableBuyCount(money.minus(10));


        LottoDto dto1 = lottoService.passivityPlay(buyPassivityCount);
        ResultView.printLoots(dto1);


        LottoGame lottoGame = new LottoGame(money);
        LottoDto dto = lottoService.autoPlay(availableBuyCount - buyCount);
        ResultView.printLoots(dto);

        String luckyNumberInput = InputView.inputLuckyNumber();
        List<Integer> luckyNumber = StringUtil.splitStringToIntegers(luckyNumberInput);
        Collections.sort(luckyNumber);

        int bonus = StringUtil.parseStringToInt(InputView.inputBonusLuckyNumber());
        WinLottoTicket winLottoTicket = new WinLottoTicket(
                LottoNumbers.createLottoNumbersUseInteger(luckyNumber), new LottoNumber(bonus));

        LottoDto winGame = lottoGame.findWinGame(winLottoTicket);
        ResultView.printResult(winGame);

        LottoDto earningRate = lottoGame.getEarningRate(winLottoTicket);
        ResultView.printEarningResult(earningRate);
    }
}
