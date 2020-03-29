package lotto.domain.item;

import java.util.*;

public class Lotto {
    private static final int LOTTO_NUMBER_SIZE = 6;
    private static final int LOTTO_MIN_NUMBER_SIZE = 1;
    private static final int LOTTO_MAX_NUMBER_SIZE = 45;

    protected final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        Collections.sort(numbers);
        validateSize(numbers);
        validateNumberRange(numbers);
        validateDuplicate(numbers);

        List<Integer> result = new ArrayList<>(numbers);
        this.numbers = Collections.unmodifiableList(result);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("번호는 6개만 지정 가능 합니다.");
        }
    }

    private void validateNumberRange(List<Integer> numbers) {
        for (Integer i : numbers) {
            if (i < LOTTO_MIN_NUMBER_SIZE || i > LOTTO_MAX_NUMBER_SIZE) {
                throw new IllegalArgumentException("번호는 1~ 45 사이의 정수만 가능 합니다.");
            }
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> duplicate = new HashSet<>(numbers);
        if (numbers.size() != duplicate.size()) {
            throw new IllegalArgumentException("번호는 중복될 수 없습니다.");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(numbers, lotto.numbers);
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
