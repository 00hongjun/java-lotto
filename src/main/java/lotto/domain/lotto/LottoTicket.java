package lotto.domain.lotto;

import enums.Rank;
import lotto.exception.ValidLottoException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class LottoTicket {

    public static final double PRICE = 1000;
    private static final String NULL_PARAM_ERROR_MESSAGE = "매개변수:null";

    private final LottoNumbers numbers;

    public LottoTicket(final List<LottoNumber> numbers) {
        validateNumbers(numbers);
        this.numbers = new LottoNumbers(numbers);
    }

    public LottoTicket(final LottoNumbers numbers) {
        validateNumbers(numbers.getValue());
        this.numbers = new LottoNumbers(numbers);
    }

    private void validateNumbers(final List<LottoNumber> numbers) {
        Optional<List<LottoNumber>> numbersOpt = Optional.ofNullable(numbers);
        numbersOpt.orElseThrow(() -> new ValidLottoException(NULL_PARAM_ERROR_MESSAGE));
    }

    public int getMatchCount(final WinLottoTicket winTicket) {
        int matchCount = 0;
        for (LottoNumber num : this.numbers.getValue()) {
            matchCount += winTicket.getLottoTicket().numbers.getMatchNumberCount(num);
        }

        return matchCount;
    }

    private boolean isMatchBonus(final WinLottoTicket winTicket) {
        LottoNumber bonus = winTicket.getBonus();
        return numbers.getValue().contains(bonus);
    }

    public Rank getRank(final WinLottoTicket winLottoTicket) {
        int matchCount = getMatchCount(winLottoTicket);
        boolean matchBonus = isMatchBonus(winLottoTicket);
        return Rank.findRank(matchCount, matchBonus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
